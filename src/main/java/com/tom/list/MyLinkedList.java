package com.tom.list;

/**
 * @author WangTao
 */
public class MyLinkedList<E> {


    private Node head;

    private int size;


    public MyLinkedList(){
        //头结点
        head = new Node(null,null);
        size = 0;

    }

    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("add index error");
        }
        Node p = head.next;
        for (int i = 0; i < index-1; i++) {
            p = p.next;
        }
        return p.getE();
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return  size == 0;
    }

    public void addFirst(E e){
        add(e,0);
    }

    public void add(E e,int index){
        if (index < 0 || index > size){
                throw new IllegalArgumentException("add index error");
        }
        Node pre = head;
        for (int i = 0; i < index; i++) {
              pre = pre.next;
        }
        /*
             Node node = new Node(e);
             node.next = pre.next;
             pre.next = node;
         */
        pre.next = new Node(e,pre.next);
        size++;
    }

    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("delete index error");
        }
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node retNode = pre.next;
        pre.next = retNode.next;
        E e = retNode.e;
        retNode.next = null;
        size--;
        return e;
    }

    public E removeFirst(){
        return remove(0);
    }


    public E removeLast(){
        return remove(size-1);
    }



    public void set(E e,int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set index error");
        }
        Node cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean find(E e){
        Node cur = head.next;
        while (cur != null){
            if (cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void addLast(E e){
        add(e,size);
    }


    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList:");
        Node cur = head.next;
        while (cur != null){
            sb.append(cur+"-->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    private class Node{
        E e;
        Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        public E getE() {
            return e;
        }



        public Node getNext() {
            return next;
        }


        @Override
        public String toString() {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
        }
        System.out.println(list);
        list.add(5,0);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeLast();
        System.out.println(list);
    }
}
