package com.tom.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    private GroupAnagrams(){

    }


    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> tmp = new HashMap<>();
        for (String str:strs){
            String tmpValue = staticChar(str);
            if (!tmp.containsKey(tmpValue)){
                List<String> list = new ArrayList<>();
                list.add(str);
                tmp.put(tmpValue,list);
            }else {
                tmp.get(tmpValue).add(str);
            }
        }
        for (Map.Entry<String,List<String>> entry:tmp.entrySet()){
            result.add(entry.getValue());
        }
        return result;

    }

    private static String staticChar(String s){
        int[] tmp = new int[26];
        for (char c:s.toCharArray()){
            tmp[c - 'a'] ++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (tmp[i] != 0){
                sb.append((char) (i + 'a')).append(tmp[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] str = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(str).forEach(
                System.out::println
        );
    }
}
