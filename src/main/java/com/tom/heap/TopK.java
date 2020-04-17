package com.tom.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author WangTao
 */
public class TopK {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0){
            return new int[0];
        }
        int[] result = new int[k];
        Queue<Integer> queue = new PriorityQueue<>(arr.length,
                (i1,i2) -> Integer.compare(i2,i1));
        for(int i: arr){
            if (queue.isEmpty() || queue.size() < k || i < queue.peek()){
                queue.offer(i);
            }
            if (queue.size() > k){
                queue.poll();
            }
        }
        int j = 0;
        while (!queue.isEmpty()){
            int tmp = queue.poll();
            result[j++] = tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,1};
        int[] result =new TopK().getLeastNumbers(arr,2);
        for(int i:result){
            System.out.println(i);
        }
    }
}
