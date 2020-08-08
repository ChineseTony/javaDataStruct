package com.tom.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author WangTao
 */
public class PhaserStudy {

    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        IntStream.range(1,10).forEach(v ->{
            phaser.register();
            final int number = v;
            Thread student= new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("学生"+number+"交卷");
                phaser.arriveAndDeregister();
            });
            student.start();
        });

        phaser.arriveAndAwaitAdvance();
        IntStream.range(1,3).forEach(v ->{
            phaser.register();
            final int number = v;
            Thread teacher= new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("老师"+number+"判卷");
                phaser.arriveAndDeregister();
            });
            teacher.start();
        });

        phaser.arriveAndAwaitAdvance();
        IntStream.range(1,3).forEach(v ->{
            phaser.register();
            final int number = v;
            Thread student= new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("辅导员"+number+"公布成绩");
                phaser.arriveAndDeregister();
            });
            student.start();
        });
        phaser.arriveAndAwaitAdvance();

    }
}
