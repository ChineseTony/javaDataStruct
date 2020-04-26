package com.tom.oj;

import java.util.Stack;

/**
 * @author WangTao
 */
public class CQueue<T> {

    private Stack<T> stack1;
    private Stack<T> stack2;

    public CQueue() {
        stack1= new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(T value) {
        stack1.push(value);
    }

    public T deleteHead() {
        if (isEmpty()){
            return null;
        }
        if (!stack2.isEmpty()){
            return stack2.pop();
        }else {
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

    }

    public boolean isEmpty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }



    public static void main(String[] args) {
        CQueue<Integer> queue = new CQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.appendTail(i);
        }
        while (!queue.isEmpty()){
            System.out.println(queue.deleteHead());
        }
    }
}
