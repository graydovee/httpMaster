package cn.graydove.httpmaster.core.common;

import java.util.function.Supplier;

public class Singleton<T> {

    private T data;

    private Supplier<T> supplier;

    public Singleton(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (null == data) {
            synchronized (this) {
                if (null == data) {
                    data = supplier.get();
                }
            }
        }
        return data;
    }

    public static <T> Singleton<T> of(Supplier<T> supplier) {
        return new Singleton<>(supplier);
    }
}
