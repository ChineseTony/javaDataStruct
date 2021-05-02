package com.tom.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/count-sorted-vowel-strings/
 */
public class CountVowelStrings {
    private List<String> result = new ArrayList<>();

    public List<String> getAll(String s,int len){
        dfs(s,new StringBuilder(),0,len);
        return result;
    }

    private void dfs(String s,StringBuilder tmpStr,int i,int index){
        if (index == tmpStr.length()){
            result.add(new String(tmpStr));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            tmpStr.append(s.charAt(j));
            dfs(s,tmpStr,j, index);
            tmpStr.deleteCharAt(tmpStr.length()-1);
        }
    }

    public int countVowelStrings(int n) {
        dfs("aeiou",new StringBuilder(),0,n);
        return result.size();
    }

    public int countVowelStrings2(int n){
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }

    public int minimumSwap(String s1, String s2) {
        int len = s1.length(), xy = 0, yx = 0;
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        for(int i = 0; i < len; i ++) {
            if(cs1[i] == cs2[i]) {
                continue;
            }else if(cs1[i] == 'x') {
                xy ++;
            }else {
                yx ++;
            }
        }
        return ((xy + yx) & 1) == 1 ? -1 : (xy + 1) / 2 + (yx + 1) / 2;
    }


    public static void main(String[] args) {
        CountVowelStrings countVowelStrings = new CountVowelStrings();
        List<String> aeiou = countVowelStrings.getAll("aeiou", 2);
        System.out.println(aeiou.size());
        for(String tmp:aeiou){
            System.out.println(tmp);
        }
    }
}
