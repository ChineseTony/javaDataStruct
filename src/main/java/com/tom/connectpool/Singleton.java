package com.tom.connectpool;

/**
 * @author wangtao
 * @date 2021/8/6
 */
public class Singleton {

    private volatile static Singleton instance;

    private Singleton(){

    }

    private Singleton getInstance(){
        if (instance != null){
            synchronized (Singleton.class){
                if (instance != null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
