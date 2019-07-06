package com.tom.stack;

import com.tom.list.MyLinkedList;

/**
 * @author WangTao
 */
public class MyLinkedStack<T> implements MyStack<T> {

    private MyLinkedList<T> linkedList;

    public MyLinkedStack() {
        linkedList = new MyLinkedList<>();
    }

    @Override
    public void push(T t) {
        linkedList.addFirst(t);
    }

    @Override
    public T pop() {
        return linkedList.removeFirst();
    }

    @Override
    public T peek() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top[");
        sb.append(linkedList);
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyLinkedStack<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack);
        while (!stack.isEmpty()){
            stack.pop();
            System.out.println(stack);
        }
    }
}
