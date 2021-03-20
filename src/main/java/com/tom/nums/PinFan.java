package com.tom.nums;


import java.util.ArrayDeque;

/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/sum-of-square-numbers/submissions/
 */
public class PinFan {


    /**
     * 判断 是不是 存在 a * a + b * b = c
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;

    }


    /**
     *  https://leetcode-cn.com/problems/xor-operation-in-an-array/
     * @param n
     * @param start
     * @return
     */
    public static int xorOperation(int n, int start) {
        int tmp = start;
        for (int i = 1; i < n; i++) {
            tmp = tmp ^ (2 * i + start);
        }
        return tmp;


    }

    /**
     * tokens = ["2","1","+","3","*"]
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (!isSymole(token)){
                stack.push(token);
            }else {
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                int tmp = 0;
                if ("+".equals(token)) {
                    tmp = a + b;
                }else  if ("-".equals(token)) {
                    tmp = b - a;
                }else  if ("*".equals(token)) {
                    tmp = a * b;
                }else {
                    tmp = b / a;
                }
                stack.push(String.valueOf(tmp));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private boolean isSymole(String s){
        return "+".equals(s) ||
                "-".equals(s) ||
                "/".equals(s) ||
                "*".equals(s);
    }

    public static void main(String[] args) {
        System.out.println(xorOperation(4,3));

        String[] tokens=new String[]{
                "4","13","5","/","+"
        };
        System.out.println(new PinFan().evalRPN(tokens));
    }
}
