package com.tom.array;

import java.util.Arrays;

public class ThreeSumClosest {

    private ThreeSumClosest(){

    }

    public static int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length <= 2){
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int result = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < len-2; i++) {
            int tmp = target - nums[i];
            //
            int left = i + 1;
            int right = len - 1;
            while (left < right){
                int tmpValue  =Math.abs(nums[left] + nums[right] - tmp);
                if(tmpValue < diff){
                    diff = tmpValue;
                    result = nums[left] + nums[right] + nums[i];
                }
                if (nums[left] + nums[right] > tmp){
                    right -- ;
                }else if (nums[left] + nums[right] < tmp){
                    left++;
                }else {
                    return target;
                }
            }
        }

        return  result;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1,2,1,-4};
        System.out.println(threeSumClosest(arr,1));
    }
}
