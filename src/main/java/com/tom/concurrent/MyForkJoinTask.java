package com.tom.concurrent;





import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author WangTao
 */
public class MyForkJoinTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 100;
    private int from;
    private int to;

    public MyForkJoinTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (THRESHOLD > (to - from)){
            return IntStream.range(from,to+1)
                    .reduce((a,b) -> a+b).getAsInt();
        }else {
            int forkNumber = (from + to) / 2;
            MyForkJoinTask left = new MyForkJoinTask(from, forkNumber);
            MyForkJoinTask right = new MyForkJoinTask(forkNumber + 1, to);

            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
