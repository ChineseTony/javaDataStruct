package com.tom.jinjie.chap1;

import java.util.concurrent.TimeUnit;

/**
 * @author WangTao
 */
public class DamonThreadStudy {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Thread t = new Thread(() -> {
            try {
                TimeUnit.DAYS.sleep(Long.MAX_VALUE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("关闭线程");
        });
        //只有所有daemon 非守护进程都结束 jvm线程才结束
        t.setDaemon(true);
        t.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序执行"+(endTime - startTime) +"ms");
    }
}
