package cn.graydove.httpmaster.core.request;

import cn.graydove.httpmaster.core.exception.UnsupportedException;

import java.nio.charset.Charset;
import java.util.Map;

public interface HttpRequest {

    HttpRequestFactory getHttpRequestFactory();

    default HttpHeader header() {
        HttpHeader httpHeader = getHttpHeader();
        if (null == httpHeader) {
            httpHeader = getHttpRequestFactory().newHttpHeader(this);
            setHttpHeader(httpHeader);
        }
        return httpHeader;
    }

    default HttpQuery query() {
        HttpQuery httpQuery = getHttpQuery();
        if (null == httpQuery) {
            httpQuery = getHttpRequestFactory().newHttpQuery(this);
            setHttpQuery(httpQuery);
        }
        return httpQuery;
    }

    default HttpBody body() {
        HttpBody httpBody = getHttpBody();
        if (null == httpBody) {
            httpBody = getHttpRequestFactory().newHttpBody(this);
            setHttpBody(httpBody);
        }
        return httpBody;
    }

    default KeyValueHttpBody keyValueBody() {
        HttpBody httpBody = getHttpBody();
        if (httpBody instanceof KeyValueHttpBody) {
            return (KeyValueHttpBody) httpBody;
        }
        KeyValueHttpBody keyValueHttpBody = getHttpRequestFactory().newKeyValueHttpBody(this);
        if (null != httpBody) {
            Map<Object, Object> map = httpBody.toQueryMap();
            keyValueHttpBody.data(map);
        }
        setHttpBody(keyValueHttpBody);
        return keyValueHttpBody;
    }

    default HttpUrl url() {
        HttpUrl httpUrl = getHttpUrl();
        if (null == httpUrl) {
            httpUrl = getHttpRequestFactory().newHttpUrl(this);
            setHttpUrl(httpUrl);
        }
        return httpUrl;
    }

    default HttpRequest url(String url) {
        url().of(url);
        return this;
    }

    HttpHeader getHttpHeader();

    HttpBody getHttpBody();

    HttpQuery getHttpQuery();

    HttpUrl getHttpUrl();

    Charset getCharset();

    void setHttpHeader(HttpHeader httpHeader);

    void setHttpBody(HttpBody httpBody);

    void setHttpQuery(HttpQuery httpQuery);

    void setHttpUrl(HttpUrl httpUrl);
}
