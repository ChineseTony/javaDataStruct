package com.tom.array;


/**
 * @author wangtao
 */
public class FindMaxConsecutiveOnes {

    public static int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length <= 0){
            return 0;
        }
        int result = 0;
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1){
                count ++;
            }else {
                result = Math.max(count,result);
                count = 0;
            }
        }
        result = Math.max(result,count);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes(nums));

    }
}
