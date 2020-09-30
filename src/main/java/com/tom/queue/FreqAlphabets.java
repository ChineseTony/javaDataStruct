package com.tom.queue;





import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author wangtao
 *
 * @link https://leetcode-cn.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
 *
 * 给你一个字符串s，它由数字（'0' - '9'）和'#'组成。我们希望按下述规则将s映射为一些小写英文字符：
 *
 * 字符（'a' - 'i'）分别用（'1' -'9'）表示。
 * 字符（'j' - 'z'）分别用（'10#'-'26#'）表示。
 * 返回映射之后形成的新字符串。
 *
 * 题目数据保证映射始终唯一。

 */
public class FreqAlphabets {

    public static String freqAlphabets(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        char[] tmpArr = s.toCharArray();
        int len = tmpArr.length;
        Queue<Character> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (tmpArr[i] != '#'){
                queue.offer(tmpArr[i]);
                while (queue.size() > 2){
                    char tmp = queue.poll() ;
                    sb.append((char)(tmp - '1' + 'a'));
                }
            }else{
                int tmpVal = 0;
                while (!queue.isEmpty()){
                    tmpVal = tmpVal * 10 + (queue.poll() - '0');
                }
                sb.append((char)(tmpVal - 10 + 'j'));
            }
        }
        while (!queue.isEmpty()){
            char tmp = queue.poll();
            sb.append((char)(tmp  - '1' + 'a'));
        }
        return sb.toString();
    }

    public static String freqAlphabets2(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        char[] tmpArr = s.toCharArray();
        int len = tmpArr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i + 2 < len && tmpArr[i+2] == '#'){
                int tmpVal = (tmpArr[i] - '0') * 10 + tmpArr[i+1] - '0';
                sb.append((char)(tmpVal - 10 + 'j'));
                i = i + 2;
            }else{
                sb.append((char)(tmpArr[i] - '1' + 'a'));
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
//  4J
        System.out.println(freqAlphabets2("410#23#"));
//        System.out.println(freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
        System.out.println(
                freqAlphabets2("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#")
        .equals("abcdefghijklmnopqrstuvwxyz"));


    }
}
