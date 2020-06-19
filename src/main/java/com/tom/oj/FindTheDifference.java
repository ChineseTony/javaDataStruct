package com.tom.oj;

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

    public static void main(String[] args) {
        System.out.println(FindTheDifference.findTheDifference("ab","aa"));
    }
}
