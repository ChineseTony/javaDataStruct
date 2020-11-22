package com.tom.string;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author WangTao
 */
public class SwtichCase {

    public static void main(String[] args) {
//        method(null);

        String a = "abc";
        String b = "abc";
        System.out.println(buddyStrings(a,b));
        System.out.println(getCommon(a,b));
        String[] strings = new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strings));


        Arrays.stream(uncommonFromSentences(
                "d b zu d t","udb zu ap"))
                .forEach(System.out::println);
    }

    /**
     *  字符串做switch case 语句需要判断是否为空
     * @param s 字符串
     */
    public static void method(String s){
        switch(s) {
            case "sth":
                System.out.println("it's sth");
                break;
            case "null":
                System.out.println("it's null");
                break;
            default:
                System.out.println("it's default");
                break;
        }
    }


    /**
     * @link https://leetcode-cn.com/problems/longest-common-prefix/
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        int len = strs.length;
        if (len == 1){
            return strs[0];
        }
        String s = getCommon(strs[0],strs[1]);
        for (int i = 2; i < len; i++) {
            s = getCommon(s,strs[i]);
        }
        return s;
    }

    private static String getCommon(String a,String b){
        if (StringUtils.isEmpty(a) || StringUtils.isEmpty(b)){
            return "";
        }
        int len1 = a.length();
        int len2 = b.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < len1 && i < len2){
            if (a.charAt(i) == b.charAt(i)) {
                sb.append(a.charAt(i));
            }else {
                break;
            }
            i++;
        }
        return sb.toString();
    }


    public static boolean buddyStrings(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        if (len1 != len2){
            return false;
        }
        for (int i = 0; i < len1; i++) {
            for (int j = i+1; j < len1; j++) {
//                交换 i,j位置
                char[] tmp = a.toCharArray();
                char tmpVal = tmp[i];
                tmp[i] = tmp[j];
                tmp[j] = tmpVal;
                String s = new String(tmp);
                if (s.equals(b)){
                    return true;
                }
            }
        }
        return false;

    }


    public static boolean buddyStrings2(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        if (len1 != len2){
            return false;
        }
        if (a.equals(b)){
            //            完全相等 判断字符串有没有重复字符
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < a.length(); i++) {
                if (set.contains(a.charAt(i))){
                    return  true;
                }
                set.add(a.charAt(i));
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < len1; i++) {
            if (a.charAt(i) != b.charAt(i)){
                result.add(i);
            }
            if (result.size() > 2){
                return false;
            }
        }
        if (result.size() == 2){
            int i = result.get(0);
            int j = result.get(1);
            char[] tmp = a.toCharArray();
            char tmpVal = tmp[i];
            tmp[i] = tmp[j];
            tmp[j] = tmpVal;
            String s = new String(tmp);
            if (s.equals(b)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if (table.get(ch) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String tmp:word1){
            sb1.append(tmp);
        }
        for (String tmp:word2){
            sb2.append(tmp);
        }
        return sb1.toString().equals(sb2.toString());
    }


    public static String[] uncommonFromSentences(String a, String b) {
        String s1[]=a.split(" ");
        String s2[]=b.split(" ");
        Map<String,Integer> map=new HashMap<>();
        List<String> list=new ArrayList<>();
        for(String s:s1){
            map.put(s,map.containsKey(s)?map.get(s)+1:1);
        }
        for(String s:s2){
            map.put(s,map.containsKey(s)?map.get(s)+1:1);
        }
        for(String key:map.keySet()){
            if(map.get(key)==1){
                list.add(key);
            }
        }
        String []res=new String[list.size()];
        for(int i=0;i<res.length;i++){
            res[i]=list.get(i);
        }
        return res;
    }

}
