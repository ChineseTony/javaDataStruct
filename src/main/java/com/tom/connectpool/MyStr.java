package com.tom.connectpool;

/**
 * @author wangtao
 * @date 2021/8/6
 */
public class MyStr {
    public static void main(String[] args) {
        String s = "abc";
        String c = s;
        System.out.println(s == c.intern());
        System.out.println(s == c);
        String b = new String(c);
        System.out.println(b == s);
        System.out.println(b.intern() == c);
    }
}
