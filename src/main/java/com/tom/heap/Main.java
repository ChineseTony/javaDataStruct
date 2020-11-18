package com.tom.heap;


import java.util.List;
import java.util.Scanner;

/**
 * @author WangTao
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        MyMinHeap<Integer> myMaxHeap = new MyMinHeap<>(N);
        for (int i = 0; i < N; i++) {
            myMaxHeap.insert(scanner.nextInt());
        }
        for (int i = 0; i < M; i++) {
            int index = scanner.nextInt();
            List<Integer> tmp = myMaxHeap.getAllParent(index);
            int len = tmp.size();
            for (int j = 0; j < len; j++) {
                System.out.print(tmp.get(j));
                if (j != len-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        scanner.close();
    }

}

