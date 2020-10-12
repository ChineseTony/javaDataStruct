package com.tom.array;


/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * @link gitbook https://leetcode-cn.com/leetbook/read/binary-search/xem7js/
 */
public class SearchTarget {


    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == target){
                return i;
            }
        }
        return -1;
    }

    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = len -1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]){
                return mid;
            }
            if (nums[0] <= nums[mid]){
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }else {
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static int findRightValue(int[] nums,int target){
        if (nums == null || nums.length == 0){
            return -1;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = len-1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return -1;

    }


    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }



    public int findPeakElement2(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
        if (l == r) {
            return l;
        }
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, l, mid);
        }
        return search(nums, mid + 1, r);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4,5,6,7,0,1,2};
        System.out.println(search2(arr,0));

        int[] arr2 = new int[]{0,0,0,1,1,2};
        System.out.println(findRightValue(arr2,1));
//        System.out.println(search2(arr,3));
    }
}
