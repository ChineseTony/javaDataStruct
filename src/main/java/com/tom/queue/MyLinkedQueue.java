package com.tom.queue;



/**
 * @author WangTao
 *
 * 没有头结点的链表栈
 */
public class MyLinkedQueue<E> implements MyQueue<E> {

    private Node head;

    private Node rear;

    private int size;


    public MyLinkedQueue() {
        head = rear = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (head == null){
            head = new Node(e);
            rear = head;
        }else{
            rear.next = new Node(e);
            rear = rear.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new RuntimeException("deque is empty");
        }
        Node temp = head;
        head = head.next;
        //从链表中断开 让垃圾回收机制回收
        temp.next = null;
        if (head == null){
            rear = null;
        }
        size --;
        return temp.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new RuntimeException("deque is empty");
        }
        return head.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: head :");
        Node cur = head;
        while (cur != null){
            sb.append(cur+"-->");
            cur = cur.next;
        }
        sb.append("NULL rear");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyLinkedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        while (!queue.isEmpty()){
            System.out.print(queue.dequeue()+" ");
        }
        System.out.println();
    }

    private class Node{
        E e;
        Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }


        @Override
        public String toString() {
            return e.toString();
        }
    }
}
