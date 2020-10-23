package com.tom.interview;

public class Confusing {

    public Confusing(Object o) {
        System.out.println("Object");
    }

    public Confusing(double[] dArray) {
        System.out.println("double array");
    }

    public static void main(String[] args) {
        //output double array
//        null可以匹配任何类对象。从最底层子类依次向上查找
        new Confusing(null);
        new Confusing(1);

    }
}
