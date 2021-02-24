package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.common.MapConsumer;
import cn.graydove.httpmaster.core.request.HttpQuery;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.hutool.core.collection.CollectionUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultHttpQuery extends AbstractHttpParam implements HttpQuery {

    private Map<String, Object> params;

    public DefaultHttpQuery(HttpRequest request) {
        super(request);
        this.params = new HashMap<>();
    }

    @Override
    public DefaultHttpQuery addQuery(String key, Object value) {
        params.put(key, value);
        return this;
    }

    @Override
    public DefaultHttpQuery addQueries(Map<?, ?> params) {
        for (Map.Entry<?, ?> entry : params.entrySet()) {
            this.params.put(entry.getKey().toString(), entry.getValue());
        }
        return this;
    }

    @Override
    public DefaultHttpQuery addQueries(HttpQuery params) {
        this.params.putAll(params.asMap());
        return this;
    }

    @Override
    public Map<String, Object> asMap() {
        if (CollectionUtil.isEmpty(params)) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(params);
    }

    @Override
    public void forEach(MapConsumer<String, Object> mapConsumer) {
        if (CollectionUtil.isEmpty(params)) {
            return;
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            mapConsumer.apply(entry.getKey(), entry.getValue());
        }
    }
}
