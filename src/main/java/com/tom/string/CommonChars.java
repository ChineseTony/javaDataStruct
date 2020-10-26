package com.tom.string;

import java.util.ArrayList;
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


    public static void main(String[] args) {
        String[] arr = new String[]{"bella","label","roller"};
        commonChars(arr).forEach(v -> System.out.print(v + "\t"));

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
