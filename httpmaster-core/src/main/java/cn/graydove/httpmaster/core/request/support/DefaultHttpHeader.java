package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.common.KVConsumer;
import cn.graydove.httpmaster.core.request.HttpHeader;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.hutool.core.collection.CollectionUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author graydove
 */
public class DefaultHttpHeader extends AbstractHttpParam implements HttpHeader {

    private Map<String, String> headers;

    public DefaultHttpHeader(HttpRequest request) {
        super(request);
        headers = new HashMap<>();
    }

    @Override
    public DefaultHttpHeader addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public DefaultHttpHeader addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    @Override
    public DefaultHttpHeader addHeaders(HttpHeader headers) {
        this.headers.putAll(headers.asMap());
        return this;
    }

    @Override
    public Map<String, String> asMap() {
        if (CollectionUtil.isEmpty(headers)) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(headers);
    }


    @Override
    public void forEach(KVConsumer<String, String> consumer) {
        if (CollectionUtil.isEmpty(headers)) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            consumer.apply(entry.getKey(), entry.getValue());
        }
    }

}
