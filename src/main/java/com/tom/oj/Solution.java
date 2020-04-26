package com.tom.oj;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangTao
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        int[] result = new int[2];
        while (i < j){
            if(nums[i] + nums[j] == target){
                result[0] = nums[i];
                result[1] = nums[j];
                break;
            }else if (nums[i] + nums[j] < target){
                i ++;
            }else {
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int i = 6;
        int times = 2;
        int[] a = new int[i];
        for (int j = 0; j < i; j++) {
            a[j] = j+1;
        }
        printArray(a);
        for (int j = 0; j < times; j++) {
            reverse(a);
        }
        printArray(a);

    }

    private static void reverse(int[] a){
        int temp = a[a.length-1];
        int i;
        for (i = a.length -1; i > 0; i--) {
            a[i] = a[i-1];
        }
        a[0] = temp;
    }

    private static void printArray(int[] a){
        for (int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }


}
