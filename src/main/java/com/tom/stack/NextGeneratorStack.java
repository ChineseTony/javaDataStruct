package com.tom.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGeneratorStack {

    public static int[] getNextMax(int[] arr){
        Deque<Integer> stack = new ArrayDeque<>();
        int len = arr.length;
        int[] result = new int[len];
        for (int i = len-1; i >= 0; i--) {
            while (!stack.isEmpty() && result[i] >=  stack.peek()){
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;

    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new ArrayDeque<>();
//        for (int i = 0; i < n * 2 - 1; i++) {
//            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
//                ret[stack.pop()] = nums[i % n];
//            }
//            stack.push(i % n);
//        }
        for (int i = 2 * n - 1;i>=0;i--){
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]){
                stack.pop();
            }
            ret[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return ret;

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length <= 0 ){
            return nums;
        }
        int len = nums.length;
        int[] result = new int[len - k +1];
        int j = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (!queue.isEmpty() && i - queue.peek() >= k){
                //删除队首元素
                queue.poll();
            }
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offer(i);
            if (i >= k-1){
                result[j++] = nums[queue.peek()];
            }
        }
        return result;
    }



    public static void main(String[] args) {
        int[] arr = new int[]{1,-3,4,9,0};
        int[] tmp = getNextMax(arr);
        for(int i:tmp){
            System.out.print(i+"\t");
        }
    }
}
