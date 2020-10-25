package com.tom.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WangTao
 */
public class MystrStr {

    public int strStr(String haystack, String needle) {
        int i=0;
        int j=0;
        //标志位
        int flag=0;
        if(needle.length()==0){
            return 0;
        }
        while(i<haystack.length()&&j<needle.length()){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
                flag++;
            }else{
                j=0;
//                i 跳过前面元素
                i=i-flag+1;
                flag=0;
            }

            if(j==needle.length() && flag==needle.length()){
                return i-needle.length();
            }
        }
        return -1;

    }

    public static boolean repeatedSubstringPattern(String s) {
        return (s+s).substring(1,s.length()*2-1).indexOf(s)!=-1;
    }


    private static Set<Character> set;

    static {
        set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
    }

    public static String reverseVowels(String s) {
       if (s == null || s.length() == 0){
           return s;
       }
       int left = 0;
       char[] tmp = s.toCharArray();
       int len = s.length();
       int right = len-1;
       while (left <= right){
           if (!isYuanYin(tmp[left])){
               left++;
           }else if (!isYuanYin(tmp[right])){
               right--;
           }else {
               char t = tmp[left];
               tmp[left] = tmp[right];
               tmp[right] = t;
               left++;
               right--;
           }
       }
       return new String(tmp);
    }

    private static boolean isYuanYin(char c){
       return set.contains(c);
    }


    public static double findMaxAverage(int[] nums, int k) {
        double maxAverage =-Double.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<>(k);
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++) {
            if (deque.size() < k){
                deque.offer(nums[i]);
                sum += nums[i];
                if (deque.size() == k){
                    maxAverage = Double.max(sum,maxAverage);
                }
            }else {
                int tmp = deque.poll();
                sum = sum - tmp + nums[i];
                deque.offer(nums[i]);
                maxAverage = Double.max(sum,maxAverage);
            }
        }
        return maxAverage / k;
    }


//    public boolean buddyStrings(String A, String B) {
//
//    }

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abaababaab"));
        System.out.println(reverseVowels("hello"));
        int[] arr = new int[]{1,12,-5,-6,50,3};
        System.out.println(findMaxAverage(arr,4));

    }



}
