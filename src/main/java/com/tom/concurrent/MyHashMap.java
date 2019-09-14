package com.tom.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangTao
 */
public class MyHashMap {

    public static void main(String[] args) {


        HashMapThread t1 = new HashMapThread();
        HashMapThread t2 = new HashMapThread();
        HashMapThread t3 = new HashMapThread();
        HashMapThread t4 = new HashMapThread();
        HashMapThread t5 = new HashMapThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

    static class HashMapThread extends Thread
    {

        private  AtomicInteger ai = new AtomicInteger(0);
        private  Map<Integer, Integer> map = new HashMap<>(1);

        @Override
        public void run()
        {
            while (ai.get() < 100000)
            {
                map.put(ai.get(), ai.get());
                ai.incrementAndGet();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束完");
        }
    }

}
