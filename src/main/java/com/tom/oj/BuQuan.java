package com.tom.oj;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tom
 * 判断括号是否匹配，不匹配进行自动补全操作
 */
public class BuQuan {

    public static boolean kuohao(String s){
        if (s == null || s.length() <= 0 ){
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        int len = s.length();
        boolean flag = true;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '('){
                stack.push('(');
            }else {
                if (!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else {
                    flag = false;
                    System.out.println("(*");
                }
            }
        }
        while (!stack.isEmpty()){
            System.out.println("*)");
            flag = false;
            stack.pop();
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(kuohao("((()"));

    }
}
