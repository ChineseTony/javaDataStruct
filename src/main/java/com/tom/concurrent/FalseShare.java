package com.tom.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author WangTao
 * @linked https://www.jianshu.com/p/defb9f9af5d3
 * 伪共享
 */
public class FalseShare implements Runnable{
    private static int NUM_THREADS = 4;

    private final static long ITERATIONS = 50L * 1000L * 1000L;

    private final int arrayIndex;

    private static VolatileLong[] longs;

    private static long SUM_TIME = 0L;

    public FalseShare(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        Thread.sleep(10000);
        // 多个线程操作多个VolatileLong
        for(int j=0; j<10; j++){
            // 初始化
            System.out.println(j);
            if (args.length == 1) {
                NUM_THREADS = Integer.parseInt(args[0]);
            }
            longs = new VolatileLong[NUM_THREADS];
            for (int i = 0; i < longs.length; i++) {
                longs[i] = new VolatileLong();
            }
            final long start = System.nanoTime();
            // 构造并启动线程
            runTest();
            final long end = System.nanoTime();
            SUM_TIME += end - start;
        }
        System.out.println("平均耗时："+SUM_TIME/10);
    }
    private static void runTest() throws InterruptedException {
        // 创建每个线程， 每个线程操作一个VolatileLong
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseShare(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }
    public final static class VolatileLong {
//        4101067752
//      jdk1.8 以上 @Contended  -XX:-RestrictContended=false 使得jdk外部类使用 –XX: EnableContented
        public volatile long value = 0L;
        // 注释此行，结果区别很大
        public long p1, p2, p3, p4, p5, p6;
    }



}


/**
 * Represents a padded {@link AtomicLong} to prevent the FalseSharing problem<p>
 *
 * The CPU cache line commonly be 64 bytes, here is a sample of cache line after padding:<br>
 * 64 bytes = 8 bytes (object reference) + 6 * 8 bytes (padded long) + 8 bytes (a long value)
 *
 * @author yutianbao
 */
class PaddedAtomicLong extends AtomicLong {
    private static final long serialVersionUID = -3415778863941386253L;

    /** Padded 6 long (48 bytes) */
    public volatile long p1, p2, p3, p4, p5, p6 = 7L;

    /**
     * Constructors from {@link AtomicLong}
     */
    public PaddedAtomicLong() {
        super();
    }

    public PaddedAtomicLong(long initialValue) {
        super(initialValue);
    }

    /**
     * To prevent GC optimizations for cleaning unused padded references
     */
    public long sumPaddingToPreventOptimization() {
        return p1 + p2 + p3 + p4 + p5 + p6;
    }

}
