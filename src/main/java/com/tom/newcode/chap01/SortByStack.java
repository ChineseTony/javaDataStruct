package com.tom.newcode.chap01;

import java.util.Stack;

/**
 * @author WangTao
 */
public class SortByStack<T extends Comparable> {


    @SuppressWarnings("unchecked")
    public  void sort(Stack<T> stack){
        Stack<T> help = new Stack<>();
        while (!stack.isEmpty()){
            T temp = stack.pop();
            //将temp压入到help合适的位置
            while (!help.isEmpty() && help.peek().compareTo(temp) < 0){
                stack.push(help.pop());
            }
            help.push(temp);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }

    }

    public static void main(String[] args) {
        SortByStack<Integer> sortByStack = new SortByStack<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(1);
        stack.push(6);
        stack.push(2);
        System.out.println(stack);
        sortByStack.sort(stack);
        System.out.println(stack);
    }
}
