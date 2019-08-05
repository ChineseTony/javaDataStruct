package com.tom.array;

import java.util.Arrays;

/**
 * 977. 有序数组的平方r
 * @author WangTao
 *
 * 给定一个按非递减顺序排序的整数数组 MyLinkedListLeetcode，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */
public class SqrtArray {

    public static int[] sortedSquares(int[] A) {
        int length = A.length;
        for (int i = 0; i < length; i++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }


    /**
     * 算法思路  [-4,-1,0,3,10] 将数组分割成 负数和正数部分 进行归并
     * [-4,-1],[0,3,10] 将二个有序的数组进行归并
     * @param A
     * @return
     */
    public static int[] sortedSquares2(int[] A) {
        int length = A.length;
        int j = 0;
        while (j < length && A[j] < 0){
            j++;
        }
        int i = j -1;
        int[] B = new int[length];
        int index = 0;
        while (i >= 0 && j < length){
            if (A[i] * A[i] < A[j] * A[j]){
                B[index++] = A[i] * A[i];
                i--;
            }else {
                B[index++] = A[j] * A[j];
                j++;
            }
        }
        while (i >= 0){
            B[index++] = A[i] * A[i];
            i--;
        }
        while (j < length){
            B[index++] = A[j] * A[j];
            j++;
        }
        return  B;
    }


    public static void main(String[] args) {
        int[] A = new int[]{-4,-1,0,3,10};
        int[] B = sortedSquares2(A);
        for (int i : B){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
