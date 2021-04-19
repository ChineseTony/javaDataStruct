package com.tom.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tom
 */
public class RemoveKDigit {

    public static String removeKdigit(String nums,int k){
        if (nums == null || nums.length() <= 0 || k<= 0){
            return nums;
        }
        int len = nums.length();
        if (k >= len){
            return "0";
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c:nums.toCharArray()){
            if (!stack.isEmpty() && stack.peek() > c && k>0){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && stack.peek() == '0'){
            stack.pop();
        }
        while (!stack.isEmpty() ){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "1593212";
        int k = 3;
        System.out.println(removeKdigit(s,k));

    }
}
