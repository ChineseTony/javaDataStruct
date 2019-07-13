package com.tom.set;

/**
 * @author WangTao
 */
public interface MySet<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();


}
