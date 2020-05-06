package com.tom.oj;

import java.util.Stack;

/**
 * @author WangTao
 */
public class MinStack<T extends Comparable> {

    private Stack<T> stack;
    private Stack<T> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(T x) {
        stack.push(x);
        if (minStack.isEmpty() || x.compareTo(minStack.peek()) <= 0){
            minStack.push(x);
        }

    }

    public void pop() {
        T x = stack.peek();
        if (x == minStack.peek()){
            minStack.pop();
        }
        stack.pop();

    }

    public T top() {
        if (isEmpty()){
            return null;
        }
        return stack.peek();
    }

    public T getMin() {
        return minStack.peek();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
