package com.tom.interview;


import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author WangTao
 * 只有守护线程，程序将退出
 */
public class JvmExit {

    public static void main(String[] args) throws Exception {
//        打印语句并没有被执行程序就退出
        StopWatch stopWatch = StopWatch.createStarted();
        CountDownLatch latch = new CountDownLatch(1);
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException ignore) {
            }
            System.out.println("异步任务");
            latch.countDown();
        });
        latch.await();

        readFile();
        System.out.println("time---->"+stopWatch.getTime());

    }

    private static void  readFile(){
        //阻止 code==2 退出 jvm 阻止jvm运行 使其执行 finally语句
        System.setSecurityManager(new SecurityManager() {

            @Override
            public void checkExit(int status) {
                if(status == 2){
                    throw new SecurityException("不允许退出");
                }
            }
        });
        try {
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));
            System.out.println(br.readLine());
            br.close();
        } catch (Exception e) {
            // 省略一些代码 （第 2 处）不执行Finally语句
            System.exit(2);
        } finally {
            System.out.println("Exiting the program");
        }
    }
}
