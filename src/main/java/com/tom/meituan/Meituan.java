package com.tom.meituan;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 样例输入
 * 2
 * 5
 * 00011
 * 5
 * 11210
 * 样例输出
 * 00001
 * 01210
 *
 * 提示
 * 第一组数据：
 * 替换一块积木，无法使数字串变成回文数字串，因此只能数字串形成的数字最小。
 * 第二组数据：
 * 可以把第一块积木1换成0，也可以把第五块积木0换成1，
 * 从而使得积木是回文积木。又想要积木字典序最小，所以把第一块积木1替换成0。
 * 需要考虑如果是回文字符串  121 -->  需要转成101
 */



public class Meituan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        while (times-- > 0){
            int n = scanner.nextInt();
            String tmp = scanner.next();
//          找到第一个不为0的数进行替换
            char[] chars = tmp.toCharArray();
            if (!isHuiwen(tmp)){
                int i = 0;
//                替换最大数组，然后进行排序 000791   ----> 000017
                int maxIndex = 0;
                while (i < n ){
                    if (chars[i] > chars[maxIndex]){
                        maxIndex = i;
                    }
                    i++;
                }
                if (maxIndex < n){
                    chars[maxIndex] = '0';
                    Arrays.sort(chars);
                }
            }else {
                int i = 0;
                int j = tmp.length() -1;
                while (i < j){
                    Integer a = Integer.parseInt(String.valueOf(chars[i]));
                    Integer b = Integer.parseInt(String.valueOf(chars[j]));
                    if (a > b) {
                        chars[i] = (char) (b + '0');
                    }else {
                        chars[j] = (char) (a + '0');
                    }
                    i++;
                    j--;
                }
//                需要考虑如果是回文字符串  121 -->  需要转成101
                if (tmp.length() % 2 == 1){
                    int t = tmp.length() / 2;
                    chars[t] = '0';
                }
            }
            System.out.println(new String(chars));
        }
    }

    /**
     * 判断是不是可以回文
     * @param tmp
     * @return
     */
    private static boolean isHuiwen(String tmp){
        if (tmp == null || "".equals(tmp)) {
            return false;
        }
        int len = tmp.length();
        int times = 0;
        int i = 0;
        int j = len-1;
        while (i < j){
            if (tmp.charAt(i) != tmp.charAt(j)){
                times++;
            }
            i++;
            j--;
        }
        return times <= 1;

    }
}
