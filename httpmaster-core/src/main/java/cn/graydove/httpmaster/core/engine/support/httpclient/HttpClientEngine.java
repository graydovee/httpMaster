package cn.graydove.httpmaster.core.engine.support.httpclient;

import cn.graydove.httpmaster.core.engine.AbstractHttpEngine;
import cn.graydove.httpmaster.core.enums.HttpMediaType;
import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpBody;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.request.support.DefaultHttpRequestFactory;
import cn.graydove.httpmaster.core.response.support.DefaultHttpContent;
import cn.graydove.httpmaster.core.response.support.DefaultHttpResponse;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpClientEngine extends AbstractHttpEngine {

    private CloseableHttpClient client;

    public HttpClientEngine(HttpRequestFactory httpRequestFactory, CloseableHttpClient client) {
        super(httpRequestFactory);
        this.client = client;
    }

    public HttpClientEngine(HttpRequestFactory httpRequestFactory, HttpClientFactory httpClientFactory) {
        super(httpRequestFactory);
        this.client = httpClientFactory.newClient();
    }

    public HttpClientEngine(HttpRequestFactory httpRequestFactory) {
        this(httpRequestFactory, new DefaultHttpClientFactory());
    }

    public HttpClientEngine() {
        this(new DefaultHttpRequestFactory(), new DefaultHttpClientFactory());
    }

    @Override
    public DefaultHttpResponse request(HttpMethod httpMethod, HttpRequest httpRequest) {
        HttpRequestBase requestBase = toRequest(httpMethod, httpRequest);
        return execute(requestBase);
    }

    private HttpRequestBase toRequest(HttpMethod httpMethod, HttpRequest httpRequest) {
        HttpRequestBase request = resolveRequest(httpMethod, httpRequest.url().getFullUrl());
        resolveHeader(request, httpRequest);
        resolveBody(request, httpRequest);
        return request;
    }

    private void resolveBody(HttpRequestBase request, HttpRequest httpRequest) {
        if (null == httpRequest.getHttpBody()) {
            return;
        }
        if (request instanceof HttpEntityEnclosingRequestBase) {
            ((HttpEntityEnclosingRequestBase) request).setEntity(buildHttpEntity(httpRequest));
        }
    }

    private void resolveHeader(HttpRequestBase request, HttpRequest httpRequest) {
        if (null == httpRequest.getHttpHeader()) {
            return;
        }
        httpRequest.getHttpHeader().forEach(request::setHeader);
    }

    private HttpRequestBase resolveRequest(HttpMethod httpMethod, String url) {
        switch (httpMethod) {
            case GET:
                return new HttpGet(url);
            case DELETE:
                return new HttpDelete(url);
            case HEAD:
                return new HttpHead(url);
            case OPTIONS:
                return new HttpOptions(url);
            case TRACE:
                return new HttpTrace(url);
            case POST:
                return new HttpPost(url);
            case PUT:
                return new HttpPut(url);
            case PATCH:
                return new HttpPatch(url);
            default:
                throw new UnsupportedException();
        }
    }

    private HttpEntity buildHttpEntity(HttpRequest httpRequest) {
        HttpBody httpBody = httpRequest.getHttpBody();
        if (null == httpBody) {
            return null;
        }
        HttpMediaType httpMediaType;
        try {
            httpMediaType = HttpMediaType.toHttpMediaType(httpRequest);
        } catch (UnsupportedException e) {
            httpMediaType = HttpMediaType.URL_ENCODED;
        }
        switch (httpMediaType) {
            case URL_ENCODED:
                Map<Object, Object> map = httpRequest.getHttpBody().toQueryMap();
                List<NameValuePair> nameValuePairList = map.entrySet().stream()
                        .filter(entry -> null != entry.getKey() && null != entry.getValue())
                        .map(entry -> new BasicNameValuePair(StrUtil.toString(entry.getKey()), StrUtil.toString(entry.getValue())))
                        .collect(Collectors.toList());
                try {
                    return new UrlEncodedFormEntity(nameValuePairList);
                } catch (UnsupportedEncodingException e) {
                    throw new UnsupportedException(e);
                }
            case JSON:
                try {
                    return new StringEntity(JSONUtil.toJsonStr(httpRequest.getHttpBody().getData()));
                } catch (UnsupportedEncodingException e) {
                    throw new UnsupportedException(e);
                }
            default:
        }
        return null;
    }

    private DefaultHttpResponse execute(HttpRequestBase httpRequestBase) {
        try (CloseableHttpResponse response = client.execute(httpRequestBase)) {
            DefaultHttpResponse httpResponse = new DefaultHttpResponse();

            httpResponse.setStatusCode(response.getStatusLine().getStatusCode());

            HttpEntity entity = response.getEntity();
            DefaultHttpContent content = new DefaultHttpContent();
            Charset charset = Optional.ofNullable(entity.getContentEncoding())
                    .map(NameValuePair::getValue)
                    .map(Charset::forName)
                    .orElse(null);
            content.setEncodeType(charset);
            content.setBytes(EntityUtils.toByteArray(entity));
            content.setLength(entity.getContentLength());
            httpResponse.setHttpContent(content);

            List<Pair<String, String>> headList = Arrays.stream(response.getAllHeaders())
                    .map(pair -> Pair.of(pair.getName(), pair.getValue()))
                    .collect(Collectors.toList());
            httpResponse.setHead(headList);

            return httpResponse;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }
}