package com.tom.util;

/**
 * @author WangTao
 */
public class IpUtil {

    public static boolean isIp(String s){
        if (s == null || s.length() <= 0){
            return false;
        }
        String[] strings = s.split("\\.");
        if (strings.length != 4){
            return false;
        }
        int length = strings.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < strings[j].length(); j++) {
                if (strings[i].charAt(j) < '0' || strings[i].charAt(j) > '9'){
                    return false;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            if (i == 0){
                int temp = Integer.parseInt(strings[i]);
                if (temp <= 1 || temp > 255){
                    return false;
                }
            }else {
                int temp = Integer.parseInt(strings[i]);
                if (temp <0 || temp > 255){
                    return false;
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        //true
        System.out.println(IpUtil.isIp("255.255.255.255"));
        //false
//        System.out.println(IpUtil.isIp("055.255.255.255"));
        //false
        System.out.println(IpUtil.isIp("255.255.255.a"));
        //false
        System.out.println(IpUtil.isIp("255.255.a"));
        //false
        System.out.println(IpUtil.isIp("255.255.255.266"));

    }


}
