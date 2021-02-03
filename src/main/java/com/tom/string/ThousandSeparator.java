package com.tom.string;

/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/thousand-separator/
 */
public class ThousandSeparator {


    public static String thousandSeparator(int n) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        if(n == 0){
            return "0";
        }
        while(n != 0){
            int t = n % 10;
            sb.append(t);
            n = n / 10;
            count ++;
            if (count % 3 == 0 && n != 0) {
                sb.append('.');
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
//        1,233
        System.out.println(thousandSeparator(233));

    }
}
