package com.tom.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,Integer> map = new HashMap<>();

        int N = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < N; i++) {
            String[] tmp = scanner.nextLine().split(" ");
            int t = Integer.valueOf(tmp[1]);
            t = Math.max(t,K);
            if (!map.containsKey(tmp[0])){
                map.put(tmp[0],t);
            }else {
                map.put(tmp[0],map.get(tmp[0]) + t);
            }

        }
        int times = scanner.nextInt();
        scanner.nextLine();
        while (times -- > 0){
            String id = scanner.nextLine();

            if (map.get(id) != null){
                System.out.println(map.get(id));
            }else {
                System.out.println("No Info");
            }
        }

    }
}
