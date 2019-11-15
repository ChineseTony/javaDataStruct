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

    //最长不重复子串的长度
    public static int longestSubsting(String s){
        int curLength=0;
        int maxLength=0;
        int[] position = new int[26];
        for (int i = 0; i < 26; i++) {
            position[i]=-1;
        }
        for (int i = 0; i < s.length(); i++) {
            int preIndex = position[s.charAt(i)-'a'];
            if (preIndex <0 || i-preIndex > curLength){
                curLength++;
            }else {
                if (curLength>maxLength){
                    maxLength=curLength;
                }
                curLength=i-preIndex;
            }
            position[s.charAt(i)-'a']=i;
        }
        if (curLength > maxLength){
            maxLength=curLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "I am Student.";
        System.out.println(ReplaceBlack.replaceBlack(s));
        System.out.println(ReplaceBlack.longestSubsting("abcda"));
    }
}
