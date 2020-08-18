package com.tom.spistudy.proxy.jdk;

/**
 * @author WangTao
 */
public interface Person {

    default void say(){
        System.out.println("hello");
    }
}
