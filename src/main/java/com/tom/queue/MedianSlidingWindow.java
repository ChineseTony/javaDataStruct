package com.tom.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/sliding-window-median/
 * hard todo
 */
public class MedianSlidingWindow {


    public static double[] medianSlidingWindow(int[] nums, int k) {
        if(nums== null || nums.length <= 0 || k <= 0){
            return null;
        }
        int len = nums.length;
        double[] result = new double[len - k +1];
        Queue<Integer> queue = new ArrayDeque<>();
        int index = 0;
        for (int i = 0; i <= len; i++) {
            if (!queue.isEmpty() && queue.size() >= k){
//                求 queue的中位数
                Integer[] tmp = queue.toArray(new Integer[0]);
                Arrays.sort(tmp);
                if(k % 2 == 0){
                    result[index ++] = ((double)tmp[k / 2 - 1] + tmp[k / 2]) / 2.0;
                }else {
                    result[index ++] = tmp[k / 2];
                }
                queue.poll();

            }
            if(i != len) {
                queue.offer(nums[i]);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        Arrays.stream(medianSlidingWindow(nums, k)).forEach(System.out::println);
    }
}
