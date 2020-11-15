package com.tom.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKdigits {


    /**
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (num == null || num.length() <= 0 || k <= 0){
            return num;
        }
        String tmp = num;
        long result = 0;
        while (k-- > 0){
//            删除一位数
            int len = tmp.length();
            for (int i = 0; i < len; i++) {
                String str = tmp.substring(0, i)+tmp.substring(i+1);
                if (i == 0){
                    result = isEmpty(str) ? 0 : Long.parseLong(str) ;
                }else {
                    result = Long.min(result, Long.parseLong(str));
                }
            }
            tmp = String.valueOf(result);
        }
        return String.valueOf(result);

    }

    private static boolean isEmpty(String str){
        return "".equals(str.trim());
    }


    public static String removeKdigits2(String num, int k) {
        if (num == null || num.length() <= 0 || k <= 0){
            return num;
        }
        Deque<Character> deque = new ArrayDeque<>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();

    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("10",2));
        System.out.println(removeKdigits2("10",2));
    }
}
