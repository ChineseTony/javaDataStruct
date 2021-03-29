package com.tom.string;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tom
 */
public class ScoreOfParentheses {

    public static int scoreOfParentheses(String s) {
        if (s == null){
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (char c: s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            }else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String s = "(()(()))";
        System.out.println(scoreOfParentheses(s));

    }
}
