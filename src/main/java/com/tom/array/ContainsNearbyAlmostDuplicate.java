package com.tom.array;


/**
 * @author tom
 * @link https://leetcode-cn.com/problems/contains-duplicate-iii/submissions/
 */
public class ContainsNearbyAlmostDuplicate {

    public static boolean containsNearbyAlmostDuplicate(
            int[] nums, int k, int t) {
//        for (int i = 0; i < nums.length; ++i) {
//            for (int j = Math.max(i - k, 0); j < i; ++j) {
//                if (Math.abs(nums[i] - nums[j]) <= t) {
//                    return true;
//                }
//            }
//        }
        return false;

    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,1};
        System.out.println(containsNearbyAlmostDuplicate(nums,
                3,0));

    }
}
