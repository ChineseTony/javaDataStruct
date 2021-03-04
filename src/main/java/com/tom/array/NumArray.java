package com.tom.array;


/**
 * @author wangtao
 */
public class NumArray {

    private int[] arr;

    public NumArray(int[] nums) {
        arr = new int[nums.length];
        System.arraycopy(nums,0,
                arr,0,nums.length);
    }

    public int sumRange(int i, int j) {
        int result = 0;
        for (int k = i; k < j; k++) {
            result += arr[k];
        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        System.out.println(new NumArray(nums));

    }
}
