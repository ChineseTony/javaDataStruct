package com.tom.list;


import com.tom.util.ListNode;

/**
 * @author WangTao
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class RemoveElement {

    //没有头结点
    public ListNode removeElements1(ListNode head, int val) {
        //删除开始节点值为val 的节点
        while (head != null && head.val == val){
            ListNode node = head;
            head = head.next;
            node.next = null;
        }
        if (head == null){
            return head;
        }
        ListNode pre = head;
        while (pre.next != null){
            //删除pre.next 值为 val
            if (pre.next.val == val){
                ListNode deleteNode = pre.next;
                pre.next = deleteNode.next;
                deleteNode.next = null;
            }else {
                pre = pre.next;
            }
        }
        return head;
    }

    //没看懂
    public ListNode removeElements3(ListNode head, int val) {
        //删除开始节点值为val 的节点
        if (head == null){
            return null;
        }
        head.next = removeElements3(head.next,val);
        //删除值为val 的 节点
        if (head.val == val){
            return head.next;
        }else {
            return head;
        }
    }

    //有头结点
    public ListNode removeElements2(ListNode head, int val) {
        //虚拟出来的头结点
        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        //删除开始节点值为val 的节点
        ListNode pre = tmp;
        while (pre.next != null){
            //删除pre.next 值为 val
            if (pre.next.val == val){
                ListNode deleteNode = pre.next;
                pre.next = deleteNode.next;
                deleteNode.next = null;
            }else {
                pre = pre.next;
            }
        }
        return tmp.next;
    }

    //头插法反转链表
    public ListNode reverseList1(ListNode head) {
        ListNode tmp = new ListNode(-1);
        tmp.next = null;
        ListNode cur = head;
        while (cur != null){
            ListNode a = new ListNode(cur.val);
            a.next = tmp.next;
            tmp.next = a;
            cur = cur.next;
        }
        return tmp.next;
    }

    public int length(ListNode head){
        if (head == null){
            return 0;
        }
        return 1 + length(head.next);
    }

    public ListNode removeElements(ListNode head, int val, int depth) {

        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if(head == null){
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if(head.val == val) {
            ret = res;
        }else{
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);

        return ret;
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++) {
            res.append("--");
        }
        return res.toString();
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1};
        RemoveElement removeElement = new RemoveElement();
        ListNode head = new ListNode(nums);
        System.out.println(removeElement.length(head));
        System.out.println(removeElement.removeElements3(head,2));

        ListNode res = removeElement.removeElements(head, 1, 0);
        System.out.println(res);
    }

}
