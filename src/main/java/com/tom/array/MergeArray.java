package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WangTao
 */
public class MergeArray {


    /**
     *
     * @param a 有序数组
     * @param b 有序数组
     * @return 任然有序数组
     */
    public static int[] merge(int[] a,int[] b){
        int length1 = a.length;
        int length2 = b.length;
        int[] c = new int[length1+length2];
        int i = 0 ,j = 0,index =0;
        while (i < length1 && j < length2){
            if(a[i] <= b[j]){
                c[index++] = a[i++];
            }else {
                c[index++] = b[j++];
            }
        }
        while (i < length1){
            c[index++] = a[i++];
        }
        while (j < length2){
            c[index++] = b[j++];
        }
        return c;
    }


    /**
     * 39. 恢复旋转排序数组
     * 中文
     * English
     * 给定一个旋转排序数组，在原地恢复其排序。
     *
     * 样例
     * Example1:
     * [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
     * Example2:
     * [6,8,9,1,2] -> [1,2,6,8,9]
     * @param nums
     */
    public static void recoverRotatedSortedArray(List<Integer> nums) {

    }

    public static void main(String[] args) {
        int[] a = {1,3,5};
        int[] b = {2,4,6};
        int[] c = merge(a,b);
        for (int i : c){
            System.out.print(i+" ");
        }
        System.out.println();
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(1);
        list.add(2);
        list.add(3);
        recoverRotatedSortedArray(list);
        for (Integer i : list){
            System.out.print(i+" ");
        }
    }
}
