package com.tom.array;

public class MinStartValue{

    private MinStartValue(){

    }


    public static int minStartValue(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int startValue = 0;
        int len = nums.length;
        int tmp = 0;
        for (int i = 0; i < len; i++) {
            tmp = tmp + nums[i];
            System.out.print(tmp+"\t");
            if (tmp <= 0){
                startValue +=  1 - tmp;
                tmp += 1 - tmp ;
            }
        }
        return startValue == 0 ? 1 : startValue;
    }

    /**
     * @link https://leetcode-cn.com/problems/non-decreasing-array/
     * @param nums
     * @return
     */
    public static boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0){
            return true;
        }
        int result = 0;
        int len = nums.length;
        for (int i = 0; i < len -1; i++) {
            if(nums[i] > nums[i + 1]){
                result ++;
            }
        }

        return result <= 1;

    }

    public static void main(String[] args) {
        int[] nums =new int[]{3,4,2,3};
//        System.out.println(minStartValue(nums));
        System.out.println(checkPossibility(nums));
    }

}
