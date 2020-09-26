package com.tom.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author WangTao
 *
 * @link https://pintia.cn/problem-sets/434/problems/5893
 * todo fix some bug this code just pass one point
 */
public class ConvertBiaodashi {


    /**
     * 判断 a b 的优先级
     * @param a
     * @param b
     * @return
     */
    public static boolean pandu(char a,char b){
        if ((a == '*' || a == '/') && (b != '*' && b != '/')){
            return true;
        }
        return false;
    }


    /**
     * 输入：-2*(+2-2)
     * 输出：-2 2 2 - *
     * @param args
     */
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
            System.out.print(result.get(i));
            if (i != len-1){
                System.out.print("\t");
            }
        }


    }
}
