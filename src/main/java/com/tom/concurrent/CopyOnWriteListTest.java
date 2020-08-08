package com.tom.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author WangTao
 */
public class CopyOnWriteListTest {

    private static final Logger logger = LoggerFactory.getLogger(CopyOnWriteListTest.class);

    private static List<Integer> synchronizedList =  Collections.synchronizedList(new ArrayList<>());

    private static List<Integer> copyOnWriteList = new CopyOnWriteArrayList<>();

    private static final int LOOP_COUNT = 100000;

    public static void main(String[] args) {
        write();
        logger.info("####");
        read();
    }

    public static void write(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Write:copyOnWriteArrayList");
        IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(v ->
                copyOnWriteList.add(ThreadLocalRandom.current().nextInt(LOOP_COUNT)));
        stopWatch.stop();
        stopWatch.start("Write:synchronizedList");
        IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(v ->
                synchronizedList.add(ThreadLocalRandom.current().nextInt(LOOP_COUNT)));
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    public static void read(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Read:copyOnWriteArrayList");
        int count = copyOnWriteList.size();

        IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(v ->
                copyOnWriteList.get(ThreadLocalRandom.current().nextInt(count))
        );
        stopWatch.stop();
        stopWatch.start("Read:synchronizedList");
        IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(v ->
                synchronizedList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }



}
