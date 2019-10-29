package com.tom.other;

/**
 * @author WangTao
 */
public class ReorderOddEven {


    //调整顺序使得奇数位于偶数前面
    public static void reorderOddEven(int[] arr){
        if (arr == null || arr.length <= 0){
            return;
        }
        int i = 0;
        int j = arr.length-1;
        while (i < j){
            //找到第一个奇数
            while (i < j && !isOdd(arr[i])){
                i++;
            }
            //找到第一个偶数
            while (i < j && isOdd(arr[j])){
                j--;
            }
            if (i < j){
                swap(arr,i,j);
            }
        }
    }

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean isOdd(int i){
        return (i & 0x1) == 0;
    }

    private static void printArray(int[] arr){
        for (int i:arr){
            System.out.print(i+"\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,6,3,5};
        printArray(arr);
        ReorderOddEven.reorderOddEven(arr);
        printArray(arr);
    }
}
