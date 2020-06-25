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

    public static void main(String[] args) {
        String s ="cars";
        String[] arr = new String[]{"car", "ca", "rs"};
        List<String> lists = new ArrayList<>(Arrays.asList(arr));
        System.out.println(wordBreak(s,lists));
    }
}
