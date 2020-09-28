package com.tom.nums;



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

    public static void main(String[] args) {
        System.out.println(xorOperation(4,3));
    }
}
