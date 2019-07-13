package com.tom.oj;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author WangTao
 * leetcode 804. 唯一摩尔斯密码词
 *
 * 解决思路 运用集合的唯一性
 *
 */
public class MorseRepresentations {



    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = new String[]
                {".-","-...","-.-.","-..",".",
                        "..-.","--.","....","..",
                        ".---","-.-",".-..","--","-.",
                        "---",".--.","--.-",".-.","...",
                        "-","..-","...-",".--","-..-",
                        "-.--","--.."};
        Set<String> set = new TreeSet<>();
        for(String s : words){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append(codes[s.charAt(i)-'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
