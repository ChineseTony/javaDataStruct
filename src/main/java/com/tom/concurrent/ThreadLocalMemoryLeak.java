package com.tom.concurrent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author WangTao
 * -Xms10m -Xmx10m
 */
public class ThreadLocalMemoryLeak {
    public static final int LOOP_COUNT = 1000000;

    public static final ThreadLocal<List<String>> threadLocal = new ThreadLocal<> ();

    private static final ThreadLocal<Integer> myThreadLocal = ThreadLocal.withInitial(() -> null);


    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    private static final int THREAD_COUNT = 10000;


    public static void main(String[] args)throws Exception {

        test();

    }

    public static void leek() throws Exception{
        List<String> tmp = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> IntStream.rangeClosed(1, LOOP_COUNT)
                        .mapToObj(v -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString())
                .collect(Collectors.toList());

        threadLocal.set(tmp);
    }


    public static void test() throws Exception{
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute( () ->{
                    IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(
                            myThreadLocal::set
                    );
                }
        );
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);
    }

    public static Map right(Integer userId){
        String before  = Thread.currentThread().getName() + ":" + currentUser.get();
        currentUser.set(userId);
        try {
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
            Map result = new HashMap(16);
            result.put("before", before);
            result.put("after", after);
            return result;
        } finally {
            currentUser.remove();
        }
    }
}
