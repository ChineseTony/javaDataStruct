package com.tom.string;



import java.util.Arrays;


/**
 * @author wangtao
 */
public class LicenseKeyFormatting {


    public static String licenseKeyFormatting(String s, int k) {
        if (s == null || s.length() <=0 || k< 0){
            return null;
        }
        String[] strings = s.split("-");
        System.out.println(Arrays.toString(strings));
        int tmpLen = 0;
        StringBuilder sb = new StringBuilder();
        for (String tmp:strings){
            tmpLen += tmp.length();
            sb.append(tmp.toUpperCase());
        }
        StringBuilder result = new StringBuilder();
        int t = tmpLen % k ;
        int count = tmpLen / k ;
        if (t != 0){
            result.append(sb,0,t);
            if (count != 0) {
                result.append("-");
            }
        }
        for (int i = 1; i < count+1 ; i ++) {
            result.append(sb,t + (i-1) * k,t + i * k);
            if(i != count) {
                result.append("-");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
//        String s = "2-5g-3-J";
        String s = "5F3Z-2e-9-w";
        int k = 4;
        System.out.println(licenseKeyFormatting(s,k));

    }
}
