package com.tom.oj;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author WangTao
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        //最大堆
        Queue<Integer> queue = new PriorityQueue<>((o1,o2) ->  o2-o1);
        for (int i = 0; i < stones.length; i++) {
            queue.offer(stones[i]);
        }
        while (queue.size() > 1){
            int x = queue.poll();
            int y = queue.poll();
            int diff = Math.abs(x - y);
            if (diff != 0){
                queue.offer(diff);
            }

        }
        if (queue.isEmpty()){
            return 0;
        }else {
            return queue.peek();
        }
    }

    public int lastStoneWeight2(int[] stones) {
        int end = stones.length -1;
        int k = 0;
        while (k != stones.length && k != stones.length-1){
            Arrays.sort(stones);
            int x = stones[end-1];
            int y = stones[end];
            if (x == y){
                stones[end-1] = stones[end] = -1;
                k += 2;
            }else {
                stones[end-1] = stones[end] - stones[end-1];
                stones[end] = -1;
                k += 1;
            }
        }
        Arrays.sort(stones);
        return k == stones.length ? 0 : stones[end];
    }
}
