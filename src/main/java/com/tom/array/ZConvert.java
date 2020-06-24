package com.tom.array;

import java.util.ArrayList;
import java.util.List;

public class ZConvert {

    private ZConvert(){

    }

    public static String convert(String s, int numRows) {
        if(s == null || s.length() == 0){
            return s;
        }
        if(numRows == 1){
            return s;
        }
        int len = s.length();
        int tmp = len / (2 * numRows - 2);
        //求出宽度 把字符放到数组里面
        int width = len - tmp * (2 * numRows - 2);
        if (width == 0){
            width = tmp * (numRows -1);
        }else {
            width = tmp * (numRows -1) + (width < numRows ? 1 : (width-numRows) % numRows  + 1);
        }

        char[][] chars = new char[numRows][width];
        int left = 0;
        int right = 0;
        int i = 0;

        while (right < width){
            //从上往下
            for (int j = left; j < numRows  && i < len; j++) {
                chars[j][right] = s.charAt(i++);
            }
            int index = right;
            //斜向上
            for (int j = numRows-2; j > 0 && i < len; j--) {
                chars[j][++index] = s.charAt(i++);
            }
            right += numRows-1;
        }

        StringBuilder sb = new StringBuilder();
        for (i = 0; i < numRows; i++) {
            for (int j = 0; j < width; j++) {
                if (chars[i][j] != '\0'){
                    sb.append(chars[i][j]);
                }
            }
        }
        return sb.toString();

    }


    public static String convert2(String s, int numRows) {
        if(s == null || s.length() == 0){
            return s;
        }
        if(numRows == 1){
            return s;
        }

        List<StringBuilder> result = new ArrayList<>(numRows);
        for(int i = 0; i < numRows; i++){
            result.add(new StringBuilder());
        }
        int i = 0;
        int flag = 1;
        for (char c:s.toCharArray()){
            result.get(i).append(c);
            i = i + flag;
            if(i == 0 || i == numRows-1){
                flag = -flag;
            }

        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder tmp:result){
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static String addBinary(String a, String b) {


        return "";

    }

    public static void main(String[] args) {
//        System.out.println("PAYPALISHIRING".toLowerCase());
        System.out.println("PAHNAPLSIIGYIR".toLowerCase());
        System.out.println(convert("PAYPALISHIRING",3).toLowerCase());
    }
}
