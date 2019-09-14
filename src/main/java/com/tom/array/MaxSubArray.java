package com.tom.array;

/**
 * @author WangTao
 */
public class MaxSubArray {

    /**
     *
     * @param arr 数组
     * @return 连续最长子数组和
     * 暴力算法
     */
    public static int maxSub(int[] arr){
        if (arr == null || arr.length <= 0){
            return 0;
        }
        int max = arr[0];
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int sum = arr[i];
            for (int j = i+1; j < length; j++) {
                sum += arr[j];
                System.out.println("sum="+sum);
                if (sum >= max){
                    max = sum;
                }
            }
        }
        return max;
    }


    /**
     * Dp
     * @param arr 数组
     * @return 连续最长子数组和
     * 动态规划 dp
     * DP[i] = max{DP[i-1] + A[i],A[i]}
     */
    public static int maxSubSum(int arr[])
    {
        if (arr == null || arr.length <= 0){
            return 0;
        }
        int len = arr.length;
        int maxSum = 0;
        int thisSum= 0;
        for(int i=0;i<len;i++)
        {
            thisSum+= arr[i];
            if(thisSum > maxSum) {
                maxSum = thisSum;
            }else if(thisSum< 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, -6, 2, 3, -4};
        System.out.println(MaxSubArray.maxSub(arr));
        System.out.println(MaxSubArray.maxSubSum(arr));
    }
}
