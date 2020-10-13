package com.tom.array;

import java.util.HashSet;
import java.util.Set;

public class ContainsNearbyDuplicate {


    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k < 0){
            return false;
        }
        if (nums == null || nums.length == 0){
            return false;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tmp = nums[i];
            for (int j = i+1; j < len; j++) {
                if (nums[j] == tmp){
                    if (j - i <= k){
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (k < 0){
            return false;
        }
        if (nums == null || nums.length == 0){
            return false;
        }
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1,2,3};
        System.out.println(containsNearbyDuplicate(nums,2));


    }


}
