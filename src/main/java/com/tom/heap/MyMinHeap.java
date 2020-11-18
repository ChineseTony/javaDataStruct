package com.tom.heap;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMinHeap<E extends Comparable> {


    private E[] data;

    private  int len = 0;

    public MyMinHeap(int capacity){
        data = (E[]) new Comparable[capacity+1];
    }

    public void insert(E e){
        if (len + 1 >= data.length){
            throw new RuntimeException("heap is full");
        }
        data[++len] = e;
        shiftUp(len);
    }

    private void shiftUp(int index){
        while (index > 1 && data[getParent(index)].compareTo(data[index]) > 0){
            swap(getParent(index),index);
            index = getParent(index);
        }
    }

    private void swap(int i,int j){
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    private int getParent(int i){
        if (i == 0){
            throw  new RuntimeException("0 index isn't parent");
        }
        return i /  2;
    }

    public List<E> getAllParent(int i){
        if (i == 0){
            throw  new RuntimeException("0 index isn't parent");
        }
        List<E> list = new ArrayList<>();
        int index = i;
        while (index >= 1){
            list.add(data[index]);
            index = index / 2;
        }
        return list;
    }



    private int getLeftChild(int i){

        return 2 * i - 1;
    }

    private int getRightChild(int i){
        return  2 * i + 1;
    }


    /**
     * 5 3
     * 46 23 26 24 10
     * 5 4 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        MyMinHeap<Integer> myMaxHeap = new MyMinHeap<>(N);
        for (int i = 0; i < N; i++) {
            myMaxHeap.insert(scanner.nextInt());
        }
        System.out.println(myMaxHeap);
        for (int i = 0; i < M; i++) {
            int index = scanner.nextInt();
            myMaxHeap.getAllParent(index).forEach(v ->
                    System.out.print(v+"\t"));
            System.out.println();
        }

    }

}
