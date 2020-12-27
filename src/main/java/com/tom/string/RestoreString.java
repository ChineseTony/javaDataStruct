package com.tom.string;

public class RestoreString {

    public static String restoreString(String s, int[] indices) {
        if(s == null || s.length() ==0){
            return s;
        }
        int len = indices.length;
        char[] tmp = new char[len];

        for (int i = 0; i < len; i++) {
            tmp[indices[i]] = s.charAt(i);
        }
        return new String(tmp);
    }

    public static void main(String[] args) {

    }

}
