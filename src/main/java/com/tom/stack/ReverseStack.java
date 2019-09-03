package com.tom.stack;

import java.util.Stack;

/**
 * @author WangTao
 * 用递归操作 实现栈的逆序
 * 暂时没看懂
 */
public class ReverseStack<T> {


    //获取
    private   T getAndRemoveStack(Stack<T> stack){
        T result = stack.pop();
        if (stack.isEmpty()){
            //递归出口
            return result;
        }else {
            T last = getAndRemoveStack(stack);
            stack.push(result);
            return last;
        }

    }

    public void reverse(Stack<T> stack){
        if (stack.isEmpty()){
            return;
        }
        T t = getAndRemoveStack(stack);
        reverse(stack);
        stack.push(t);
    }

    public static void main(String[] args) {
        ReverseStack<Integer> reverseStack = new ReverseStack<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        reverseStack.reverse(stack);
        for(Integer i : stack){
            System.out.println(i);
        }
    }
}
