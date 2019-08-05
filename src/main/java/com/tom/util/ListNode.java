package com.tom.util;

/**
 * @author WangTao
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr){

        if(arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1 ; i < arr.length ; i ++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof ListNode) {
            ListNode anotherListNode = (ListNode)anObject;
            if (anotherListNode.val == this.val){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return  this.val * 31 ;
    }

    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            s.append(cur.val + "->");
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();
    }
}
