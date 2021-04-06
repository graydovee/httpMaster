package cn.graydove.httpmaster.core.request.support;


import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.request.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author graydove
 */
public class DefaultHttpRequest implements HttpRequest {

    public final static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final HttpRequestFactory httpRequestFactory;

    private HttpHeader httpHeader;

    private HttpQuery httpQuery;

    private HttpBody httpBody;

    private HttpUrl httpUrl;

    private HttpMethod httpMethod;

    private Charset charset;

    public DefaultHttpRequest(HttpRequestFactory httpRequestFactory, Charset charset) {
        this(httpRequestFactory);
        this.charset = charset;
    }

    public DefaultHttpRequest(HttpRequestFactory httpRequestFactory, String url, Charset charset) {
        this(httpRequestFactory);
        this.charset = charset;
        this.httpUrl = new DefaultHttpUrl(this);
        this.httpUrl.of(url);
    }

    public DefaultHttpRequest(HttpRequestFactory httpRequestFactory, String url) {
        this(httpRequestFactory);
        this.httpUrl = new DefaultHttpUrl(this);
        this.httpUrl.of(url);
    }

    public DefaultHttpRequest(HttpRequestFactory httpRequestFactory) {
        this.httpRequestFactory = httpRequestFactory;
    }

    @Override
    public Charset getCharset() {
        if (null == charset) {
            return DEFAULT_CHARSET;
        }
        return charset;
    }

    @Override
    public HttpRequestFactory getHttpRequestFactory() {
        return httpRequestFactory;
    }

    @Override
    public HttpHeader getHttpHeader() {
        return httpHeader;
    }

    @Override
    public HttpQuery getHttpQuery() {
        return httpQuery;
    }

    @Override
    public HttpBody getHttpBody() {
        return httpBody;
    }

    @Override
    public HttpUrl getHttpUrl() {
        return httpUrl;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    @Override
    public void setHttpHeader(HttpHeader httpHeader) {
        this.httpHeader = httpHeader;
    }

    @Override
    public void setHttpQuery(HttpQuery httpQuery) {
        this.httpQuery = httpQuery;
    }

    @Override
    public void setHttpBody(HttpBody httpBody) {
        this.httpBody = httpBody;
    }

    @Override
    public void setHttpUrl(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Override
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
}
