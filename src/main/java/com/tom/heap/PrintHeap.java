package com.tom.heap;



import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author wangtao
 * @link https://pintia.cn/problem-sets/434/problems/6104
 */
public class PrintHeap {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        int count = scanner.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        while (times-- > 0){
            queue.add(scanner.nextInt());
        }
        Integer[] array = queue.toArray(new Integer[0]);
        while (count-- > 0){
            int index = scanner.nextInt() - 1;
            //递归调用获取父亲节点，并且打印
            while (index > 0){
                System.out.print(array[index]+" ");
                index = (index - 1) / 2;
            }
            System.out.println(array[index]);
        }

    }
}
