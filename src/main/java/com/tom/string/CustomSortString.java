package com.tom.string;


/**
 * @author wangtao
 */

public class CustomSortString {

    public static String customSortString(String s, String t) {
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[26];
        for(char c:t.toCharArray()){
            chars[c -'a'] ++;
        }
        for (char c:s.toCharArray()){
            for (int i = 0; i < chars[c - 'a']; i++) {
                sb.append(c);
            }
            chars[c - 'a'] = 0;
        }
        for (char c='a';c <= 'z';c++){
            for (int i = 0; i < chars[c - 'a']; i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s  ="cba";
        String t = "abcd";
        System.out.println(customSortString(s,t));

    }
}
