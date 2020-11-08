package com.tom.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DivingBoard {
    private static final Logger logger = LoggerFactory.getLogger(DivingBoard.class);

    private DivingBoard(){

    }

    public static int[] divingBoard(int shorter, int longer, int k) {
        if(k <= 0){
            return new int[0];
        }else if (shorter == longer){
            return new int[]{shorter * k};
        }
        int[] result = new int[k+1];
        int index = 0;
        int sum = shorter * k;
        while (k-- >= 0){
            result[index] = sum - index * shorter + index * longer;
            index++;
        }
        return  result;

    }


    /**
     * @link https://leetcode-cn.com/problems/water-bottles/
     * @param numBottles
     * @param numExchange
     * @return
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        int result = numBottles;
        int t = numBottles / numExchange;
        while (t != 0){
            t = numBottles / numExchange;
            result += t;
            numBottles = t + numBottles % numExchange;
        }
        return result;

    }


    public static int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int result = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            long tmp = 0;
            for (int j = i; j < len; j++) {
                tmp += nums[j];
                if (tmp >= lower && tmp <= upper){
                    result ++;
                }
            }
        }
        return  result;
    }


    public static boolean checkIfExist(int[] arr) {
        if(arr == null || arr.length == 0){
            return false;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
           int t = arr[i] * 2;
            for (int j = 0; j < len; j++) {
                if (i != j && arr[j] == t){
                    return true;
                }
            }
        }
        return  false;
    }

    public static boolean checkIfExist2(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int i : arr){
            if(set.contains(i)){
                return true;
            }
            if(i % 2 == 0){
                set.add(i / 2);
            }
            set.add(i * 2);
        }
        return false;
    }



    public int maximumProduct(int[] nums) {
        if(nums == null || nums.length < 3){
            return -1;
        }
        int length = nums.length;
        Arrays.sort(nums);

        int tmp = nums[0] * nums[1] * nums[length -1];
        int last = nums[length -3] * nums[length -2] * nums[length -1];
        return Math.max(tmp,last);

    }

    public static void main(String[] args) {
        int[] tmp = divingBoard(1,1,10);
        for(int i:tmp){
            logger.info("{}\t", i);
        }

        logger.info("{}",numWaterBottles(9,3));
        int[] arr = new int[]{-2147483647,0,-2147483647,2147483647};
        // [1,1] [1,3] [2,3]
        logger.info("{}",countRangeSum(arr,  -564, 3864));

        arr = new int[]{-2,0,10,-19,4,6,-8};
        logger.info("{}",checkIfExist(arr));
    }
}
