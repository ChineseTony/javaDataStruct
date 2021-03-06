package com.tom.stack;

import java.util.Stack;

/**
 * @author WangTao
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 */
public class MinStack {

    private Stack<Integer> stack;

    //用于保存最小的值
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        //minStack 为空 或者 minStack 栈顶的值小于 x
        if (minStack.isEmpty() || minStack.peek() >= x){
            minStack.push(x);
        }
    }
    public void pop() {
        if (stack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }


}
