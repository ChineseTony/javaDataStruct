package com.tom;

import com.tom.util.ListNode;

/**
 * @author WangTao
 */
public class MyLinkedList{



    public void delete(ListNode head,int x){
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode cur = pre;
        while (cur.next != null){
            if (cur.next.val == x){
                break;
            }
            cur = cur.next;
        }
        if (cur.next == null){
            return;
        }else {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = null;
        }
    }


    private class Node{
        int  val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }

   /* public ListNode merge(ListNode p,ListNode q){

    }*/
}
