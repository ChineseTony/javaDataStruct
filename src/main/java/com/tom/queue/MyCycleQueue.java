package com.tom.queue;



/**
 * @author WangTao
 *
 * 循环数组
 * 牺牲一个存储空间 用于区分 队空还是队满
 */
public class MyCycleQueue<T> implements MyQueue<T> {

    private T[] t;

    //队首
    private int front;

    //队尾
    private int rear;

    private int size;

    private final int DEFAULT_CAPACITY = 9;


    @SuppressWarnings("unchecked")
    public MyCycleQueue(int capacity){
        t = (T[]) new Object[capacity+1];
    }

    @SuppressWarnings("unchecked")
    public MyCycleQueue(){
        t = (T[]) new Object[DEFAULT_CAPACITY+1];
    }



    @Override
    public void enqueue(T e) {
        //队满
        if ((rear+1) % t.length == front){
            resize(getCapacity() * 2);
        }
        t[rear] = e;
        rear = (rear + 1) % t.length;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        T e = t[front];
        t[front] = null;
        front = (front + 1) % t.length;
        size--;
       /* if ((rear - front + t.length) % t.length == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }*/
        if( size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return e;

    }

    @Override
    public T getFront() {
        if (isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        return t[front];
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public int getSize() {
        return size;
        //return (rear - front + t.length) % t.length;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity){
        T[] newData = (T[])new Object[capacity+1];
        //int size = (rear - front + t.length) % t.length;
        for (int i = 0; i < size; i++) {
            newData[i] = t[(i + front) % t.length];
        }
        t = newData;
        front = 0;
        rear = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: font ");
        sb.append("[");
        for (int i = front; i != rear; i = (i+1)%t.length) {
            sb.append(t[i]);
            if ( (i+1) % t.length != rear){
                sb.append(", ");
            }
        }
        sb.append("] rear");
        return sb.toString();
    }

    public int getCapacity(){
        return  t.length - 1;
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyCycleQueue<>(2);
        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        while (!queue.isEmpty()){
            System.out.print(queue.dequeue()+" ");
        }
        System.out.println();
    }
}
