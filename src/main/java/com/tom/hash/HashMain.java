package com.tom.hash;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author WangTao
 * @link https://pintia.cn/problem-sets/15/problems/890
 *
 *
 * 4 11
 * HELLO ANNK ZOE LOLI
 *
 * 3 0 10 9 6 1
 * 3 0 10 9 10 0
 */
public class HashMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int P = scanner.nextInt();
        int[] arr = new int[P];
        for (int i = 0; i < P; i++) {
            arr[i] = 0;
        }
        scanner.nextLine();
        List<Integer> result = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            String tmp = scanner.next();

            int len = tmp.length();
            int tmpVal = (tmp.charAt(len-3) - 'A') * 32 * 32
                    + (tmp.charAt(len-2) - 'A') * 32
                    + (tmp.charAt(len-1) - 'A');

            tmpVal = tmpVal % P;
            if (arr[tmpVal % P] == 0){
                arr[tmpVal] = 1;
            }else {
                //hash冲突
                int t = 1;
                int count = 0;
                while (arr[tmpVal] != 0){
                    int a = t * t * (count % 2 == 0 ? 1 : -1);
                    System.out.println("--->"+a);
                    tmpVal = (tmpVal + a) ;
                    if (count % 2 == 1){
                        t++;
                    }
                    count++;
                    while (tmpVal < 0){
                        tmpVal += P;
                    }
                    while (tmpVal >= P){
                        tmpVal -= P;
                    }
                    System.out.println("---#>"+tmpVal);
                }
                arr[tmpVal] = 1;
            }
            result.add(tmpVal);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(result.get(i));
            if (i != N-1){
                System.out.print(" ");
            }
        }

    }
}
