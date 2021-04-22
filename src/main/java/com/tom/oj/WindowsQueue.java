package com.tom.oj;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author tom
 * @link https://pintia.cn/problem-sets/15/problems/841
 */
public class WindowsQueue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.
                comparingInt(o -> o.priority));
        scanner.nextLine();
        while (m-- > 0){
            String tmp = scanner.nextLine();
            String[] tmpArr = tmp.split(" ");
            if ("PUT".equals(tmpArr[0])){
                queue.offer(new Pair(tmpArr[1],Integer.parseInt(tmpArr[2])));
            }else {
                if (queue.isEmpty()){
                    System.out.println("EMPTY QUEUE!");
                }else {
                    System.out.println(queue.poll().msg);
                }
            }
        }
    }

    static class Pair{
        String msg;
        int priority;

        public Pair(String msg, int priority) {
            this.msg = msg;
            this.priority = priority;
        }
    }
}
