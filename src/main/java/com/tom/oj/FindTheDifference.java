package com.tom.oj;

import java.util.HashSet;
import java.util.Set;

public class FindTheDifference {

    private FindTheDifference(){

    }

    public static char findTheDifference(String s, String t) {
        char[] arr = new char[26];
        for (char c:s.toCharArray()){
            arr[c-'a']++;
        }
        for (char c:t.toCharArray()){
            if (arr[c-'a']-- <= 0){
                return c;
            }
        }
        return ' ';
    }

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int len = prices.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if(prices[j] > prices[i]){
                    max = Math.max(max,prices[j] - prices[i]);
                }
            }
        }
        return max;
    }

    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int res = 0, min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            //更新最小值
            if(prices[i] <= min) {
                min = prices[i];
            }else {
                //
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int left = 0,right = 0;
        Set<Character> set = new HashSet<>();
        int max = 0;
        while (left < len && right < len){
            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
                max = Math.max(max,right-left);
            }else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(FindTheDifference.findTheDifference("ab","aa"));
        System.out.println(lengthOfLongestSubstring("aaaaa"));
    }
}
