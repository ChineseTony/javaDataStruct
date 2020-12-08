package com.tom.string;


/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/rotate-string/
 */
public class RotateString {


//    todo 优化算法 KMP算法
    public static boolean rotateString(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        if (len1 == len2){
            if (len1 == 0){
                return true;
            }
            while (len1-- >= 0){
                int len = a.length();
                a = a.substring(1,len) + a.charAt(0);
                if (a.equals(b)){
                    return true;
                }
            }
        }
        return false;
    }


    public String reverseOnlyLetters(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        int i=0,j = s.length()-1;
        char[] chars = s.toCharArray();
        while (i < j){
            if (!isCharacter(chars[i])){
                i++;
            }else if (!isCharacter(chars[j])){
                j--;
            }else {
                char tmp = chars[j];
                chars[j] = chars[i];
                chars[i] = tmp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    private boolean isCharacter(char c){
        return c >= 'a' && c <= 'z' || (c >= 'A' && c <= 'Z');
    }



    public static void main(String[] args) {
        String a = "abcde";
        String b = "cdeab";
        System.out.println(rotateString(a,b));
    }
}
