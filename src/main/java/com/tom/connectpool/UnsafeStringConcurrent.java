package com.tom.connectpool;

import com.tom.util.NameThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangtao
 * @date 2021/8/6
 */

public class UnsafeStringConcurrent {

    private static final Object lock = new Object();

    private static ExecutorService executorService =
            new ThreadPoolExecutor(10,100,
                    10, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(100),
                    new NameThreadFactory("string-test"),
                    new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            final int tmp = i;
            executorService.submit(() -> {
                test(tmp);
            });
            try {
                if (i == 3){
                    throw new RuntimeException("error");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        executorService.shutdown();



    }

    public static void test(int i){
        synchronized (lock){
            System.out.println("a");
//            if (i % 2 == 0){
//                lock.notifyAll();
//            }
//            try {
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }


}
