package com.tom.spistudy;

/**
 * @author WangTao
 */
public class Test {

    public static void main(String[] args) {
        B b = new B();
        A a = b;
        b.say();
    }
}

interface A{
    void say();
}

class B implements A{
    @Override
    public void say() {
        System.out.println("say");
    }
}