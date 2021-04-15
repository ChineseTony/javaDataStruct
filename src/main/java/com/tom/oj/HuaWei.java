package com.tom.oj;

import java.util.Scanner;
import java.util.Stack;

public class HuaWei {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "(u(love)i)";
//        String s = scanner.nextLine();
        System.out.println(reverse(s));

    }

    public static String reverse(String tmp){
        if (tmp == null || tmp.length() <= 0){
            return tmp;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char c:tmp.toCharArray()){
            if (c == ')') {
                StringBuilder stringBuilder = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '('){
                    stringBuilder.append(stack.pop());
                }
                stack.pop();
                for (int i = 0; i < stringBuilder.length(); i++) {
                    stack.push(stringBuilder.charAt(i));
                }
            }else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
