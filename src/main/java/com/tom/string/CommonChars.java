package com.tom.string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CommonChars {

    private CommonChars(){

    }


    public static List<String> commonChars(String[] arr) {
        List<String> list = new ArrayList<>();
        int[] res = new int[26];
        for (char c : arr[0].toCharArray()) {
            res[c - 'a']++;
        }
        for (int i = 1; i < arr.length; i++) {
            int[] temp = new int[26];
            for (char c : arr[i].toCharArray()) {
                temp[c - 'a']++;
            }
            //求最小值 交集
            for (int j = 0; j < 26; j++) {
                res[j] = Math.min(res[j], temp[j]);
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] > 0) {
                //可能是多个字符
                for (int j = 0; j < res[i]; j++) {
                    list.add(((char) ('a' + i) + ""));
                }
            }
        }
        return list;
    }


    /**
     * @link https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
     * @param s
     * @return
     *
     * ??yw?ipkj?
     *
     * acywaipkja
     */
    public String modifyString(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        int len = s.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {

        }
        return sb.toString();

    }


    /**
     * "abBAcC"  输出 ""
     * @param s
     * @return
     */
    public static String makeGood(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty()){
                stack.offer(s.charAt(i));
            }else {
                char cur = s.charAt(i);
                char next = stack.peekLast();
                if (Character.isLowerCase(cur) && Character.isUpperCase(next)
                        && cur == next+32){
                    stack.pollLast();
                }else if (Character.isUpperCase(s.charAt(i))
                        && Character.isLowerCase(next)  && cur+32 == next ){
                    stack.pollLast();
                }else {
                    stack.offer(cur);
                }
            }
        }
        while (!stack.isEmpty()){
            sb.append(stack.poll());
        }
       return sb.toString();

    }


    public static void main(String[] args) {
        String[] arr = new String[]{"bella","label","roller"};
        commonChars(arr).forEach(v -> System.out.print(v + "\t"));
        System.out.println(makeGood("leEeetcode"));
        System.out.println(makeGood("abBAcC"));

    }
}


class Foo {
    private CountDownLatch c2;
    private CountDownLatch c3;
    public Foo() {
        c2 = new CountDownLatch(1);
        c3 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        c2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        c2.await();
        printSecond.run();
        c3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        c3.await();
        printThird.run();
    }
}
