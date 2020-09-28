package com.tom.array;

import java.util.*;

/**
 * @author WangTao
 */
public class SumOddLengthSubarrays {


    /**
     * 求奇数个数的子数组所有和
     * @param arr
     * @return
     */
    public static int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        for (int i = 1; i <= arr.length; i = i + 2) {
            sum += countKSubarrays(arr,i);
        }
        return sum;
    }

    private static int countKSubarrays(int[] arr,int k){
        int sum = 0;
        List<Integer> deque = new ArrayList<>(k);
        for (int i = 0; i < arr.length; i++) {
            if (deque.size() >= k){
                sum += countValue(deque);
                deque.remove(0);
            }
            deque.add(arr[i]);
        }
        sum += countValue(deque);
        return sum;
    }

    private static int countValue(List<Integer> list){
        int sum = 0;
        for (int tmp : list){
            sum += tmp;
        }
        return sum;
    }



    public static int sumOddLengthSubarraysbetter(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i ++) {
            int left = i + 1, right = arr.length- i, 
                    leftEven = (left + 1) / 2, rightEven = (right + 1) / 2,
                    leftOdd = left / 2, rightOdd = right / 2;
            sum += (leftEven * rightEven + leftOdd * rightOdd) * arr[i];
        }
        return sum;
    }


    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if (target > nums[mid]){
                left = mid + 1;
            }else if (target < nums[mid]){
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }






    public static void main(String[] args) {
        int[] arr = {
                1,4,2,5,3
        };
        System.out.println(sumOddLengthSubarrays(arr));

    }
}
