package com.tom.concurrent;

import com.tom.util.NameThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author WangTao
 */
public class VolatileStudy {


    private static final Logger logger = LoggerFactory.getLogger(VolatileStudy.class);
    private static volatile int x;

    private static final int TIMES = 10000;

    private static ExecutorService service ;

    static {
        service = new ThreadPoolExecutor(
                10,100,
                1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100),
                new NameThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()

        );
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("volatile write");
        IntStream.range(1,TIMES).forEach(value -> {
            x = value;
            logger.info("volatile write value{}",value);
        });
        stopWatch.stop();

        stopWatch.start("volatile read");
        IntStream.range(1,TIMES).forEach(value -> {
            logger.info("volatile read value{}",value);
        });
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());

    }

}
