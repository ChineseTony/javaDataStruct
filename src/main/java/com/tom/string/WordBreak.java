package com.tom.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        String s ="cars";
        String[] arr = new String[]{"car", "ca", "rs"};
        List<String> lists = new ArrayList<>(Arrays.asList(arr));
        System.out.println(wordBreak(s,lists));

        System.out.println(detectCapitalUse("FlaG"));
    }
}
