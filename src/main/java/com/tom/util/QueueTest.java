package com.tom.util;

import com.tom.queue.MyQueue;

import java.util.Random;

/**
 * @author WangTao
 */
public class QueueTest {

    public static  double testQueue(MyQueue queue,int optCount){
        long start = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < optCount; i++) {
            queue.dequeue();
        }
        long end = System.nanoTime();
        return (end - start) / 100000000.0 ;
    }
}
