package com.tom.string;

/**
 * @author WangTao
 */
public class ReplaceBlack {

    public static String replaceBlack(String s){
        if (s == null || s.length() <= 0){
            return null;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int black = 0;
        for(char c: chars){
            if (c == ' '){
                black++;
            }
        }
        int newLength = length+black*2;
        if (newLength == length){
            return s;
        }
        char[] c = new char[newLength];
        System.arraycopy(chars,0,c,0,length);
        int p = length-1;
        int q = newLength-1;
        while (p >= 0 && q>p){
            if (c[p] == ' '){
                c[q--]='0';
                c[q--]='2';
                c[q--]='%';
            }else {
                c[q--]=c[p];
            }
            p--;
        }
        return new String(c);

    }

    public static void main(String[] args) {
        String s = "I am Student.";
        System.out.println(ReplaceBlack.replaceBlack(s));
    }
}
