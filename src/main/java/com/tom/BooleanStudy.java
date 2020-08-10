package com.tom;

import com.tom.util.NameThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author WangTao
 */
public class BooleanStudy {

    private static volatile int i = 0;

    private static final Object monitor = new Object();

    private static final int COUNT = 100;


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(
                10,200,1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(200),
                new NameThreadFactory("test"),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int j = 0; j < COUNT; j++) {
            executorService.execute(BooleanStudy::count);

        }

//        executorService.awaitTermination(1,TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(i);

    }

    private static void count(){
        synchronized (monitor){
            i ++;
        }

    }
}
