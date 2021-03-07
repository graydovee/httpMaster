package cn.graydove.httpmaster.starter.bean;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderBeanRegister<T> {

    private final List<T> data;

    private boolean isNotSorted;

    public OrderBeanRegister(Collection<T> data) {
        this.data = new ArrayList<>(data);
        this.isNotSorted = true;
    }

    public OrderBeanRegister() {
        this.data = new ArrayList<>();
        this.isNotSorted = true;
    }

    public void register(T element) {
        synchronized (data) {
            data.add(element);
            isNotSorted = true;
        }
    }

    public List<T> getData() {
        if (isNotSorted) {
            synchronized (data) {
                if (isNotSorted) {
                    data.sort(AnnotationAwareOrderComparator.INSTANCE);
                    isNotSorted = true;
                }
            }
        }
        return data;
    }
}
