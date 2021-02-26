package cn.graydove.httpmaster.core.common;

@FunctionalInterface
public interface KVConsumer<K, V> {

    void apply(K s, V o);
}
