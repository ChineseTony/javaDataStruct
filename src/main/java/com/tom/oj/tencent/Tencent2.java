package com.tom.oj.tencent;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


/**
 * @author tom
 */
public class Tencent2 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int index = m+n-1;
        while (i>=0 && j>=0){
            if (nums1[i] >= nums2[j]){
                nums1[index--] = nums1[i--];
            }else {
                nums1[index--] = nums2[j--];
            }
        }
        while (j >= 0){
            nums1[index--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{0,0,0,0,0};
        int[] nums2 = new int[]{1,2,3,4,5};
        merge(nums1,0,nums2,5);
        Arrays.stream(nums1).forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        String s = scanner.next();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty()){
                stack.push(s.charAt(i) - '0');
            }else {
                int tmp = s.charAt(i) - '0';
                int tmpValue = stack.peek();
                if (tmp + tmpValue == 10){
                    stack.pop();
                }else {
                    stack.push(tmp);
                }
            }
        }
        System.out.println(stack.size());
    }
}
