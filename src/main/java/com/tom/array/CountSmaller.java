package com.tom.array;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        int[] arr = new int[]{5,2,6,1};
        List<Integer> result = countSmaller(arr);
        for (int i:result){
            System.out.print(i+"\t");
        }
        System.out.println();

    }
}
