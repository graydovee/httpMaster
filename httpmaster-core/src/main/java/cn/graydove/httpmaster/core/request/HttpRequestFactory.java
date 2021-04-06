package cn.graydove.httpmaster.core.request;

import java.nio.charset.Charset;

/**
 * @author graydove
 */
public interface HttpRequestFactory {

    HttpRequest newHttpRequest();

    HttpRequest newHttpRequest(Charset charset);

    HttpHeader newHttpHeader(HttpRequest httpRequest);

    HttpQuery newHttpQuery(HttpRequest httpRequest);

    HttpBody newHttpBody(HttpRequest httpRequest);

    KeyValueHttpBody newKeyValueHttpBody(HttpRequest httpRequest);

    HttpUrl newHttpUrl(HttpRequest httpRequest);

    void setDefaultCharset(Charset charset);
}
