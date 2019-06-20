package com.tom.string;

/**
 * @author WangTao
 */
public class SwtichCase {

    public static void main(String[] args) {
        method(null);
    }

    /**
     *  字符串做switch case 语句需要判断是否为空
     * @param s 字符串
     */
    public static void method(String s){
        switch(s) {
            case "sth":
                System.out.println("it's sth");
                break;
            case "null":
                System.out.println("it's null");
                break;
            default:
                System.out.println("it's default");
                break;
        }
    }
}
