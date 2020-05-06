package com.tom.oj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author WangTao
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 */
public class FirstUniqChar {


    //使用hash表的思想解决
    public static int firstUniqChar(String s) {
        int[] freq = new int [26];
        int length= s.length();
        for (int i = 0; i < length; i++){
            freq [s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < length; i++){
            if(freq [s.charAt(i) - 'a'] == 1){
                return i;
            }
        }
        return -1;

    }

    public boolean isUnique(String astr) {
        if (astr == null || astr.length() == 0){
            return true;
        }
        char[] chars = astr.toCharArray();
        Set<Character> set = new HashSet<>(16);
        for (int i = 0; i < chars.length; i++) {
            if (!set.contains(chars[i])){
                set.add(chars[i]);
            }else{
                return false;
            }
        }
        return true;
    }

    public static int firstUniqChar2(String s){
        Map<Character, Integer> count = new HashMap<>(26);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;

    }

}
