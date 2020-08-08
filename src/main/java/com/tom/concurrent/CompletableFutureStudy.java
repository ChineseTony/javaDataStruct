package com.tom.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author WangTao
 */
public class CompletableFutureStudy {

    public static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "挖坑完成")
                .thenApply(s->s+",并且归还铁锹")
                .thenApply(s->s+"，全部完成。");

        System.out.println("result is " + completableFuture.get());
    }

    public static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "挖坑完成。")
                .thenCombine(CompletableFuture.supplyAsync(() -> "拿树苗完成。"),
                        (a,b)-> a+b+"植树完成。");

        System.out.println("result is " + completableFuture.get());
    }

    public static void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("挖坑完成");
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("取树苗完成");
        });
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("取肥料完成");
        });

        CompletableFuture.allOf(future1,future2,future3)
                .handle((result, throwable) -> {
//          处理异常
            if (result == null) {
                return "挖坑异常";
            }
            return result;
        }).get();

        System.out.println("植树准备工作完成！");

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApply();
        thenCombine();
    }
}
