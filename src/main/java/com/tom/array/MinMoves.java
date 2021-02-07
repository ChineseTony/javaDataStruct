package com.tom.array;


import java.util.Arrays;

/**
 * @author wangtao
 */
public class MinMoves {


    /**
     *
     * 输入：
     * [1,2,3]
     * 输出：
     * 3
     * 解释：
     * 只需要3次操作（注意每次操作会增加两个元素的值）：
     * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
     * @param nums
     * @return
     */
    public static int minMoves(int[] nums) {
        if(nums == null || nums.length <= 0){
            return 0;
        }
        int len = nums.length;
        int min = 0,max = len-1,count =0;
        while (true){
            //求最大值和最小值下标
            for (int i = 0; i < len; i++) {
                if (nums[max] < nums[i]) {
                    max = i;
                }
                if (nums[min] > nums[i]) {
                    min = i;
                }
            }
            if(nums[min] == nums[max]){
                break;
            }
            //累加
            for (int i = 0; i < len; i++) {
                if (i != max){
                    nums[i]++;
                }
            }
            count++;
        }
        return count;
    }



    public static int minMoves2(int[] nums) {
        if(nums == null || nums.length <= 0){
            return 0;
        }
        int len = nums.length;
        int min = 0,max = len-1,count =0;
        while (true){
            //求最大值和最小值下标
            for (int i = 0; i < len; i++) {
                if (nums[max] < nums[i]) {
                    max = i;
                }
                if (nums[min] > nums[i]) {
                    min = i;
                }
            }
            int diff = nums[max] - nums[min];
            if(diff == 0){
                break;
            }
            //累加
            for (int i = 0; i < len; i++) {
                if (i != max){
                    nums[i] += diff;
                }
            }
            count += diff;
        }
        return count;
    }



    public int minMoves3(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2147483647};

        System.out.println(minMoves2(nums));
    }


}