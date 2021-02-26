package cn.graydove.httpmaster.core.common;

import cn.hutool.core.lang.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class KVList<K,V> implements List<Pair<K,V>> {

    private final List<Pair<K,V>> list;

    public KVList() {
        this.list = new ArrayList<>();
    }

    public KVList(List<Pair<K, V>> list) {
        this.list = list;
    }

    public void add(K k, V v) {
        add(Pair.of(k,v));
    }

    public void forEach(KVConsumer<K, V> action) {
        list.forEach(pair -> action.apply(pair.getKey(), pair.getValue()));
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @NotNull
    @Override
    public Iterator<Pair<K, V>> iterator() {
        return list.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Pair<K, V> kvPair) {
        return list.add(kvPair);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Pair<K, V>> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Pair<K, V>> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Pair<K, V> get(int index) {
        return list.get(index);
    }

    @Override
    public Pair<K, V> set(int index, Pair<K, V> element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Pair<K, V> element) {
        list.add(index, element);
    }

    @Override
    public Pair<K, V> remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<Pair<K, V>> listIterator() {
        return list.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<Pair<K, V>> listIterator(int index) {
        return list.listIterator();
    }

    @NotNull
    @Override
    public List<Pair<K, V>> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }
}
