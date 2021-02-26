package cn.graydove.httpmaster.core.request;

import cn.graydove.httpmaster.core.common.KVConsumer;

import java.util.Map;

public interface HttpHeader extends HttpParam {

    HttpHeader addHeader(String key, String value);

    HttpHeader addHeaders(Map<String, String> headers);

    HttpHeader addHeaders(HttpHeader headers);

    Map<String, String> asMap();

    void forEach(KVConsumer<String, String> consumer);
}
