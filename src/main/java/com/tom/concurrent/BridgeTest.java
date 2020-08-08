package com.tom.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangTao
 *
 * 泛型 java泛型是假的 都是Object对象 java泛型擦除 语法糖
 */
public class BridgeTest {


    public static void main(String[] args) {
        Child child = new Child();

        //error
        Arrays.stream(child.getClass().getMethods())
                .filter(method -> method.getName().equals("setValue") )
                .forEach(System.out::println);

        System.out.println("########");

        //right
        Arrays.stream(child.getClass().getDeclaredMethods())
                .filter(v -> v.getName().equals("setValue") && !v.isBridge() )
                .forEach(method -> {
                    try {
                        method.invoke(child,"test");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println(child.toString());



    }

}

class Parent<T> {
    protected AtomicInteger count = new AtomicInteger();

    private T value;

    @Override
    public String toString() {
        return String.format("value:%s,count:%d",value,count.get());
    }

    public void setValue(T value) {
        System.out.println("parent called");
        this.value = value;
        count.incrementAndGet();
    }
}

class Child extends Parent<String>{


    @Override
    public void setValue(String s) {
        System.out.println("child called");
        super.setValue(s);
    }
}
