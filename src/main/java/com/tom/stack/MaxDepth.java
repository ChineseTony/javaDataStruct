package com.tom.stack;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/
 */
public class MaxDepth {

    private MaxDepth(){

    }

    public static int maxDepth(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        Queue<Character> stack = new ArrayDeque<>();
        int tmp = 0;
        int result = 0;
        for (char c:s.toCharArray()){
            if (c == '(') {
                stack.offer(c);
                tmp++;
            }else if (c == ')'){
                stack.poll();
                result = Math.max(tmp,result);
                if (stack.isEmpty()){
                    tmp = 0;
                }else {
                    tmp--;
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
        System.out.println(maxDepth("(1)+((2))+(((3)))"));
        System.out.println(maxDepth("1+(2*3)/(2-1)"));
    }
}
