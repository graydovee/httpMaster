package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.request.HttpParam;
import cn.graydove.httpmaster.core.request.HttpRequest;

public abstract class AbstractHttpParam implements HttpParam {

    protected HttpRequest httpRequest;

    protected AbstractHttpParam(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public HttpRequest build() {
        return this.httpRequest;
    }
}
