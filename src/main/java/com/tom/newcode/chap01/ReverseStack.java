package com.tom.newcode.chap01;

import java.util.Stack;

/**
 * @author WangTao
 */
public class ReverseStack<T> {
    private   T getAndRemoveElement(Stack<T> stack){
        T result = stack.pop();
        if (stack.isEmpty()){
            // 递归出口
            return result;
        }else {
            T last = getAndRemoveElement(stack);
            //不是最后一个元素重新压入栈中
            stack.push(result);
            //返回值
            return last;
        }
    }

    public void reverse(Stack<T> stack){
//      递归出口
        if (stack.isEmpty()){
            return;
        }
        T t = getAndRemoveElement(stack);
        reverse(stack);
        stack.push(t);
    }

    public static void main(String[] args) {
        ReverseStack<Integer> reverseStack = new ReverseStack<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 4; i > 0; i--) {
            stack.push(i);
        }
        reverseStack.reverse(stack);

    }
}
