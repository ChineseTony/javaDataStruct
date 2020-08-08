package com.tom.concurrent;

import com.tom.util.NameThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author WangTao
 */
public class SynchronousQueueStudy {

    private static final Logger logger = LoggerFactory.getLogger(SynchronousQueueStudy.class);

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>(true);

        ExecutorService service = new ThreadPoolExecutor(
                10,100,
                1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100),
                new NameThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()

        );

        for (int i = 0; i < 10; i++) {
            final int tmp = i;
            service.submit(() ->{
                if (tmp % 2 == 0){
                    try {
                        queue.offer(String.valueOf(tmp),1,TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        String value = queue.poll(1,TimeUnit.SECONDS);
                        logger.info("取到的元素:{}",value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

       service.shutdown();

    }
}
