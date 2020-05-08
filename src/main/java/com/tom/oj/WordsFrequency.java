package com.tom.oj;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangTao
 */
public class WordsFrequency {

    private Map<String,Integer> map;

    public WordsFrequency(String[] book) {
        map = new HashMap<>();
        for (String s:book){
            if (!map.containsKey(s)) {
                map.put(s,1);
            }else {
                map.put(s,map.get(s)+1);
            }
        }
    }

    public int get(String word) {
        if(!map.containsKey(word)){
            return 0;
        }else {
            return map.get(word);
        }
    }
}
