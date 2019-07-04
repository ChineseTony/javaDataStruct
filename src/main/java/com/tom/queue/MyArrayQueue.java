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
    public boolean isEmpty() {
        return myArray.isEmpty();
    }

    @Override
    public int getSize() {
        return myArray.getSize();
    }

    @Override
    public void enqueue(T t) {
        myArray.addLast(t);
    }

    @Override
    public T dequeue() {
        return myArray.removeFirst();
    }

    @Override
    public T getFront() {
        return myArray.get(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: font ");
        sb.append("[");
        int size = myArray.getSize();
        for (int i = 0; i < size; i++) {
            sb.append(myArray.get(i));
            if (i != size -1){
                sb.append(", ");
            }
        }
        sb.append("] rear");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        while (!queue.isEmpty()){
            System.out.print(queue.dequeue()+" ");
        }
        System.out.println();
    }
}
