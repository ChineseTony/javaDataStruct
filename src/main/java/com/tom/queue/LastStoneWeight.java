package com.tom.queue;



import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LastStoneWeight {


    public static int lastStoneWeight(int[] stones) {
        Queue<Integer>  queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < stones.length; i++) {
            queue.offer(stones[i]);
        }
        while (queue.size() > 1){
            int tmp = queue.poll();
            int y = queue.poll();
            if (tmp != y){
                queue.offer(Math.abs(tmp-y));
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2};
        System.out.println(lastStoneWeight(nums));

    }
}
