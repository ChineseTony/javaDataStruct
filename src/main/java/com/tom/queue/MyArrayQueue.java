package com.tom.queue;

import com.tom.array.MyArray;

/**
 * @author WangTao
 */
public class MyArrayQueue<T> implements MyQueue<T> {

    private MyArray<T> myArray;

    public MyArrayQueue(int capacity){
        myArray = new MyArray<>(capacity);
    }

    public MyArrayQueue(){
        myArray = new MyArray<>();
    }

    @Override
    public void enque(T t) {
        myArray.addLast(t);
    }

    @Override
    public T deque() {
        return myArray.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return myArray.isEmpty();
    }

    @Override
    public int getSize() {
        return myArray.getSize();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyArrayQueue<>();
        queue.enque(1);
        queue.enque(2);
        queue.enque(3);
        queue.enque(4);
        while (!queue.isEmpty()){
            System.out.print(queue.deque()+" ");
        }
        System.out.println();
    }
}
