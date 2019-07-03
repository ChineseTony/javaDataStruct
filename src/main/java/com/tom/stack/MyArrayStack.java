package com.tom.stack;

import com.tom.array.MyArray;

/**
 * @author WangTao
 */
public class MyArrayStack<T> implements MyStack<T> {


    private MyArray<T> myArray;

    public MyArrayStack(int capacity){
        myArray = new MyArray<>(capacity);
    }

    public MyArrayStack(){
        myArray = new MyArray<>();
    }

    @Override
    public void push(T t) {
        myArray.addLast(t);
    }

    @Override
    public T pop() {
        return myArray.removeLast();
    }

    @Override
    public T peek() {
        return myArray.getLast();
    }

    @Override
    public boolean isEmpty() {
        return myArray.isEmpty();
    }

    @Override
    public int getSize() {
        return myArray.getSize();
    }

    public int getCapacity(){
        return myArray.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack:");
        sb.append("[");
        int size = myArray.getSize();
        for (int i = 0; i < size; i++) {
            sb.append(myArray.get(i));
            if (i != size -1){
                sb.append(", ");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
