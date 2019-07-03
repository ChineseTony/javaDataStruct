package com.tom.stack;

import java.util.Stack;

/**
 * @author WangTao
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class Valid {

    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] ==  '(' || chars[i] == '[' || chars[i] == '{'){
                stack.push(chars[i]);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                char top = stack.pop();
                if (top == '[' && chars[i] != ']'){
                    return false;
                }
                if (top == '{' && chars[i] != '}'){
                    return false;
                }
                if (top == '(' && chars[i] != ')'){
                    return false;
                }
            }
        }
        return stack.isEmpty() ;
    }

    public static void main(String[] args) {

        String s = "{}[]";
        System.out.println(isValid(s));
    }
}
