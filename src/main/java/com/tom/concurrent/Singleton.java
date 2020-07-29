package com.tom.concurrent;

public class Singleton {


    private Singleton(){

    }




    static class SingletonHolder{
        static Singleton instance = new Singleton();

    }

    static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}
