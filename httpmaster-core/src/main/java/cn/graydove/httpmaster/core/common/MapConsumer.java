package cn.graydove.httpmaster.core.common;

@FunctionalInterface
public interface MapConsumer<K, V> {

    void apply(K s, V o);
}
