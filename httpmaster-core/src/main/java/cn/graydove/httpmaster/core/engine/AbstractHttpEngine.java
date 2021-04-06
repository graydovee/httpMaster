package cn.graydove.httpmaster.core.engine;

import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.response.HttpResponse;

/**
 * 引擎抽象类，必须选择以下一种方式重写
 *  1.分开重写所有get,post等方法
 *  2.单独重写request方法
 * @author graydove
 */
public abstract class AbstractHttpEngine implements HttpEngine {

    @Override
    public HttpResponse get(HttpRequest httpRequest) {
        return execute(HttpMethod.GET, httpRequest);
    }

    @Override
    public HttpResponse post(HttpRequest httpRequest) {
        return execute(HttpMethod.POST, httpRequest);
    }

    @Override
    public HttpResponse put(HttpRequest httpRequest) {
        return execute(HttpMethod.PUT, httpRequest);
    }

    @Override
    public HttpResponse delete(HttpRequest httpRequest) {
        return execute(HttpMethod.DELETE, httpRequest);
    }

    @Override
    public HttpResponse options(HttpRequest httpRequest) {
        return execute(HttpMethod.OPTIONS, httpRequest);
    }

    @Override
    public HttpResponse patch(HttpRequest httpRequest) {
        return execute(HttpMethod.PATCH, httpRequest);
    }

    @Override
    public HttpResponse head(HttpRequest httpRequest) {
        return execute(HttpMethod.HEAD, httpRequest);
    }

    @Override
    public HttpResponse trace(HttpRequest httpRequest) {
        return execute(HttpMethod.TRACE, httpRequest);
    }

    @Override
    public HttpResponse execute(HttpMethod httpMethod, HttpRequest httpRequest) {
        if (null == httpMethod) {
            throw new NullPointerException("httpMethod must not be null");
        }
        switch (httpMethod) {
            case POST:
                return post(httpRequest);
            case GET:
                return get(httpRequest);
            case PUT:
                return put(httpRequest);
            case DELETE:
                return delete(httpRequest);
            case OPTIONS:
                return options(httpRequest);
            default:
                throw new UnsupportedException("unsupported httpMethod");
        }
    }
}
