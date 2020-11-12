package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CountSmaller {

    private CountSmaller(){

    }

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>(16);
        if (nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            System.out.println(i);
            int count = 0;
            int tempVal = nums[i];
            for (int j = i+1; j < len; j++) {
                if (nums[j] < tempVal){
                    count++;
                }
            }
            result.add(count);
        }
        return  result;
    }

    public static List<Integer> countSmaller2(int[] nums) {
        List<Integer> result = new ArrayList<>(16);
        if (nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;


        return  result;
    }


    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0){
            return new int[0];
        }
        int sum = 0;
        int len = nums.length;
        int[] result = new int[len];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result[i] = sum;
        }
        return result;
    }

    public static int[] sortArrayByParityII(int[] arr) {
        int n = arr.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (arr[i] % 2 == 1) {
                while (arr[j] % 2 == 1) {
                    j += 2;
                }
                swap(arr, i, j);
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public int[] sortArrayByParityII2(int[] A) {
        if(A == null){
            return null;
        }
        int N = A.length;
        int[] ans = new int[N];
        int t = 0;
        for (int x: A){
            if (x % 2 == 0) {
                ans[t] = x;
                t += 2;
            }
        }
        t = 1;
        for (int x: A){
            if (x % 2 == 1) {
                ans[t] = x;
                t += 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,2,6,1};
        List<Integer> result = countSmaller(arr);
        for (int i:result){
            System.out.print(i+"\t");
        }
        System.out.println();
        int[] tmp = new int[]{2,3,1,1,4,0,0,4,3,3};
        Arrays.stream(sortArrayByParityII(tmp)).forEach(
                System.out::println
        );

    }
}
