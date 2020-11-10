package com.tom.queue;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 */
public class MaxQueue {

    private Queue<Integer> queue;

    public MaxQueue() {
        queue = new ArrayDeque<>();
    }

    public int max_value() {
        if (queue.isEmpty()){
            return -1;
        }else {
            return queue.stream().max(Integer::compareTo).get();
        }
    }

    public void push_back(int value) {
        queue.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()){
            return -1;
        }else {
            return queue.poll();
        }
    }

    public static void main(String[] args) {

    }
}
