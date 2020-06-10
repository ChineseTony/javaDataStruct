package com.tom.oj;

import java.util.*;

/**
 * @author WangTao
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        int[] result = new int[2];
        while (i < j){
            if(nums[i] + nums[j] == target){
                result[0] = nums[i];
                result[1] = nums[j];
                break;
            }else if (nums[i] + nums[j] < target){
                i ++;
            }else {
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int i = 6;
//        int times = 2;
//        int[] a = new int[i];
//        for (int j = 0; j < i; j++) {
//            a[j] = j+1;
//        }
//        printArray(a);
//        for (int j = 0; j < times; j++) {
//            reverse(a);
//        }
//        printArray(a);

        int[] test = new int[]{4,3,2,7,8,2,3,1};
        List<Integer> list = findDisappearedNumbers(test);
        for (int tmp:list){
            System.out.print(tmp+"\t");
        }
        System.out.println();
        String[] s= new String[]{
                "testemail@leetcode.com",
                "testemail1@leetcode.com",
                "testemail+david@lee.tcode.com"};
        System.out.println(numUniqueEmails(s));
    }

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if(len == 1 || len == 2){
            return len;
        }
        int result = 0;



        return result;
    }


    public int repeatedNTimes(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
//        int len = arr.length;
//        int result = 0;
//
//        Map<Integer,Integer> map = new HashMap<>(len);
//        for (int i:A){
//            map.put(i,map.getOrDefault(i,0)+1);
//        }
//        for (int i:A){
//            if(map.get(i) > 1){
//                return i;
//            }
//        }
//
//        return result;
        // 3个数中超过半数
        int len = arr.length;
        for (int i = 0; i < len - 2; i++) {
            if (arr[i] == arr[i+1] || arr[i] == arr[i+2]) {
                return arr[i];
            }
        }

        // 上面循环没找到，那必然是最后一个数，如[1,2,3,1]
        return arr[len - 1];
    }



    public static List<Integer>  findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tmp = Math.abs(nums[i]) -1;
            nums[tmp] =-Math.abs(nums[tmp]);
        }
        for (int i = 0; i < len; i++) {
            if(nums[i] > 0){
                result.add(i+1);
            }
        }
        return result;

    }


    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();
        int len = s.length();
        for (int i = 0; i <len;i++ ){
            Character c = s.charAt(i);
            //如果栈包含改元素直接跳过
            if(stack.contains(c)){
                continue;
            }
            // 当前栈元素小于 字典数 并且 后继元素 有栈顶元素值
            while(!stack.isEmpty() && stack.peek()>c &&s.indexOf(stack.peek(),i)!=-1) {
                stack.pop();
            }
            //压入栈顶
            stack.push(c);
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

    }


    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[n << 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            result[index++] = nums[i];
            result[index++] = nums[n+i];
        }
        return result;
    }


    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int len = A.length;
        int[] result = new int[len];
        int sum = 0;
        int q = 0;
        for(int i:A){
            if(i % 2 == 0){
                sum += i;
            }
        }
        for (int i = 0; i < len; i++) {
            int val = queries[i][0],index = queries[i][1];
            if(A[index] %  2 == 0 ){
                sum -= A[index];
            }
            A[index] += val;
            if(A[index] %  2 == 0 ){
                sum += A[index];
            }
            result[q++]= sum;
        }
        return result;

    }


    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int i = 0;
        int max = 0;
        int j= nums.length-1;
        while (i < j){
            if((nums[i] -1) * (nums[j] -1) > max){
                max = (nums[i] -1) * (nums[j] -1);
            }
            if(nums[i] > nums[j]){
                j--;
            }else {
                i++;
            }
        }
        return max;
    }


    public static int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0){
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String s:emails){
            StringBuilder sb = new StringBuilder();
            String[] tmp =s.split("@");

            String str=tmp[0].replace(".","");
            if(str.contains("+")){
                str = str.substring(0,str.indexOf("+"));
            }
            sb.append(str).append("@").append(tmp[1]);
            set.add(sb.toString());
        }
        return set.size();

    }



    private static void reverse(int[] a){
        int temp = a[a.length-1];
        int i;
        for (i = a.length -1; i > 0; i--) {
            a[i] = a[i-1];
        }
        a[0] = temp;
    }

    private static void printArray(int[] a){
        for (int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }


}
