package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.request.KeyValueHttpBody;

import java.util.Map;

/**
 * @author graydove
 */
public class MapKeyValueHttpBody extends AbstractHttpParam implements KeyValueHttpBody {

    private Map<Object, Object> data;

    private MapFactory<Object, Object> mapFactory;

    public MapKeyValueHttpBody(HttpRequest httpRequest) {
        this(httpRequest, new HashMapFactory<>());
    }

    public MapKeyValueHttpBody(HttpRequest httpRequest, MapFactory<Object, Object> mapFactory) {
        super(httpRequest);
        this.data = mapFactory.newMap();
        this.mapFactory = mapFactory;
    }

    @Override
    public KeyValueHttpBody addParam(Object key, Object value) {
        data.put(key, value);
        return this;
    }

    @Override
    public KeyValueHttpBody data(Object data) {
        if (data instanceof Map) {
            this.data = mapFactory.newMap((Map<?, ?>) data);
            return this;
        }
        throw new UnsupportedException("data can not cast to Map");
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public Map<Object, Object> toQueryMap() {
        return mapFactory.newMap(data);
    }

    public void setMapFactory(MapFactory<Object, Object> mapFactory) {
        this.mapFactory = mapFactory;
    }
}
