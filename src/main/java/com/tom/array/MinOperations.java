package com.tom.array;


import java.util.Arrays;

/**
 * @author WangTao
 * @link
 */
public class MinOperations {

    /**
     * 思路 每次选最小和最大的数字进行交换
     * @param n
     * @return
     */
    public static int minOperations(int n) {
//        int[] arr = new int[n];
//        int avg = 0;
//        for (int i = 0; i < n; i++) {
//            int tmp = 2 * i +1;
//            arr[i] = tmp;
//            avg += tmp;
//        }
//        avg = avg / n;
//        System.out.println(Arrays.toString(arr));
//        int times = 0;
//        for (int i = 0; i < n; i++) {
//            times += Math.abs(arr[i] - avg);
//        }
//        return times / 2;


        int ret = 0, avg = n;
        for (int i = 0; i < n; i++) {
            int x = 2 * i + 1;
            if (x > n) {
                ret += x - n;
            }
        }
        return ret;

    }


    public static void main(String[] args) {
        System.out.println(minOperations(3));
    }
}
