package com.tom.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/word-pattern/
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 思路把 a - dog b - cat 对应起来
 */
public class WordPattern {

    public static boolean wordPattern(String pattern, String s) {
        String[] map = new String[26];
        String[]str = s.split("\t");
        if(str.length!=pattern.length())   {
            return false;
        }
        Set<String> used = new HashSet<>();
        for(int i=0;i<pattern.length();i++){
            int index = pattern.charAt(i)-'a';
            if(map[index]==null && !used.contains(str[i])){
                map[index] = str[i];
                used.add(str[i]);
            }else{
                if((map[index]==null && used.contains(str[i]))
                        || !map[index].equals(str[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern  = "abba";
        String str = "dog cat cat fish";
        System.out.println(wordPattern(pattern,str));

    }
}
