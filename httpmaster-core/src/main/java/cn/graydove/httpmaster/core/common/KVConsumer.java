package cn.graydove.httpmaster.core.common;

/**
 * @author graydove
 */
@FunctionalInterface
public interface KVConsumer<K, V> {

    void apply(K s, V o);
}
