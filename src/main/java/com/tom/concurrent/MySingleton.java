package com.tom.concurrent;


public class MySingleton {

    private MySingleton(){

    }

    private volatile  static MySingleton mySingleton;


    public MySingleton getInstance() {
        if (mySingleton == null){
            synchronized (MySingleton.class){
                if (mySingleton == null){
                    mySingleton = new MySingleton();
                }
            }
        }
        return mySingleton;

    }
}
