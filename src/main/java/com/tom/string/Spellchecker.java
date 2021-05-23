package com.tom.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wangtao
 * @date 2021/5/23
 */
public class Spellchecker {

    public String[] spellchecker(String[] wordlist, String[] queries) {
        int len = queries.length;
        String[] result = new String[len];
        Set<String> strings = new HashSet<>();
        int wordLen = wordlist.length;
        Map<String,String> map = new HashMap<>(wordLen);
        Map<String,String> yuanMap = new HashMap<>(wordLen);
        for (int i = 0; i < wordlist.length; i++) {
            strings.add(wordlist[i]);
            if (!map.containsKey(wordlist[i].toLowerCase())) {
                map.put(wordlist[i].toLowerCase(), wordlist[i]);
            }
            String tmp = toYuanLowerCase(wordlist[i]).toLowerCase();
            if (!yuanMap.containsKey(tmp)) {
                yuanMap.put(tmp, wordlist[i]);
            }
        }
        int index = 0;
        for (int i = 0; i < len; i++) {
            if (strings.contains(queries[i])){
                result[index++] = queries[i];
            }else if (map.containsKey(queries[i].toLowerCase())){
                result[index++] = map.get(queries[i].toLowerCase());
            }else if (yuanMap.containsKey(toYuanLowerCase(queries[i]).toLowerCase())){
                result[index++] = yuanMap.get(toYuanLowerCase(queries[i]).toLowerCase());
            }else {
                result[index++] = "";
            }
        }
        return result;

    }

    private String toYuanLowerCase(String s){
        if (s == null || s.length() <= 0){
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if (isYuanYin(Character.toLowerCase(chars[i]))){
                chars[i] = '*';
            }
        }
        return new String(chars);
    }

    private boolean isYuanYin(char c){
        return c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u';
    }


    public static void main(String[] args) {
        String[] wordlist = new String[]{"KiTe", "kite", "hare", "Hare"};
        String[] queries = new String[]{
                "kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};
        String[] spellchecker = new Spellchecker().spellchecker(wordlist, queries);

        for (String s:spellchecker){
            System.out.println(s);
        }


    }
}
