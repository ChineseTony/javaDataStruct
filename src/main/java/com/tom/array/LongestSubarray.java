package com.tom.array;


import java.util.TreeMap;

/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/longest-continuous-
 * subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
public class LongestSubarray {

    public static int longestSubarray(int[] nums, int limit) {
        int len = nums.length;
        int count = 0;
        if (len == 1){
            return  1;
        }
        for (int i = 0; i < len; i++) {
            int max = nums[i];
            int min = nums[i];
            for (int j = i+1; j < len; j++) {
                max = Math.max(max,nums[j]);
                min = Math.min(min,nums[j]);
                if (max - min > limit){
                    //跳出循环
                    break;
                }
                count = Math.max(count,j - i + 1);
            }
        }
        return count;
    }

    public static int longestSubarray2(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right],
                    0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }



    public int largestAltitude(int[] gain) {
        int result = 0;
        int tmp = 0;
        for (int i :gain) {
            tmp += i;
            result = Math.max(tmp,result);
        }
        return result;
    }


    public static boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{8,2,4,7};
        int limit = 4;
        System.out.println(longestSubarray(nums, limit));

        int[][] arrs = new int[][]{{1,2,3,4}, {5, 1, 2, 3
        },{9,5,1,2}};

        System.out.println(isToeplitzMatrix(arrs));

    }
}
