package com.tom.string;




import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstring {


    /**
     *
     * @param s
     * @param k
     * @return
     */
    public static int longestSubstring(String s, int k) {
       return 0;
    }



    public int maxCount(int m, int n, int[][] ops) {

        int[][] arr = new int[m][n];
        for (int[] op: ops) {
            for (int i = 0; i < op[0]; i++) {
                for (int j = 0; j < op[1]; j++) {
                    arr[i][j] += 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == arr[0][0]) {
                    count++;
                }
            }
        }
        return count;

    }

    public int maxCount2(int m, int n, int[][] ops) {
        for (int[] op: ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }


    public static void main(String[] args) {
        String s = "ababbc";
        int k = 2;
        System.out.println(longestSubstring(s, k));
    }
}
