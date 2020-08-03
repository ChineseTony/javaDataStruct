package com.tom.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author WangTao
 */
public class LongAddrTest {

    private static final Logger logger = LoggerFactory.getLogger(LongAddrTest.class);

    private static final int THREAD_COUNT = 10;


    private static int LOOP_COUNT = 100000;

    private static final int ITEM_COUNT = 100;

    private static final String KEY = "key";

    public static void main(String[] args) throws InterruptedException {
        Map<String,Long>  concurrentHashMap = betteruser();
        printMap(concurrentHashMap);
    }

    public static Map<String,Long> gooduse() throws InterruptedException {
        ConcurrentHashMap<String,Long> map = new ConcurrentHashMap<>(16);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> {
            IntStream.range(1,LOOP_COUNT)
                    .parallel().forEach(i -> {
                String item = KEY+ ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                synchronized (map){
                    if (map.containsKey(item)){
                        map.put(item,map.get(item)+1);
                    }else {
                        map.put(item,1L);
                    }
                }
            });
        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);
        return map;
    }



    public static Map<String,Long> betteruser() throws InterruptedException {
        ConcurrentHashMap<String,LongAdder> map = new ConcurrentHashMap<>(16);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> {
            IntStream.range(1,LOOP_COUNT)
                    .parallel().forEach(i -> {
                String item = KEY+ ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                map.computeIfAbsent(item,k -> new LongAdder()).increment();
            });
        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);
        return  map.entrySet().stream()
                .collect(Collectors.toMap(
                     e -> e.getKey(),
                     e -> e.getValue().longValue(),
                     (v1,v2) -> v1
                ));
    }

    private static void printMap(Map<String,Long>  map){

        List<Map.Entry<String, Long>> mylist = new ArrayList<>(map.entrySet());
        Collections.sort(mylist, (o1, o2) -> {
            String temp1 = o1.getKey().replace(KEY,"");
            String temp2 = o2.getKey().replace(KEY,"");
            return Integer.valueOf(temp1).compareTo(Integer.valueOf(temp2));
        });
        mylist.forEach(item ->
            logger.info("key:{}---->times:{}",item.getKey(),item.getValue())
        );
    }
}
