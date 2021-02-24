package cn.graydove.httpmaster.core.request.support;

import java.util.Map;

public interface MapFactory<K, V> {

    default Map<K, V> newMap(Map<? extends K, ? extends V> map) {
        Map<K, V> kvMap = newMap();
        kvMap.putAll(map);
        return kvMap;
    }

    Map<K, V> newMap();
}
