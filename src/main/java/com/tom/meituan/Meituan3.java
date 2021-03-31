package com.tom.meituan;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 6 4
 * 2 3 1 2 3 3
 * 1
 * 2
 * 3
 * 4
 */
public class Meituan3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        int m = scanner.nextInt();
        int len = times;
        int[] arr = new int[times];
        int index = 0;
        while (times -- > 0){
            arr[index ++] = scanner.nextInt();
        }
        while (m-- > 0){
            int tmp = scanner.nextInt();
            int start = -1;
            int end = -1;
            for (int k = 0; k < len; k++) {
                if (tmp == arr[k]){
                    start = k;
                    break;
                }
            }
            for (int k = len-1; k >= 0; k--) {
                if (tmp == arr[k]){
                    end = k;
                    break;
                }
            }
            if (start != -1 && end != -1){
                System.out.println((start+1)+" "+(end+1));
            }else {
                System.out.println(0);
            }
        }
    }


}
