package com.tom;


import java.util.Arrays;


/**
 * @author
 *
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 * 输入: [3, 30, 34, 5, 9]
 * 输出: "30 3 34 59"
 */
public class Main {
//    选首字母最小的 然后判断后面字符顺序 3 30 34 5 9
//    3 39 34 30 333   3 30  339 34 39
    public static String getMin(int[] arr){
        if(arr == null || arr.length == 0){
            return "";
        }
        int len = arr.length;
        String[] arrs = new String[len];
        for (int i = 0; i < len ; i++) {
            arrs[i] = String.valueOf(arr[i]);
        }
        Arrays.sort(arrs, (s1,s2) -> {
            int a = Integer.parseInt("" + s1 + s2);
            int b = Integer.parseInt("" + s2 + s1);
            return Integer.compare(a,b);

        });
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuffer.append(arrs[i]);
        }
        return stringBuffer.toString();

//
    }

    public String printMinNumber(int [] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int a = Integer.parseInt("" + numbers[i] + numbers[j]);
                int b = Integer.parseInt("" + numbers[j] + numbers[i]);
                if (a > b) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            str.append(numbers[i]);
        }
        return str.toString();
    }

        public static void main(String[] args) {
        int[] arr = new int[]{3,30,34,5,9};
        String s = getMin(arr);
        System.out.println(s);
        System.out.println("3033459".equals(s));

    }
}
