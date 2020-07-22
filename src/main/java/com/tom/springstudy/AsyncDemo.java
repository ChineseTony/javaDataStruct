package com.tom.springstudy;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;


@EnableAsync
@Configuration
public class AsyncDemo {


    @Autowired
    private ObjectProvider<AService> provider;

    @Autowired
    private ObjectProvider<BService> bServiceObjectProvider;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext();

        configApplicationContext.register(AsyncDemo.class);
        configApplicationContext.refresh();
        ObjectProvider<AService>  object = configApplicationContext.
                getBeanProvider(AService.class);
        long startTime = System.currentTimeMillis();
        System.out.println("函数开始执行");
        object.ifAvailable(AService::doSomething);
        long endTime = System.currentTimeMillis();
        System.out.println("函数执行完成,执行时间"+(endTime - startTime) / 1000+"s");

        ObjectProvider<BService>  beanProvider = configApplicationContext.
                getBeanProvider(BService.class);


        AtomicReference<Future<String>> future = new AtomicReference<>();
        beanProvider.ifAvailable(b -> {
            future.set(b.doSomething());
        });
        try {
            System.out.println("future---->"+future.get().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        configApplicationContext.close();

    }

    @Bean
    public AService getAService() {
        return new A();
    }

    @Bean
    public BService getBService() {
        return new B();
    }




    class A  implements AService{
        @Autowired
        private BService bService;

        @Override
        @Async("myExecutor")
        public void doSomething() {
            System.out.println(Thread.currentThread().getName()+"--->call A service");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Component
    interface AService{
        void doSomething();
    }

    @Component
    interface BService{
        Future<String> doSomething();
    }


    class B  implements BService{
        @Autowired
        @Lazy
        private AService aService;

        @Override
        @Async
        public Future<String> doSomething() {
            System.out.println(Thread.currentThread().getName()+"--->call B service");
            Future<String> result = new AsyncResult<>("call B service");
            return result;
        }
    }



    @Bean("myExecutor")
    public Executor executor(){
        ThreadPoolTaskExecutor executor =  new ThreadPoolTaskExecutor();
        //核心池大小：线程池维护线程的最少数量
        executor.setCorePoolSize(50);
        //最大线程数
        executor.setMaxPoolSize(100);
        //队列大小
        executor.setQueueCapacity(1000);
        //线程空闲时间
        executor.setKeepAliveSeconds(60);
        //线程名称前缀
        executor.setThreadNamePrefix("wangtao-");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //线程池注册优雅停机：当要停止应用时，等待所有线程执行完再停止
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置等待时间，如果超过这个时间还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(120);
        return executor;

    }


}
