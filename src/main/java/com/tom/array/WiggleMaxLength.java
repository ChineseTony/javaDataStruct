package com.tom.array;


/**
 * @link https://leetcode-cn.com/problems/wiggle-subsequence/
 *
 * @author wangtao
 */
public class WiggleMaxLength {


    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }
            if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,17,5,10,13,15,10,5,16,8};
//        int[] nums = new int[]{1,7,4,9,2,5};
        System.out.println(wiggleMaxLength(nums));


    }
}
