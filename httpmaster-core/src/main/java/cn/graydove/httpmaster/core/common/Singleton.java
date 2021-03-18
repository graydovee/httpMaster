package cn.graydove.httpmaster.core.common;

import cn.hutool.core.lang.Assert;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Singleton<T> {

    private T data;

    private Supplier<T> supplier;

    public Singleton() {
    }

    public Singleton(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        return get(supplier);
    }

    public T get(Supplier<T> getter) {
        if (null == data) {
            Assert.notNull(getter);
            synchronized (this) {
                if (null == data) {
                    data = getter.get();
                }
            }
        }
        return data;
    }

    public void set(T data) {
        this.data = data;
    }

    public static <T> Singleton<T> of(Supplier<T> supplier) {
        return new Singleton<>(supplier);
    }

    public <R> R safeOperate(Function<T, R> function) {
        synchronized (this) {
            return function.apply(data);
        }
    }
}
