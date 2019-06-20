package com.tom.oj;

/**
 * @author WangTao
 */
public class Solution {

    public static void main(String[] args) {
        int i = 6;
        int times = 2;
        int[] a = new int[i];
        for (int j = 0; j < i; j++) {
            a[j] = j+1;
        }
        printArray(a);
        for (int j = 0; j < times; j++) {
            reverse(a);
        }
        printArray(a);

    }

    private static void reverse(int[] a){
        int temp = a[a.length-1];
        int i;
        for (i = a.length -1; i > 0; i--) {
            a[i] = a[i-1];
        }
        a[0] = temp;
    }

    private static void printArray(int[] a){
        for (int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }


}
