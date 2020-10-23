package com.tom.array;

import java.util.HashSet;
import java.util.Set;


/**
 * @link https://leetcode-cn.com/problems/set-mismatch/
 */
public class FindErrorNums {

    private FindErrorNums(){

    }



    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0){
            return nums;
        }
        int len = nums.length;
        Set<Integer> set = new HashSet<>(len);
        int sum = 0;
        int tmp = 0;
        int repeat = 0;
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])){
                repeat = nums[i];
            }
            tmp += i+1;
            sum += nums[i];
            set.add(nums[i]);
        }
        int[] result = new int[2];
        result[0] = repeat;
        result[1] = repeat + tmp - sum;
        return result;
    }

    public boolean threeConsecutiveOdds(int[] arr) {
        if (arr == null || arr.length < 3){
            return false;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1){
                count++;
                if (count == 3){
                    return true;
                }
            }else {
                count = 0;
            }
        }
        return false;

    }



}
