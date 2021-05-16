package com.tom.study.agent;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangtao
 * @date 2021/5/15
 *
 *  设置jvm 运行参数
 *  -javaagent:/Users/tom/ideaprojects/javaDataStruct/myagent/target/my-agent.jar=first
 */
public class AgentStudy {

    public static void main(String[] args) {
        ScheduledExecutorService executorService =
                new ScheduledThreadPoolExecutor(1
                        ,new NamedThreadFactory("study-agent-thread"
                        ,false));
        final AgentStudy agentStudy = new AgentStudy();
        executorService.scheduleAtFixedRate(() ->{
            agentStudy.test(2);
            System.out.println("##");
            testMethod("a",1);
        },0, 1, TimeUnit.SECONDS);

    }

    public  static void testMethod(String a,int b){
        System.out.println(a + b);
    }

    private  void test(int a){
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
