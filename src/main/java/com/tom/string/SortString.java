package com.tom.string;


/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/increasing-decreasing-string/
 */
public class SortString {

    private static final int ALL_LOWER_CHARS = 26;

    public static String sortString(String s) {
        if (s == null || s.length() <= 0){
            return null;
        }
        int[] arr = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int tmp = len;
        while (tmp > 0){
            for (int i = 0; i < ALL_LOWER_CHARS; i++) {
                if (arr[i] != 0){
                    sb.append((char) (i + 'a'));
                    arr[i]--;
                    tmp -- ;
                }
            }
            for (int i = ALL_LOWER_CHARS - 1; i >= 0; i--) {
                if (arr[i] != 0){
                    sb.append((char) (i + 'a'));
                    arr[i]--;
                    tmp -- ;
                }
            }
        }
        return sb.toString();

    }



    public static int totalMoney(int n) {
        int sum = 0;
        int t = n % 7;
        int count = n / 7;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 7; j++) {
                sum +=   j+i+1;
                System.out.print((j+i+1)+" + ");
            }
            System.out.println();
        }
        int tmp = count + 1;
        while (t-- > 0){
            sum += tmp ++;
           System.out.print(tmp + "+");
        }
        System.out.println();
        return sum;

    }

    public static void main(String[] args) {
        String tmp = "leetcode";
//        cdelotee
        System.out.println(sortString(tmp));

        System.out.println(totalMoney(20));

    }
}
