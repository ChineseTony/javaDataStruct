package com.tom.array;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @author tom
 * @link https://leetcode-cn.com/problems/132-pattern/
 */
public class Find132pattern {

    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        if (nums.length < 3) {
            return false;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] min = new int[len];
        min[0] = nums[0];
        for (int i = 1; i < len; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        for (int j = len - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
