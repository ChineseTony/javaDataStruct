package com.tom.stack;

/**
 * @author WangTao
 */
public class StackTest {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyArrayStack<>();
        for (int i = 0; i < 5; i++) {
            myStack.push(i);
        }
        System.out.println(myStack);
        myStack.pop();
        System.out.println(myStack);
        System.out.println(myStack.peek());
    }
}
