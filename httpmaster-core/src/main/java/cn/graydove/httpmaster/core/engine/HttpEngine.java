package cn.graydove.httpmaster.core.engine;

import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.core.lang.Assert;

/**
 * @author graydove
 */
public interface HttpEngine {

    HttpResponse get(HttpRequest httpRequest);

    HttpResponse post(HttpRequest httpRequest);

    HttpResponse put(HttpRequest httpRequest);

    HttpResponse delete(HttpRequest httpRequest);

    HttpResponse options(HttpRequest httpRequest);

    HttpResponse patch(HttpRequest httpRequest);

    HttpResponse head(HttpRequest httpRequest);

    HttpResponse trace(HttpRequest httpRequest);

    HttpResponse execute(HttpMethod httpMethod, HttpRequest httpRequest);

    default HttpResponse execute(HttpRequest httpRequest) {
        HttpMethod httpMethod = httpRequest.getHttpMethod();
        Assert.notNull(httpMethod, "http method is null");
        return execute(httpMethod, httpRequest);
    }
}
