package com.tom.oj.baidu;

import java.util.Arrays;

public class Baidu2 {


    public static void main(String[] args) {
        int[] nums = new int[]{0,1,6,1,3,5,6,3,4};
        // 0,-1,3,-3,
        System.out.println(getDup(nums));
        System.out.println(Arrays.toString(nums));
    }

    public static int getDup(int[] arr){
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int value = arr[i];
            int index = Math.abs(value);
            arr[index] = -value;
            if (arr[index] > 0){
                return index;
            }
        }
        return -1;


    }
}
