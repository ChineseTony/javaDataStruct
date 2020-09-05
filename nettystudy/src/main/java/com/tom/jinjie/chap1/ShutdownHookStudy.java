package com.tom.jinjie.chap1;

/**
 * @author WangTao
 */
public class ShutdownHookStudy {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{

            System.out.println("hook start");
        }));

        System.exit(-1);
    }
}
