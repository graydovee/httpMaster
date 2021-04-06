package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.request.HttpBody;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.hutool.core.bean.BeanUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author graydove
 */
public class DefaultHttpBody extends AbstractHttpParam implements HttpBody {

    private Object data;

    public DefaultHttpBody(HttpRequest httpRequest) {
        super(httpRequest);
    }

    @Override
    public DefaultHttpBody data(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public Map<Object, Object> toQueryMap() {
        return toQueryMap(data);
    }

    @SuppressWarnings("unchecked")
    private static Map<Object, Object> toQueryMap(Object data) {
        if (data instanceof Map) {
            return Collections.unmodifiableMap((Map) data);
        }

        if (data instanceof Iterable<?>) {
            Map<Object, Object> map = new HashMap<>();
            for (Object o : ((Iterable) data)) {
                map.putAll(toQueryMap(o));
            }
            return Collections.unmodifiableMap(map);
        }

        Map<Object, Object> map = new HashMap<>();
        for (Map.Entry<String, PropertyDescriptor> entry : BeanUtil.getPropertyDescriptorMap(data.getClass(), false).entrySet()) {
            String name = entry.getKey();
            Method readMethod = entry.getValue().getReadMethod();
            try {
                Object invoke = readMethod.invoke(data);
                map.put(name, invoke);
            } catch (IllegalAccessException | InvocationTargetException ignored) {
            }
        }
        return Collections.unmodifiableMap(map);
    }

}
