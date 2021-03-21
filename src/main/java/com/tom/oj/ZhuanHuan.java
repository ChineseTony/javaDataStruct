package com.tom.oj;



import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


public class ZhuanHuan {


    /*   *//**
     * 判断 a b 的优先级
     * @param a
     * @param b
     * @return
     *//*
    public static boolean pandu(char a,char b){
        if ((a == '*' && (b == '+' || b == '-'))){
            return true;
        }else if ((a == '/' )
                && (b != '+' && b != '-')){
            return true;
        }
        return false;
    }


    */

    /**
     * 输入：-2*(+2-2)
     * 输出：-2 2 2 - *
     *
     * @param args
     *//*
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String tmp = s.nextLine();
        ArrayDeque<Character> charStack = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int digit =0;
        for (char c : tmp.toCharArray()){
            if (Character.isDigit(c)){
                //没有判断 正负号 还有 10 + 2
//                result.add(String.valueOf(c));
                digit++;
                sb.append(c);
            }else {
                if (digit != 0){
                    digit = 0;
                    result.add(sb.toString());
                    sb.delete(0,sb.length());
                }
                if (charStack.isEmpty() || c == '('){
                    charStack.push(c);
                }else if (c == ')'){
//                    弹出所有操作符号  ( 需要出栈
                    while (!charStack.isEmpty() && charStack.peek() != '('){
                        result.add(String.valueOf(charStack.pop()));
                    }
                    if (!charStack.isEmpty() && charStack.peek() == '('){
                        charStack.pop();
                    }
                }else {
//                    判断优先级 c的优先级大于栈顶元素
                    if (pandu(charStack.peek(),c)){
                        result.add(String.valueOf(charStack.pop()));
                        result.add(String.valueOf(c));
                    }else {
                        charStack.push(c);
                    }
                }
            }
        }
        if (sb.length() != 0){
            result.add(sb.toString());
        }
        while (!charStack.isEmpty()){
            result.add(String.valueOf(charStack.pop()));
        }
        int len = result.size();
        for (int i = 0; i < len; i++) {
            System.out.print(result.get(i).trim());
            if (i != len-1){
                System.out.print("\t");
            }
        }

    }*/
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        Stack<Character> stk = new Stack<Character>();
        Map<Character, Integer> map = new HashMap<>(16);
        //优先级
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('(', 3);
        map.put(')', 3);
        int index = 0;
        boolean flag = true;//判断是否是第一输出
        while (index < str.length()) {
            //判断是否是操作数,如上图介绍
            if ((index < 1 || str.charAt(index - 1) == '(') && (str.charAt(index) == '+' || str.charAt(index) == '-') || isdigit(str.charAt(index))) {
                if (flag) {
                    flag = false;
                }else {
                    System.out.printf(" ");
                }
                if (str.charAt(index) != '+') {
                    System.out.printf("%c", str.charAt(index));
                }
                while (index + 1 < str.length() &&
                        (str.charAt(index + 1) == '.'
                                || isdigit(str.charAt(index + 1)))) {
                    System.out.printf("%c", str.charAt(++index));
                }
                index++;
            } else {
                //操作符的情况,比较优先级,弹出优先级大于等于自己的。
                if (str.charAt(index) == '(') {
                    stk.push(str.charAt(index));
                }else if (str.charAt(index) == ')') {
                    while (!stk.empty() && stk.peek() != '(') {
                        System.out.printf(" %c", stk.peek());
                        stk.pop();
                    }
                    stk.pop();
                } else {
                    while (!stk.empty() && stk.peek() != '(' && map.get(stk.peek()) >= map.get(str.charAt(index))) {
                        System.out.printf(" %c", stk.peek());
                        stk.pop();
                    }
                    stk.push(str.charAt(index));
                }
                index++;
            }
        }
        while (!stk.empty()) {
            System.out.printf(" %c", stk.peek());
            stk.pop();
        }
    }


    static boolean isdigit(char s) {
            if (s == '1' || s == '2' || s == '3' || s == '4' || s == '5' || s == '6' || s == '7'
                    || s == '8' || s == '9' || s == '0') {
                return true;
            }
            return false;
    }

}
