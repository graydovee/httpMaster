package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.request.*;
import cn.graydove.httpmaster.core.request.*;

import java.nio.charset.Charset;

public class DefaultHttpRequestFactory implements HttpRequestFactory {

    private Charset defaultCharset;

    @Override
    public HttpRequest newHttpRequest() {
        if (null == defaultCharset) {
            return new DefaultHttpRequest(this);
        }
        return newHttpRequest(defaultCharset);
    }

    @Override
    public HttpRequest newHttpRequest(Charset charset) {
        return new DefaultHttpRequest(this, charset);
    }

    @Override
    public HttpHeader newHttpHeader(HttpRequest httpRequest) {
        return new DefaultHttpHeader(httpRequest);
    }

    @Override
    public HttpQuery newHttpQuery(HttpRequest httpRequest) {
        return new DefaultHttpQuery(httpRequest);
    }

    @Override
    public HttpBody newHttpBody(HttpRequest httpRequest) {
        return new DefaultHttpBody(httpRequest);
    }

    @Override
    public KeyValueHttpBody newKeyValueHttpBody(HttpRequest httpRequest) {
        return new MapKeyValueHttpBody(httpRequest, new HashMapFactory<>());
    }

    @Override
    public HttpUrl newHttpUrl(HttpRequest httpRequest) {
        return new DefaultHttpUrl(httpRequest);
    }

    @Override
    public void setDefaultCharset(Charset charset) {
        this.defaultCharset = charset;
    }
}
