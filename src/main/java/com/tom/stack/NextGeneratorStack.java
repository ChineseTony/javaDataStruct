package com.tom.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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


    public int[] xorQueries(int[] arr, int[][] queries) {
        //计算前缀异或值
        int[] prefixXOR = new int[arr.length+1];
        for(int i=0;i<arr.length;i++){
            prefixXOR[i+1] = (prefixXOR[i]^arr[i]);
        }

        int[] output = new int[queries.length];
        //计算给定区间异或值
        for(int i=0;i<queries.length;i++){
            int start = queries[i][0];
            int end = queries[i][1]+1;
            output[i] = (prefixXOR[start]^prefixXOR[end]);
        }
        return output;
    }

    public int subArray(int[] arr,int k){
        int len = arr.length;
        Map<Integer,Integer> map = new HashMap<>(len);
        map.put(0,1);
        int result = 0;
        int preSum = 0;
        for (int i = 0; i < len; i++) {
            preSum += arr[i];
            int tmp = preSum - k;
            if (map.containsKey(tmp)) {
                result += map.get(tmp);
            }
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return result;
    }

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(nums[nums.length / 2] - num);
        }
        return sum;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,-3,4,9,0};
        int[] tmp = getNextMax(arr);
        for(int i:tmp){
            System.out.print(i+"\t");
        }
    }
}
