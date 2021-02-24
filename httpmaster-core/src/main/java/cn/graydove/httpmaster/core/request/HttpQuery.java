package cn.graydove.httpmaster.core.request;

import cn.graydove.httpmaster.core.common.MapConsumer;

import java.util.Map;

public interface HttpQuery extends HttpParam {

    HttpQuery addQuery(String key, Object value);

    HttpQuery addQueries(Map<?, ?> params);

    HttpQuery addQueries(HttpQuery params);

    Map<String, Object> asMap();

    void forEach(MapConsumer<String, Object> mapConsumer);
}
