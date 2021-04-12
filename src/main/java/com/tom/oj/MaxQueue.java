package com.tom.oj;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author WangTao
 */
public class MaxQueue {

    private Deque<Integer> queue;

    private Queue<Integer> data;

    public MaxQueue() {
        queue = new ArrayDeque<>();
        data = new ArrayDeque<>();
    }

    public int max_value() {
        if (queue.isEmpty()){
            return -1;
        }else {
            return queue.peek();
        }
    }

    public void push_back(int value) {
        while (!queue.isEmpty() && queue.getLast() < value){
            queue.pollLast();
        }
        queue.addLast(value);
        data.offer(value);

    }

    public int pop_front() {
        if (data.isEmpty()) {
            return -1;
        }
        int ans = data.poll();
        if (ans == queue.peekFirst()) {
            queue.pollFirst();
        }
        return ans;
    }

}
