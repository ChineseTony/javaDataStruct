package com.tom.oj;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author wangtao
 */
public class PrintStudent {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int studentLen = scanner.nextInt();
        int count = scanner.nextInt();
        Map<Integer, PriorityQueue<String>> map = new HashMap<>();
        int tmp = studentLen;
        for (int i = 0; i < count; i++) {
            PriorityQueue<String> queue = new PriorityQueue<>();
            map.put(i+1,queue);
        }
        while (tmp -- >0){
            String s = scanner.next();
            int times = scanner.nextInt();
            while (times-- > 0){
                int course = scanner.nextInt();
                PriorityQueue<String> queue = map.get(course);
                queue.add(s);

            }
        }
        for (int i = 0; i < count; i++) {
            PriorityQueue<String> queue = map.get(i+1);
            System.out.println((i+1) + " " + queue.size());
            while (!queue.isEmpty()){
                System.out.println(queue.poll());
            }
        }
        scanner.close();
    }


}
