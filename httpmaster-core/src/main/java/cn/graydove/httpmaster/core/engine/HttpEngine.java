package cn.graydove.httpmaster.core.engine;

import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.response.HttpResponse;

public interface HttpEngine {

    HttpResponse get(HttpRequest httpRequest);

    HttpResponse post(HttpRequest httpRequest);

    HttpResponse put(HttpRequest httpRequest);

    HttpResponse delete(HttpRequest httpRequest);

    HttpResponse options(HttpRequest httpRequest);

    HttpResponse patch(HttpRequest httpRequest);

    HttpResponse head(HttpRequest httpRequest);

    HttpResponse trace(HttpRequest httpRequest);

    HttpResponse request(HttpMethod httpMethod, HttpRequest httpRequest);

    HttpRequestFactory getHttpRequestFactory();

    default HttpRequest newHttpRequest() {
        return getHttpRequestFactory().newHttpRequest();
    }
}
