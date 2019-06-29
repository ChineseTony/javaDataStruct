package com.tom.string;

/**
 * @author WangTao
 * String StringBuffer StringBuilder 区别
 */
public class StringAndStringBuffer {

    public static void main(String[] args) {
        String s = "abc";
        int times = 100;
        //因为String 是不可变的类 字符串数组是被final关键字修饰 字符串拼接 会在java堆中产生对象
        //不建议在循环里面做字符串拼接
        for (int i = 0; i < times; i++) {
            s += i;
        }
        System.out.println(s);
        //
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < times; i++) {
            s2.append(i);
        }
        System.out.println(s2);
        //StringBuffer 线程安全的 所有的方法都被synchronized关键字修饰
        StringBuffer stringBuffer = new StringBuffer();

    }
}
