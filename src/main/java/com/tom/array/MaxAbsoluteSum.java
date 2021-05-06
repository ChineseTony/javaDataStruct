package com.tom.array;


/**
 * @author tom
 * @link https://leetcode-cn.com/problems/maximum-absolute-sum-of-any-subarray/
 */
public class MaxAbsoluteSum {
    public static int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        int ans = 0;
        int min = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans,Math.abs(sum[i] - max));
            ans = Math.max(ans,Math.abs(sum[i] - min));
            max = Math.max(ans,max);
            min = Math.min(ans,min);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,-5,1,3};
        System.out.println(maxAbsoluteSum(nums));

    }
}
