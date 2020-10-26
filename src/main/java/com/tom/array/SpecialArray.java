package com.tom.array;

import java.util.Arrays;

public class SpecialArray {

    private SpecialArray(){

    }

    public static int specialArray2(int[] nums) {
        int[] helper = new int[1001];
        for (int i = 0; i < nums.length; i++) {
            helper[nums[i]]++;
        }
        int count = 0;
        for (int i = 1000; i >= 0; i--) {
            count += helper[i];
            if (count == i) {
                return i;
            }
        }
        return -1;
    }


    public static int specialArray(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        for (int i = 1; i <= max; i++) {
            //统计
            int count = 0;
            for (int tmp:nums) {
                if (tmp >= i){
                    count++;
                }
            }
            if (i == count){
                return i;
            }
        }
        return -1;

    }



    public static void main(String[] args) {
        int[] arr = new int[]{4,4,4,4};
        System.out.println(specialArray(arr));
    }
}
