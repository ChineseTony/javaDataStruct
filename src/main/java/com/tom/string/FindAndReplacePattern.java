package com.tom.string;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangtao
 */
public class FindAndReplacePattern {

    public static List<String> findAndReplacePattern(
            String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word:words){
            if (isSamePattern(word,pattern)){
                result.add(word);
            }
        }
        return result;
    }

    private static boolean isSamePattern(String word,String pattern){
        Map<Character, Character> m1 = new HashMap<>(26);
        Map<Character, Character> m2 = new HashMap<>(26);
        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!m1.containsKey(w)) {
                m1.put(w, p);
            }
            if (!m2.containsKey(p)){
                m2.put(p, w);
            }
            if (m1.get(w) != p || m2.get(p) != w) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = new String[]{
                "abc","deq","mee","aqq","dkd","ccc"
        };
        String pattern = "abb";
        findAndReplacePattern(words,pattern)
                .forEach(System.out::println);

    }
}
