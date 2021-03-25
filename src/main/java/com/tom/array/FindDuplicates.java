package com.tom.array;


import java.util.ArrayList;
import java.util.List;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 */
public class FindDuplicates {


    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0) {
                res.add(Math.abs(index + 1));
            }
            nums[index] = -nums[index];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,3,1};
        findDuplicates(nums).forEach(System.out::println);
    }
}
