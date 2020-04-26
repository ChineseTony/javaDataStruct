package com.tom;

import java.util.Scanner;

/**
 * @author WangTao
 */
public class Test2 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        int i;
        int s1=0,s2=0;
        Scanner scanner = new Scanner(System.in);
        for (int j = 0; j < arr.length; j++) {

            System.out.println("请输入第"+(j+1)+"个数");
            arr[j] = scanner.nextInt();
            if(arr[j] < 0){
                s1 += arr[j];
            }else {
                s2 += arr[j];
            }
        }
        scanner.close();
        System.out.println("正数和"+s2);
        System.out.println("负数和"+s1);
    }
}
