package com.tom.oj.tencent.ch2;

import java.util.Scanner;

public class Tencent4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t= scanner.nextInt();
        while (t-- > 0){
            int n = scanner.nextInt();
            int[] all = new int[n];
            for (int i = 0; i < n; i++) {
                all[i] = scanner.nextInt();
            }
            int[] cost = new int[n];
            for (int i = 0; i < n; i++) {
                cost[i] = scanner.nextInt();
            }
        }
    }
}
