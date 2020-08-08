package com.tom.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author WangTao
 */
public class MyForkJoinStduy {
    public static void main(String[] args) throws Exception{
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> result = forkJoinPool.submit(new MyForkJoinTask(1, 10000));

        System.out.println("计算结果为"+result.get());
        forkJoinPool.shutdown();
    }
}
