package com.tom.array;

import java.util.Arrays;

public class FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        if(nums ==  null || nums.length == 0){
            return 1;
        }
        Arrays.sort(nums);
        int result = 1;
        int len = nums.length;
        int i;
        for(i=0;i<len-1;i++){
            //小于0的数直接跳过
            if(nums[i] > 0){
               if (result != nums[i]){
                   return result;
               }else {
                   //有重复的元素
                   if (result != nums[i+1]){
                       result++;
                   }
               }
            }

        }
        //如果result等于最后一个元素 result+1
        if (result == nums[len-1]){
            result++;
        }
        return result;
    }

    public static int firstMissingPositive2(int[] nums) {
        if(nums ==  null || nums.length == 0){
            return 1;
        }
        int result = 1;
        int len = nums.length;

        //1.把所有小于等于0变为len+1
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0){
                nums[i] = len+1;
            }
        }
        //2.原地hash 把 num[i] 变为 负数
        for (int i = 0; i < len; i++) {
            int tmp = Math.abs(nums[i]);
            if (tmp <= len){
                nums[tmp-1] = -Math.abs(nums[tmp-1] );
            }
        }
        //找到第一个为0的正数
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0){
                return i+1;
            }
        }
        return len+1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,3,4};
        System.out.println(firstMissingPositive2(arr));

    }
}
