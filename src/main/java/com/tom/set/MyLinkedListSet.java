package com.tom.set;


import com.tom.list.MyLinkedList;

/**
 * @author WangTao
 */
public class MyLinkedListSet<E> implements MySet<E> {

    private MyLinkedList<E> list;

    public MyLinkedListSet(){
        list = new MyLinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.find(e)){
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.find(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
