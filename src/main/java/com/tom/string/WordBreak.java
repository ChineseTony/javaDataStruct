package com.tom.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class WordBreak {

    private WordBreak(){

    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        for (String tmp:wordDict){
            s = s.replaceAll(tmp,"");
        }
        return s.length() == 0;

    }


    public int findString(String[] words, String s) {
        int i = 0;
        for(String tmp: words){
            if(s.equals(tmp)){
                return i;
            }
            i++;
        }
        return -1;

    }


    public static boolean detectCapitalUse(String word) {
        if(word == null || word.length() == 0){
            return false;
        }
        int len = word.length();
        if (len == 1){
            return true;
        }
        char c = word.charAt(0);
        boolean lower = Character.isLowerCase(c);
        int upperLen = 0;
//        全部是大写或者全部是小写
        int lowerLen = 0;
        for (int i = 1; i <  len; i++) {
            if (Character.isLowerCase(word.charAt(i))){
                lowerLen ++;
            }else {
                upperLen++;
            }

        }
        return lower ? lowerLen == len -1 : (lowerLen == len -1 || upperLen == len -1);

    }


    public static boolean checkValidString(String s) {
        if(s == null || s.length() == 0){
            return  true;
        }
        int len = s.length();
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star= new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '('){
                left.push(i);
            }else if (c == ')'){
                if (!left.isEmpty()){
                    left.pop();
                }else if (!star.isEmpty()){
                    star.pop();
                }else {
                    return false;
                }
            }else if (c == '*'){
                star.push(i);
            }
        }
        while (!left.isEmpty() && !star.isEmpty()){
            if (left.pop() > star.pop()){
                return false;
            }
        }
        return left.isEmpty();
    }

    public static void main(String[] args) {
        String s ="cars";
        String[] arr = new String[]{"car", "ca", "rs"};
        List<String> lists = new ArrayList<>(Arrays.asList(arr));
        System.out.println(wordBreak(s,lists));

        System.out.println(detectCapitalUse("FlaG"));
        System.out.println(checkValidString("(***"));
    }
}
