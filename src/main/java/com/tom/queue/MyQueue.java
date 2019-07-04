package com.tom.queue;

/**
 * @author WangTao
 */
public interface MyQueue<T> {

    void enqueue(T t);

    T dequeue();


    T getFront();

    boolean isEmpty();

    int getSize();
}
