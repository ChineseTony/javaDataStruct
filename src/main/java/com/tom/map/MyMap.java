package com.tom.map;

/**
 * @author WangTao
 */
public interface MyMap<K,V> {

    void add(K  key,V value);

    V remove(K key);

    boolean contains(K key);

    int getSize();

    boolean isEmpty();

    void set(K key,V value);

    V get(K key);

}
