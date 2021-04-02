package com.tom.array;


/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 */
public class FindLength {

    public static int findLength(int[] a, int[] b) {
        return a.length < b.length ?
                getMaxLength(a,b) : getMaxLength(b,a);


    }

    private static int getMaxLength(int[] a,int[] b){
        int max = 0;
        int aLen = a.length;
        int bLen = b.length;
        for (int i = 1; i <= aLen; i++) {
            max = Math.max(max, getMax(a, 0, b, bLen - i, i));
        }

        for (int i = bLen - aLen; i >= 0; i--) {
            max = Math.max(max, getMax(a, 0, b, i, aLen));
        }
        for(int i=1;i<aLen;i++) {
            max = Math.max(max, getMax(a, i, b, 0, aLen - i));
        }
        return max;
    }


    private static int getMax(int[] a,int i,int[] b,int j,int len){
        int count = 0;
        int max = 0;
        for (int k = 0; k < len; k++) {
            if (a[i+k] == b[j+k]){
                count++;
            }else {
                max = Math.max(count,max);
                count = 0;
            }
        }
        max = Math.max(count,max);
        return max;
    }

    public static void main(String[] args) {
        //模拟2个数组交汇
        int[] a = new int[]{1,2,3,2,1};
        int[] b = new int[]{3,2,1,4,7};
        System.out.println(findLength(a,b));

    }
}
