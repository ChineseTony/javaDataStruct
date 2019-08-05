package com.tom.list;

/**
 * @author WangTao
 */
public class MyLinkedListLeetcode {

    private Node head;

    private int size;

    public MyLinkedListLeetcode() {
        head = new Node(-1,null);
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size){
            return -1;
        }
        Node p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.getE();
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 ){
            addAtHead(val);
            return;
        }
        if (index > size ){
            return;
        }
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = new Node(val,pre.next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size){
            return;
        }
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        size--;
    }


    private class Node{
        int  val;
        Node next;

        public Node(int e,Node next){
            this.val = e;
            this.next = next;
        }

        public Node(int e){
            this(e,null);
        }

        public Node(){
            this(-1,null);
        }

        public int getE() {
            return val;
        }



        public Node getNext() {
            return next;
        }


    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList:");
        Node cur = head.next;
        while (cur != null){
            sb.append(cur.val+"-->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyLinkedListLeetcode linkedList = new MyLinkedListLeetcode();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        System.out.println(linkedList);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        System.out.println(linkedList);
        System.out.println(linkedList.get(1));            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        System.out.println(linkedList);
        System.out.println(linkedList.get(1));            //返回3

    }

}
