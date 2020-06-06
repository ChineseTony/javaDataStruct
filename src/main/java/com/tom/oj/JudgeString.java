package com.tom.oj;

import java.util.Arrays;

public class JudgeString {


    public boolean isNumber(String s) {

        return true;

    }

    public int nthUglyNumber(int n) {
        int[] result = new int[n];
        result[0] = 1;
        int p2=0,p3=0,p5=0;
        int n2,n3,n5;
        for (int i = 1; i < n; i++) {
            n2 = result[p2] * 2;
            n3 = result[p3] * 3;
            n5 = result[p5] * 5;
            result[i] =Math.min(n5,Math.min(n2,n3));
            if(result[i] == n2){
                p2++;
            }
            if(result[i] == n3){
                p3++;
            }
            if(result[i] == n5){
                p5++;
            }

        }
        return result[n-1];

    }

    public boolean isStraight(int[] nums) {
        if(nums == null || nums.length == 0){
            return true;
        }
        Arrays.sort(nums);
        int zero = 0;
        int diff = 0;
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] == 0){
                zero++;
                continue;
            }
            if(nums[i+1]  == nums[i] ){
                return false;
            }else {
                diff += nums[i+1] - nums[i] -1;
            }
        }
        return diff <= zero ;
    }

    public static void main(String[] args) {
        System.out.println(1E3);
    }


}
