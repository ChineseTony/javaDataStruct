package com.tom.stack;

/**
 * @author WangTao
 */
public interface MyStack<T> {

    void push(T t);

    T pop();

    //获取栈顶元素操作
    T peek();

    boolean isEmpty();

    int getSize();
}
