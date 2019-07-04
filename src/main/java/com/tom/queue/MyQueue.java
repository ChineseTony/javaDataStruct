package com.tom.queue;

/**
 * @author WangTao
 */
public interface MyQueue<T> {

    void enque(T t);

    T deque();

    boolean isEmpty();

    int getSize();
}
