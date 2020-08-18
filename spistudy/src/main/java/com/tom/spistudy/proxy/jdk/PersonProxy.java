package com.tom.spistudy.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WangTao
 */
public class PersonProxy<T> implements InvocationHandler {

    private T target;

    public PersonProxy(T target) {
        this.target = target;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before invoke .");
        Object ret = method.invoke(target, args);
        System.out.println("after invoke .");
        return ret;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(Thread.currentThread().
                getContextClassLoader(), target.getClass().getInterfaces(),
                this);
    }

    public static void main(String[] args) {
        Person us = new Person() {
                @Override
                public void say() {
                    System.out.println("hello");
                }
        };
        PersonProxy<Person> proxyHandler = new PersonProxy<>(us);
        Person userService = proxyHandler.getProxy();
        userService.say();
        byte[] bytes= ProxyGenerator.generateProxyClass("$Proxy0",
                new Class[]{Person.class});
        try(FileOutputStream fos = new FileOutputStream
                (new File("d:/person.class"))){
            fos.write(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
