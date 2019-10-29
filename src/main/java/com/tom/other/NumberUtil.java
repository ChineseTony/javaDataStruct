package com.tom.other;

/**
 * @author WangTao
 *
 */
public class NumberUtil {

    public static boolean isNumber(String s){
        if (s == null || s.length() <= 0){
            return false;
        }
        int length = s.length();
        boolean numeric = isSignedInteger(s);
        int i;
        for (i = 0; i < length; i++) {
            //有小数点
            if (s.charAt(i) == '.') {
                numeric = isUnSignedInteger(s.substring(i+1)) || numeric;
            }
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E'){
                numeric = numeric && isUnSignedInteger(s.substring(i+1));
            }
        }
        return numeric && i == length;
    }

    private static boolean isSignedInteger(String s){
        if (s == null || s.length() <= 0){
            return false;
        }
        int length = s.length();
        int j=0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-'){
                j++;
            }
        }
        return isUnSignedInteger(s.substring(j));
    }


    private static boolean isUnSignedInteger(String s){
        if (s == null || s.length() <= 0){
            return false;
        }
        int length = s.length();
        int count=0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) < '9'){
                count++;
            }
        }
        //存在0-9的数字
        return count > 0;
    }

    public static void main(String[] args) {
        System.out.println(NumberUtil.isNumber("-123"));
        System.out.println(NumberUtil.isNumber("-1E-16"));
        System.out.println(NumberUtil.isNumber("5e2"));
        System.out.println(NumberUtil.isNumber("3.1416"));
    }

}
