package com.tom.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */
public class MyLinkedListTest {

    private MyLinkedList<Integer> myLinkedList;

    @Before
    public void initData(){
       myLinkedList = new MyLinkedList<>();
       myLinkedList.addFirst(1);
       myLinkedList.addLast(3);
       myLinkedList.add(2,1);

    }

    @Test
    public void removeElements(){
        assertEquals(myLinkedList.toString(),"LinkedList:1-->2-->3-->NULL");
        myLinkedList.removeFirst();
        myLinkedList.removeLast();
        assertEquals(myLinkedList.get(0).intValue(),2);
        myLinkedList.add(4,1);
        myLinkedList.add(5,2);
        myLinkedList.remove(1);
        assertEquals(myLinkedList.toString(),"LinkedList:2-->5-->NULL");
    }

    @Test
    public void findKToTail(){
        System.out.println(myLinkedList.toString());
        System.out.println(myLinkedList.findKToTail(4));
        System.out.println(myLinkedList.findKToTail(3));
        System.out.println(myLinkedList.findKToTail(2));
        System.out.println(myLinkedList.findKToTail(1));
    }
}
