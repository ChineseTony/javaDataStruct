package com.tom.array;


import java.io.BufferedReader;

/**
 * @link https://leetcode-cn.com/problems/grumpy-bookstore-owner/
 * @author wangtao
 */
public class MaxSatisfied {

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0;
        int len = customers.length;
        for (int i = 0; i < len; i++) {
            if (grumpy[i] == 0){
                total += customers[i];
            }
        }
        int increase = 0;
        //  求窗口为X  生气最大值
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        int maxIncrease = 0;
        for (int i = X; i < len; i++) {
            increase = increase - customers[i - X] * grumpy[i - X]
                    + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }

    public static void main(String[] args) {
        int[] customers = new int[]{1,0,1,2,1,1,7,5};
        int[] grumpy = new int[]   {0,1,0,1,0,1,0,1};
        int X = 3;
        System.out.println(maxSatisfied(customers,
                grumpy,X));

        String line ="io.seata.common.loader.EnglishHello #aab";

        final int ci = line.indexOf('#');
        System.out.println(ci);
        if (ci >= 0) {
            line = line.substring(0, ci);
        }
        line = line.trim();
        System.out.println(line);

    }
}
