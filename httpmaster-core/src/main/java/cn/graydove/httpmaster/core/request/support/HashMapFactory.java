package cn.graydove.httpmaster.core.request.support;

import java.util.HashMap;
import java.util.Map;

public class HashMapFactory<K, V> implements MapFactory<K, V> {

    @Override
    public Map<K, V> newMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    @Override
    public Map<K, V> newMap() {
        return new HashMap<>();
    }
}
