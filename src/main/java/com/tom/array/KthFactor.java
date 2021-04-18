package com.tom.array;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * @author tom
 */
public class KthFactor {

    public static int kthFactor(int n, int k) {
     /*   List<Integer> list =new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            if (n % i == 0){
                list.add(i);
            }
        }
        if (k > list.size() || k < 0){
            return -1;
        }else {
            return list.get(k-1);
        }*/

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0){
               count++;
            }
            if (count == k){
                return i;
            }
        }
        return -1;
    }



    public static int removeDuplicates(int[] nums) {
        if (nums == null){
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    public static String removeDuplicates(String s, int k) {
        if (s == null || s.length() <= 0 || k <= 0){
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c:s.toCharArray()){
            if (stack.isEmpty() || stack.peek() != c){
                stack.push(c);
            }else {
                stack.push(c);
                int count = 0;
                while (!stack.isEmpty() && stack.peek() == c){
                    stack.pop();
                    count++;
                }
                if (count >= k){
                    count = count - k;
                }
                while (count -- > 0){
                    stack.push(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static String removeDuplicates2(String s, int k) {
        if (s == null || s.length() <= 0 || k <= 0){
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    //删除k个元素
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(kthFactor(7,2));
        int[] nums = new int[]{1,1,2,2,3};
        int len = removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        String s = "pbbcggttciiippooaais";
        int k = 2;
        System.out.println(removeDuplicates(s,k));
    }
}
