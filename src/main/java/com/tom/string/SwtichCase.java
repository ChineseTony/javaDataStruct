package com.tom.string;

import javax.naming.ldap.HasControls;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WangTao
 */
public class SwtichCase {

    public static void main(String[] args) {
//        method(null);

        String a = "abc";
        String b = "bac";
        System.out.println(buddyStrings(a,b));
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
}
