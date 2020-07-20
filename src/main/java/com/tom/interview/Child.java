package com.tom.interview;

import java.util.Collection;

public class Child<E> extends Parent<E> {

    @Override
    public void eat(E e) {
        System.out.println("C:eat" + e);
    }

    @Override
    public void eatAll(Collection<? extends E> c) {
        System.out.println("C:eatAll");
        super.eatAll(c);
    }
}
