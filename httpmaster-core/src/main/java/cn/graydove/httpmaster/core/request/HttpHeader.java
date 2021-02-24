package cn.graydove.httpmaster.core.request;

import cn.graydove.httpmaster.core.common.MapConsumer;
import cn.graydove.httpmaster.core.request.support.DefaultHttpHeader;

import java.util.Map;

public interface HttpHeader extends HttpParam {

    HttpHeader addHeader(String key, String value);

    HttpHeader addHeaders(Map<String, String> headers);

    HttpHeader addHeaders(HttpHeader headers);

    Map<String, String> asMap();

    void forEach(MapConsumer<String, String> consumer);
}
