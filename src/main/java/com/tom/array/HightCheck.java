package com.tom.array;

import java.util.Arrays;

/**
 * @author WangTao
 * 输入：[1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 */
public class HightCheck {

    public static int heightChecker(int[] heights) {
        int number = 0;
        int length = heights.length;
        int[] tmp = new int[length];
        System.arraycopy(heights,0,tmp,0,length);
        Arrays.sort(heights);
        for (int i = 0; i < length; i++) {
            if (tmp[i] != heights[i]){
                number++;
            }
        }
        return number;
    }


    //基数排序思想

    public static int heightChecker2(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (heights[j++] != i){
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,1,4,2,1,3};
        System.out.println(heightChecker(a));
    }
}
