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


    public String reverseWords(String s) {
        if(s == null || s.length() == 0){
            return s;
        }

        String[] tmp = s.split(" ");
        int len = tmp.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(reverse(tmp[i]));
            if (i != len-1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }


    public  String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }


    /**
     * @link https://leetcode-cn.com/problems/check-if-a-w
     * ord-occurs-as-a-prefix-of-any-word-in-a-sentence/
     * @param sentence
     * @param searchWord
     * @return
     */
    public static int isPrefixOfWord(String sentence, String searchWord) {
        if(sentence == null || sentence.length() == 0){
            return -1;
        }
        if(searchWord == null || searchWord.length() == 0){
            return -1;
        }
        String[] tmp = sentence.split(" ");
        int len = tmp.length;
        for (int i = 0; i < len; i++) {
            String string = tmp[i];
            if (isPrefixOf(string,searchWord)){
                return i+1;
            }

        }
        return -1;
    }

    private static boolean isPrefixOf(String str,String searchWord){
        int len1 = str.length();
        int len2 = searchWord.length();
        int i = 0;
        while (i < len1 && i < len2){
            if (str.charAt(i) != searchWord.charAt(i)){
                return false;
            }
            i++;
        }
        if (i == len2 || len1 == len2){
            return true;
        }else {
            return false;
        }

    }


    public static int maxPower(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int i=0;
        int result = 0;
        int count = 1;
        while (i < len){
            if (i < len-1 && s.charAt(i) != s.charAt(i+1)){
                result = Math.max(result,count);
                count = 1;
            }else if (i != len-1){
                count++;
            }
            i++;
        }
        result = Math.max(result,count);
        return result;
    }

    public static void main(String[] args) {
        String pattern  = "abba";
        String str = "dog cat cat fish";
        System.out.println(wordPattern(pattern,str));


        String sentence  = "i love eating burger";
        String searchWord = "burg";
        System.out.println(isPrefixOfWord(sentence,searchWord));

        System.out.println(maxPower("t"));

    }
}
