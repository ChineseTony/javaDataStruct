package com.tom.array;

/**
 * @author WangTao
 *给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 *
 * 你可以返回满足此条件的任何数组作为答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 */
public class SortByParity {

    public static int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length-1;
        while (i < j){

            //左奇右偶
            if (A[i] % 2 == 1 && A[j] % 2 == 0){
                swap(A,i,j);
                j--;
                i++;
                //右奇
            }else if (A[j] % 2 == 1){
                j--;
                //左偶
            }else if (A[i] % 2 == 0){
                i++;
            }else {
                //右偶左奇
                j--;
                i++;
            }
        }
        return A;
    }

    public static void main(String[] args) {

    }

    public static boolean isOdd(int i){
        return  i%2 == 0;
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
