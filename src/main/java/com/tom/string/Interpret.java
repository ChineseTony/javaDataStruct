package com.tom.string;


import java.util.Stack;

/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/goal-parser-interpretation/
 */
public class Interpret {

    public static String interpret(String command) {
        if (command == null || command.length() <= 0){
            return command;
        }
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c:command.toCharArray()){
            if (c == '('){
                stack.push(c);
            }else if (c == ')'){
                if (stack.peek() != '('){
                    StringBuilder tmp = new StringBuilder();
                    while (stack.peek() != '('){
                        tmp.append(stack.pop());
                    }
                    sb.append(tmp.reverse());
                }else {
                    sb.append('o');
                }
                stack.pop();
            }else {
                if (stack.isEmpty()){
                    sb.append(c);
                }else {
                    stack.push(c);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "(al)G(al)()()G";
        System.out.println(interpret(s));

    }
}
