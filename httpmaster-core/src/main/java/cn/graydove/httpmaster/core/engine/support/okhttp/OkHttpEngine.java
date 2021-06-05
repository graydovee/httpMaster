package cn.graydove.httpmaster.core.engine.support.okhttp;

import cn.graydove.httpmaster.core.common.HeaderConstant;
import cn.graydove.httpmaster.core.engine.AbstractHttpEngine;
import cn.graydove.httpmaster.core.enums.HttpMediaType;
import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpBody;
import cn.graydove.httpmaster.core.request.HttpHeader;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.XML;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @author graydove
 */
public class OkHttpEngine extends AbstractHttpEngine {

    private final OkHttpClientFactory okHttpClientFactory;

    private final OkHttpClient client;

    public OkHttpEngine(OkHttpClientFactory okHttpClientFactory) {
        this.okHttpClientFactory = okHttpClientFactory;
        this.client = okHttpClientFactory.newOkHttpClient();
    }

    public OkHttpEngine() {
        this(new DefaultOkHttpClientFactory());
    }

    @Override
    public HttpResponse execute(HttpMethod httpMethod, HttpRequest httpRequest) {
        Request request = buildRequest(httpMethod, httpRequest);
        try {
            Response response = client.newCall(request).execute();
            return new OkHttpResponse(response);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    private Request buildRequest(HttpMethod httpMethod, HttpRequest httpRequest) {
        Request.Builder builder = new Request.Builder()
                .url(httpRequest.url().getFullUrl());
        // head
        HttpHeader httpHeader = httpRequest.getHttpHeader();
        if (null != httpHeader) {
            httpHeader.forEach(builder::addHeader);
        }
        // body
        RequestBody requestBody = toRequestBody(httpRequest);
        builder.method(httpMethod.name(), requestBody);
        return builder.build();
    }

    private RequestBody toRequestBody(HttpRequest httpRequest) {
        HttpBody httpBody = httpRequest.getHttpBody();
        if (null == httpBody) {
            return null;
        }
        String contentType = httpRequest.header().asMap().get(HeaderConstant.CONTENT_TYPE);
        HttpMediaType httpMediaType;
        MediaType mediaType;
        if (contentType == null) {
            httpMediaType = HttpMediaType.URL_ENCODED;
            mediaType = MediaType.get(httpMediaType.getValue());
        } else {
            httpMediaType = HttpMediaType.toHttpMediaType(contentType).orElse(HttpMediaType.URL_ENCODED);
            mediaType = MediaType.get(contentType);
        }

        String body;
        switch (httpMediaType) {
            case JSON:
                body = httpRequest.getJsonParser().toJsonStr(httpBody.getData());
                return RequestBody.create(body, mediaType);
            case URL_ENCODED:
                UrlQuery urlQuery = new UrlQuery();
                httpRequest.getHttpBody().toQueryMap().forEach((k, v) -> urlQuery.add(StrUtil.toString(k), StrUtil.toString(v)));
                body = urlQuery.build(httpRequest.getCharset());
                return RequestBody.create(body, mediaType);
            case XML:
                body = XML.toXml(httpBody.getData());
                return RequestBody.create(body, mediaType);
            case MULTIPART:
                Object data = httpBody.getData();
                if (data instanceof byte[]) {
                    return RequestBody.create((byte[]) data, mediaType);
                } else if (data instanceof File) {
                    return RequestBody.create((File) data, mediaType);
                }
            default:
                throw new UnsupportedException(StrUtil.format("unsupported mediaType: ", httpMediaType));
        }
    }
}
