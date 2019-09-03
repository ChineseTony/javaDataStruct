package com.tom.stack;

import java.util.Stack;

/**
 * @author WangTao
 */
public class SortByStack {

    public static void sort(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()){
            int temp = stack.pop();
            while (!help.isEmpty() && help.peek() > temp){
                stack.push(help.pop());
            }
            help.push(temp);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(3);
        stack.push(-1);
        stack.push(1);
        SortByStack.sort(stack);
        for (Integer i : stack){
            System.out.println(i);
        }
    }
}
