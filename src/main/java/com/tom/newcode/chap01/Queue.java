package com.tom.newcode.chap01;

import java.util.Stack;

/**
 * @author WangTao
 */
public class Queue {

    private Stack stack1;
    private Stack stack2;

    public Queue(){
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void enqueue(Object o){
        stack1.push(o);
    }

    public Object dequeue(){
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        if (stack2.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return stack2.pop();
    }

    public boolean isEmpty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        final int n = 10;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        while (!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }
    }
}
