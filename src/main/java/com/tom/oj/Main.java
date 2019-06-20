package com.tom.oj;

/**
 * @author WangTao
 *
 * 6 2
 * 1 2 3 4 5 6
 * 5 6 1 2 3 4
 */
import java.util.Scanner;

public class Main{

    private static void myreverse(int[] a,int from,int to){
        while (from < to){
            int temp = a[from];
            a[from++] = a[to];
            a[to--] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int change = (in.nextInt()) % num;
        change = num - change;
        int[] list = new int[num];
        for (int i = 0; i < list.length; i++) {
            list[i] = in.nextInt();
        }
        if(change != num){
            myreverse( list,0,change-1);
            myreverse(list, change,list.length-1);
            myreverse(list,0,list.length-1);
        }
        printArray(list);
    }

    private static void printArray(int[] list){
        for (int i = 0; i < list.length-1; i++) {
            System.out.print(list[i]+" ");
        }
        System.out.println(list[list.length-1]);
    }
}