package com.tom.string;


import java.util.Arrays;

/**
 * @author WangTao
 */
public class EqualSubstring {

    public static int equalSubstring(String s, String t, int maxCost) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        if(t == null || t.length() <= 0){
            return 0;
        }
        int len = s.length();
        int[] tmp = new int[len];
        for (int i = 0; i < len; i++) {
            tmp[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        System.out.println(Arrays.toString(tmp));
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (end < len) {
            sum += tmp[end];
            while (sum > maxCost) {
                sum -= tmp[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;

    }


    public static void main(String[] args) {

        String s = "tyiraojpcfuttwblehv", t = "stbtakjkampohttraky";
        int cost = 119;

        System.out.println(equalSubstring(s, t, cost));

    }
}
