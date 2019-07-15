package com.tom.heap;

import com.tom.array.MyArray;

/**
 * @author WangTao
 */
@SuppressWarnings("unchecked")
public class MyMaxHeap<E extends Comparable> {


    //动态数组方便扩容
    private MyArray<E> data;


    public MyMaxHeap(int capacity){
        data = new MyArray<>(capacity);
    }

    public MyMaxHeap(){
        data = new MyArray<>();

    }

    public void insert(E e){
        data.addLast(e);
        shiftUp(data.getSize()-1);
    }

    public E getMax(){
        if (isEmpty()){
            throw new RuntimeException("Heap is empty");
        }
        return data.get(0);
    }

    public E removeMax(){
        if (isEmpty()){
            throw new RuntimeException("Heap is empty");
        }
        E e = data.get(0);
        data.swap(0,data.getSize()-1);
        data.removeLast();
        shiftDown(0);
        return e;
    }

    public int getLength(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private void shiftDown(int index){
        while (getLeftChild(index) < data.getSize()){

            int j = getLeftChild(index);
            //有右孩子 右孩子节点值大于左孩子节点值
            if (j + 1 < data.getSize()
                    && data.get(j + 1).compareTo(data.get(j)) >0){
                j++;
            }
            //大于左右孩子节点值
            if (data.get(index).compareTo(data.get(j)) >0) {
                break;
            }
            data.swap(index,j);
            index = j;
        }
    }

    private void shiftUp(int index){
        while (index > 0 && data.get(getParent(index)).compareTo(data.get(index)) < 0){
            data.swap(getParent(index),index);
            index = getParent(index);
        }
    }

    private int getParent(int i){
        if (i == 0){
            throw  new RuntimeException("0 index isn't parent");
        }
        return (i - 1) / 2;
    }


    private int getLeftChild(int i){

        return 2 * i + 1;
    }

    private int getRightChild(int i){
        return  2 * i + 2;
    }





}
