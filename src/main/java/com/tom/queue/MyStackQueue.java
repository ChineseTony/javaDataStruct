package com.tom.queue;

import java.util.Stack;

/**
 * @author WangTao
 * 用栈实现队列
 */
public class MyStackQueue<T> implements MyQueue<T> {

    private Stack<T> stack1;
    private Stack<T> stack2;

    public MyStackQueue(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();

    }


    @Override
    public void enqueue(T t) {
        stack1.push(t);
    }

    @Override
    public T dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.size() == 0){
            throw new RuntimeException("Queue is empty");
        }
        return stack2.pop();
    }

    @Override
    public T getFront() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.size() == 0){
            throw new RuntimeException("Queue is empty");
        }
        return stack2.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    @Override
    public int getSize() {
        return stack1.size() + stack2.size();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyStackQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.dequeue();
        queue.enqueue(5);
        System.out.println(queue.getFront());
        System.out.println(queue.getSize());
        while (!queue.isEmpty()){
            System.out.print(queue.dequeue()+" ");
        }
    }

}
