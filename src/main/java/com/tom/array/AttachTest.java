package com.tom.array;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangtao
 * @date 2021/5/20
 */
public class AttachTest {

    public static void main(String[] args) {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1
        ,new NamedThreadFactory("schedule-%d",false));

        service.scheduleAtFixedRate(() ->{
            System.out.println("hello");}
        ,1,1, TimeUnit.SECONDS);


    }
}
