package com.tom.oj;

/**
 * @author WangTao
 */
public class SearchInsert {

    public static int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int len = nums.length;
        int result = 0;
        int i ;

        for (i = 0; i < len; i++) {
            if (target <= nums[i]){
                result = i;
                break;
            }
        }
        return i == len ? len : result;

    }

    public static void main(String[] args) {
        int[] arr = new int[] {
                1,3,5,6
        };
        System.out.println(searchInsert(arr,0));

    }
}
