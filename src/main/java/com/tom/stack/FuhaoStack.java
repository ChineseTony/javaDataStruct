package com.tom.stack;

import java.util.*;

/**
 * @author WangTao
 */
public class FuhaoStack {


    private FuhaoStack(){

    }

    /**
     *   4个 a 任意添加 + - * / 运算 判断是不是等于 b
     * @param a
     * @param b
     * @return
     */
    public  static boolean cal(int a,int b){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(a);
        for (int i = 0; i < 3; i++) {
            List<Integer> tmp = new ArrayList<>();
            while (!stack.isEmpty()){
                tmp.add(stack.pop());
            }
            for(int t:tmp){
                stack.push(t + a);
                stack.push(t - a);
                stack.push(t * a);
                stack.push(t / a);
            }
        }
        Set<Integer> set = new HashSet<>(stack);
        return set.contains(b);
    }

    public static void main(String[] args) {
        // 1 + 1 + 1 * 1
        System.out.println(cal(1,3));
        System.out.println(cal(1,5));
    }
}
