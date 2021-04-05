package com.tom.oj.tencent;

import java.util.Scanner;

public class Tencent5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        while (count -- > 0){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] arr = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int tmp = scanner.nextInt();
                arr[i] = tmp;
                sum += tmp;
            }
            if (sum % 2 != 0
            ||  sum / 2 > m ){
                System.out.println(-1);
            }else {
                int t = sum / 2;

            }
        }
    }
}
