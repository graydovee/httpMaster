package cn.graydove.httpmaster.core.request;

import cn.graydove.httpmaster.core.common.KVConsumer;

import java.util.Map;

/**
 * @author graydove
 */
public interface HttpQuery extends HttpParam {

    HttpQuery addQuery(String key, Object value);

    HttpQuery addQueries(Map<?, ?> params);

    HttpQuery addQueries(HttpQuery params);

    Map<String, Object> asMap();

    void forEach(KVConsumer<String, Object> KVConsumer);
}
