package com.tom.stack;
import	java.util.ArrayDeque;

import java.util.Deque;
import java.util.Stack;

/**
 * @author WangTao
 */
public class StackUtil {
    private StackUtil(){

    }

    /**
     *
     * @param pushA 入栈字符串
     * @param popA 入栈字符串
     * @return 判断 是不是pushA的出栈顺序
     */
    public  static boolean isSequence(String pushA,String popA){
        if (pushA == null || popA == null
                || pushA.length() != popA.length()){
            return false;
        }
        int length = pushA.length();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        int j = 0;
        while (j < length){
            while (stack.isEmpty() || stack.peek() != popA.charAt(j)){
                //已经到字符串末尾 直接 跳出循环
                if (i == length){
                    break;
                }
                //一致入栈 直至找到  栈顶元素和 popA 一致时候
                stack.push(pushA.charAt(i));
                i++;
            }
            //判断栈顶元素 是不是 popA 一致
            if (stack.peek() != popA.charAt(j)){
                break;
            }
            stack.pop();
            j++;
        }
        if (stack.isEmpty() && j == length){
            return true;
        }
        return false;
    }

    public boolean isPopOrder(int [] pushA,int [] popA) {
        if(pushA == null || popA == null || pushA.length !=popA.length){
            return false;
        }
        int length = pushA.length;
        int i =0;
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        while(j  < length){
            while(stack.isEmpty() || stack.peek() != popA[j]){
                if(i == length){
                    break;
                }
                stack.push(pushA[i++]);
            }
            if(stack.peek() != popA[j]){
                break;
            }
            stack.pop();
            j++;
        }
        if(stack.isEmpty() && j == length ){
            return true;
        }
        return false;
    }




    public static int longestValidParentheses(String s) {
        if(s == null || s.length() ==0){
            return 0;
        }
        int maxans = 0;
        int length = s.length();
        Deque<Integer> stack = new ArrayDeque<> ();
        stack.push(-1);
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '('){
                stack.push(i);
            }else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }

        }
        return maxans;

    }

    public static void main(String[] args) {
//        System.out.println(StackUtil.isSequence("12345","43512"));
//        System.out.println(StackUtil.isSequence("12345","45321"));
        System.out.println(longestValidParentheses("()(()"));
    }
}
