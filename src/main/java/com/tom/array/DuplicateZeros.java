package com.tom.array;

import java.util.Arrays;


public class DuplicateZeros {

    private DuplicateZeros(){

    }

    public static void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0){
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; ) {
            if (arr[i] == 0){
                for (int j = len-1; j > i; j--) {
                    arr[j] = arr[j-1];
                }
                if (i != len-1){
                    arr[i+1] = 0;
                }
                i = i+2;
            }else {
                i++;
            }
        }

    }


    public static void duplicateZeros2(int[] arr) {
        if (arr == null || arr.length == 0){
            return;
        }
        int len = arr.length;
        int i ,count =0;
        for (i = 0; i < len; i++) {
            if (arr[i] == 0){
                count++;
            }
            if (i + count >= len-1){
                break;
            }
        }
        if ((count + i) > len - 1) {
            arr[len-- - 1] = arr[i--];
        }
        /*从i开始填充数据*/
        int j = len - 1;
        while (j > i) {
            if (arr[i] == 0) {
                arr[j] = 0;
                arr[--j] = 0;
            } else {
                arr[j] = arr[i];
            }
            i--;
            j--;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,0,2,3,0,4,5,0};
        duplicateZeros2(nums);
//        1,0,0,2,3,0,0,4
        Arrays.stream(nums).asLongStream().forEach(value ->
            System.out.print(value + "\t")
        );
    }
}
