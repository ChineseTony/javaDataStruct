package com.tom.oj;

/**
 * @author wangtao .
 * @createTime on 2020/10/11
 * @link https://leetcode-cn.com/problems/trapping-rain-water/solution/
 */

public class Trap {


    public static int trap(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        int result = 0;
        int len = height.length;
        for (int i = 1; i < len - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft,height[j]);
            }
            for (int j = i; j < len; j++) {
                maxRight = Math.max(maxRight,height[j]);
            }
            result += Math.min(maxLeft,maxRight) - height[i];
        }
        return result;
    }


    public static int trap2(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        int result = 0;
        int len = height.length;
        int[] leftMaxArray = new int[len];
        int[] rightMaxArray = new int[len];
        leftMaxArray[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMaxArray[i] = Math.max(height[i],leftMaxArray[i-1]);
        }
        rightMaxArray[len-1] = height[len - 1];
        for (int i = len -2 ; i >= 0; i--) {
            rightMaxArray[i] = Math.max(height[i],rightMaxArray[i+1]);
        }
        for (int i = 1; i < len - 1; i++) {
            result += Math.min(leftMaxArray[i],rightMaxArray[i]) - height[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] test = new int[]{2,0,2};
        System.out.println(trap(test));
        System.out.println(trap2(test));
    }
}
