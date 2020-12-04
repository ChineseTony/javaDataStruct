package com.tom.array;


/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {

    private SearchRange(){

    }


    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1,-1};
        if (nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == target){
                result[0] = i;
                break;
            }
        }
        for (int i=len-1;i >=0;i--){
            if (nums[i] == target){
                result[1] = i;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
