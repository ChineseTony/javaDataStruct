package com.tom.string;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/longest-substring-of-all-vowels-in-order/
 */
public class LongestBeautifulSubstring {


    public static int longestBeautifulSubstring(String word) {
        if (word == null || word.length() <= 0){
            return 0;
        }
        int len = word.length();
        Deque<Character> queue = new ArrayDeque<>();
        int result = 0;
        int left = 0;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (queue.isEmpty() || c >= queue.peekLast()){
                if (!queue.contains(c)){
                    queue.offer(c);
                }
                if (queue.size() == 5){
                    result = Math.max(result,i  - left+1);
                }
            }else {
                queue.clear();
                queue.add(c);
                left = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "aeiaaioaaaaeiiiiouuuooaauuaeiu";
        System.out.println(longestBeautifulSubstring(s));
    }
}
