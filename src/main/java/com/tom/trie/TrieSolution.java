package com.tom.trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrieSolution {


    /**
     * @link https://leetcode-cn.com/problems/longest-word-in-dictionary/
     * @param words
     * //        "eyj" // TODO: 2020/11/14 // FIXME: 2020/11/14
     * @return
     */
    public static String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String s:words){
            trie.add(s);
        }
        List<String> tmp = trie.getLastWord();
        if (tmp.size() != 0){
            return tmp.get(0);
        }else {
            return "";
        }
    }

    public static String longestWord2(String[] words) {
        Arrays.sort(words);
        String result = "";
        Set<String> set = new HashSet<>();
        for (String tmp:words){
            if (tmp.length() == 1 || set.contains(tmp.substring(0,tmp.length()-1))){
                result = tmp.length() > result.length() ? tmp : result;
                set.add(tmp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[]{
                "ogz","eyj","e","ey","hmn","v","hm","ogznkb","ogzn","hmnm","eyjuo","vuq","ogznk","og","eyjuoi","d"
        };

        System.out.println(longestWord(words));
        System.out.println(longestWord2(words));
    }
}
