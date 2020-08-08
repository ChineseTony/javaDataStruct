package com.tom.concurrent;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @author WangTao
 */
public class CompletionServiceTest {

    public static void main(String[] args)throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CompletionService<String> service = new ExecutorCompletionService<>(pool);
        Stream.of("a","b","c","d")
                .forEach(fruit ->
                        service.submit(() ->{
                            if (fruit.equalsIgnoreCase("a")){
                                TimeUnit.SECONDS.sleep(6);
                            }else if (fruit.equalsIgnoreCase("b")){
                                TimeUnit.SECONDS.sleep(1);
                            }else if (fruit.equalsIgnoreCase("c")){
                                TimeUnit.SECONDS.sleep(7);
                            }else if (fruit.equalsIgnoreCase("d")){
                                TimeUnit.SECONDS.sleep(3);
                            }
                        return "洗干净的"+fruit;
                }
        ));

        String result;
        while ((result = service.take().get()) != null){
            System.out.println("吃掉了"+result);
        }
        pool.shutdown();

    }
}
