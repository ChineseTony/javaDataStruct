package com.tom.oj;

/**
 * @author WangTao
 */
public class NumArray2 {
    private int[]  sum;

    private int[] data;


    public NumArray2(int[] nums) {
        if (nums.length > 0){
            data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            sum = new int[nums.length + 1];
            sum[0]  = 0;
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }
    }

    public void update(int i, int val) {
        data[i] = val;
        for (int j = i; j < sum.length-1; j++) {
            sum[j + 1] = sum[j] + data[j];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
