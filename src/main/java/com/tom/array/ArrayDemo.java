package com.tom.array;

/**
 * @author WangTao
 */
public class ArrayDemo {

    public static void main(String[] args) {
        MyArray<Integer> myArray = new MyArray<>(20);
        for (int i = 0; i < 10 ; i++) {
            myArray.addLast(i);
        }
        System.out.println(myArray);
        myArray.add(1,200);
        System.out.println(myArray);
        myArray.addFirst(900);
        System.out.println(myArray);
        myArray.addLast(1000);
        System.out.println(myArray);
        myArray.remove(2);
        System.out.println(myArray);
        myArray.removeFirst();
        System.out.println(myArray);
        myArray.removeLast();
        System.out.println(myArray);
        myArray.removeElement(4);
        System.out.println(myArray);
    }
}
