package com.tom.queue;


import com.tom.heap.MyMaxHeap;

/**
 * @author WangTao
 * 基于最大堆的优先队列
 */
public class MyPriorityQueue<T extends Comparable> implements MyQueue<T> {

    private MyMaxHeap<T> myMaxHeap;

    public MyPriorityQueue(){
        myMaxHeap = new MyMaxHeap<>();
    }

    @Override
    public void enqueue(T t) {
        myMaxHeap.insert(t);
    }

    @Override
    public T dequeue() {
        return myMaxHeap.removeMax();
    }

    @Override
    public T getFront() {
        return myMaxHeap.getMax();
    }

    @Override
    public boolean isEmpty() {
        return myMaxHeap.isEmpty();
    }

    @Override
    public int getSize() {
        return myMaxHeap.getLength();
    }
}
