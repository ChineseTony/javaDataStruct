package com.tom.stack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author WangTao
 */
public class MyQueueStack<T> implements MyStack<T> {

    private Queue<T> q1;

    private Queue<T> q2;

    public  MyQueueStack(){
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();

    }

    @Override
    public void push(T t) {
        q1.offer(t);
    }

    @Override
    public T pop() {
        T t = null;
        if (isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        t = q1.poll();
        while (!q2.isEmpty()){
            q1.offer(q2.poll());
        }
        return t;
    }

    @Override
    public T peek() {
        T t = null;
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        return q1.peek();
    }

    @Override
    public boolean isEmpty() {
        return q1.isEmpty() ;
    }

    @Override
    public int getSize() {
        return q1.size() ;
    }

}
