package com.tom.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


/**
 * @author WangTao
 */
public class ConcurrentHashMapMisuse {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentHashMapMisuse.class);

    private static final int THREAD_COUNT = 10;

    private static final int ITEM_COUNT = 1000;

    private static ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
//                转成hashmap
                .collect(Collectors.
                        toConcurrentMap(i -> UUID.randomUUID().toString(),
                                Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
    }



    public static void main(String[] args) throws InterruptedException {

        right();
        System.out.println("#############");
        error();
    }


    public static void right() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        logger.info("init size:{}", concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (concurrentHashMap) {
                int gap = ITEM_COUNT - concurrentHashMap.size();
                logger.info("gap size:{}", gap);
                concurrentHashMap.putAll(getData(gap));
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        logger.info("finish size:{}", concurrentHashMap.size());
    }


    public static void error() throws InterruptedException{
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        logger.info("init size:{}", concurrentHashMap.size());
        //concurrentHashMap只保证单个操作保证原子性
        forkJoinPool.execute(() -> {
            IntStream.rangeClosed(1,10).parallel()
                    .forEach(i -> {
                        int gap = ITEM_COUNT - concurrentHashMap.size();
                        logger.info("gap size:{}", gap);
                        concurrentHashMap.putAll(getData(gap));
                    });
        });

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        logger.info("finish size:{}", concurrentHashMap.size());
    }
}
