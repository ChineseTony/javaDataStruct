package com.tom.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;


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


    /**
     * @link https://leetcode-cn.com/problems/positions-of-large-groups/
     * @param s
     * @return
     */
    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        if (s == null || s.length() == 0){
            return  result;
        }
        int len = s.length();
        int i = 0;
        while (i < len){
            int j = i+1;
            char tmp = s.charAt(i);
            while (j < len && s.charAt(j) == tmp){
                j++;
            }
            if (j - i  >= 3){
                List<Integer>  list = new ArrayList<>();
                list.add(i);
                list.add(j-1);
                result.add(list);
                i = j;
            }else {
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("102340189234",4));
        System.out.println(removeKdigits2("102340189234",4));

        String tmp =  "abcdddeeeeaabbbcd";
//       [[3,5],[6,9],[12,14]]
        largeGroupPositions(tmp).forEach(
                System.out::println
        );
    }
}
