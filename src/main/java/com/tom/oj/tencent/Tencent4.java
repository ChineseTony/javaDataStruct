package com.tom.oj.tencent;

import java.util.Scanner;

public class Tencent4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        while (count -- > 0){
            int nums = scanner.nextInt();
            int dian = scanner.nextInt();
            int times = 0;
            for (int i = 0; i < nums; i++) {
               int a = scanner.nextInt();
               int haodian = scanner.nextInt();
               if (haodian < dian){
                   times++;
               }else {

               }
            }
            if (times == nums){
//               能无限续航
                System.out.println(-1);
            }else {

            }
        }
    }
}
