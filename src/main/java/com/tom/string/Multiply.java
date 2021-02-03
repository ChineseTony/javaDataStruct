package com.tom.string;

import java.util.ArrayList;
import java.util.List;

public class Multiply {

    private Multiply(){

    }


    public static String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0){
            return num1;
        }
        if (num2 == null || num2.length() == 0){
            return num1;
        }
        if ("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        boolean flag = num1.length() > num2.length();
        if (!flag){
            StringBuilder s3 = new StringBuilder();
            s3.append(num1).append(num2);      //将字符串1和2合并
            num2 = s3.substring(0,num1.length());  //str2从合并的字符串中截取str1
            num1 = s3.substring(num1.length());
        }
        int len1 = num1.length();
        int len2 = num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int i = len1 - 1;
        int j = len2 - 1;
        int index = 0;
        List<String> list = new ArrayList<>();
        while (j >= 0){
            int tmp = chars2[j] - '0';
            int k = i;
            StringBuffer sb = new StringBuffer();
            int  jin = 0;
            while (k >= 0){
                int mutl = tmp * (chars1[k] - '0') + jin;
                sb.append(mutl % 10 );
                jin = mutl / 10;
                k--;
            }
            if (jin != 0){
                sb.append(jin);
            }
            StringBuffer s = sb.reverse();
            int tmpValue = index;
            while (tmpValue > 0){
                s.append('0');
                tmpValue--;
            }
            index++;
            list.add(s.toString());
            j--;
        }
        int len = list.size();
        if (len == 1){
            return list.get(0);
        }else {
            String tmp = "0";
            for (String s:list){
                tmp = add(tmp,s);
            }
            return tmp;
        }
    }


    private static String add(String num1,String num2){
        if (num1 == null || num1.length() == 0){
            return num1;
        }
        if (num2 == null || num2.length() == 0){
            return num1;
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int i = len1 - 1;
        int j = len2 - 1;
        int  jin = 0;
        StringBuffer sb = new StringBuffer();
        while (j >= 0 && i >= 0){
            int tmp = num1.charAt(i) - '0';
            int mutl = tmp + (num2.charAt(j) - '0') + jin;
            sb.append(mutl % 10);
            jin = mutl / 10;
            i--;
            j--;
        }
        while (i >= 0){
            int tmp = num1.charAt(i) - '0';
            int mutl = tmp  + jin;
            sb.append(mutl % 10);
            jin = mutl / 10;
            i--;
        }
        while (j >= 0){
            int tmp = num2.charAt(j) - '0';
            int mutl = tmp  + jin;
            sb.append(mutl % 10);
            jin = mutl / 10;
            j--;
        }
        if (jin != 0){
            sb.append(jin);
        }
        return sb.reverse().toString();
    }


    public static String multiply2(String num1, String num2) {
        /**
         num1的第i位(高位从0开始)和num2的第j位相乘的结果在乘积中的位置是[i+j, i+j+1]
         例: 123 * 45,  123的第1位 2 和45的第0位 4 乘积 08 存放在结果的第[1, 2]位中
         index:    0 1 2 3 4

         1 2 3
         *     4 5
         ---------
         1 5
         1 0
         0 5
         ---------
         0 6 1 5
         1 2
         0 8
         0 4
         ---------
         0 5 5 3 5
         这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中
         **/
        int n1 = num1.length()-1;
        int n2 = num2.length()-1;
        if(n1 < 0 || n2 < 0) {
            return "";
        }
        int[] mul = new int[n1+n2+2];
        for(int i = n1; i >= 0; --i) {
            for(int j = n2; j >= 0; --j) {
                int bitmul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                bitmul += mul[i+j+1]; // 先加低位判断是否有新的进位
                mul[i+j] += bitmul / 10;
                mul[i+j+1] = bitmul % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 去掉前导0
        while(i < mul.length-1 && mul[i] == 0) {
            i++;
        }
        for(; i < mul.length; ++i) {
            sb.append(mul[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String num1 = "0", num2 = "123";
        System.out.println(multiply(num1,num2));
        num1 = "45";
        System.out.println(multiply(num1,num2));
        System.out.println(multiply2(num1,num2));

    }
}
