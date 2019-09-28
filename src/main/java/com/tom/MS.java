package com.tom;

/**
 * @author WangTao
 */
public class MS {

    public static void main(String[] args) {
  /*      boolean a = 127;
        boolean b = 127;
        System.out.println(a + b);*/
        String a = "hello";
        String b = "he"+new String("llo");
//        false
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
