package com.tom.list;


import com.tom.util.ListNode;
import java.util.Stack;

/**
 * @author WangTao
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class ListOperate {

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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //头节点
        ListNode head = new ListNode(-1);
        //尾节点
        ListNode rear = head;
        ListNode head1 = l1;
        ListNode head2 = l2;
        while (head1 != null && head2 != null){
            if (head1.val <= head2.val){
                rear.next = head1;
                rear = rear.next;
                head1 = head1.next;
            }else {
                rear.next = head2;
                rear = rear.next;
                head2 = head2.next;
            }
        }
        if (head1 != null ){
            rear.next = head1;
        }
        if (head2 != null ){
            rear.next = head2;
        }
        return  head.next;
    }

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 示例 1:
     *
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     *
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

     /*   ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
        */
        if(head == null || head.next == null){
            return head;
        }
        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null){
            if (pre.val == cur.val){
                ListNode node = cur;
                pre.next = node.next;
                cur = node.next;
                node.next = null;
            }else {
                pre = cur;
                cur = pre.next;
            }
        }
        return tmp.next;
    }


    /**
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        //用stack
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null){
            stack.push(cur.val);
            cur = cur.next;
            count++;
        }
        cur = head;
        for (int i = 0; i < count/2; i++) {
            if (stack.peek() != cur.val){
                return false;
            }else {
                stack.pop();
                cur = cur.next;
            }
        }
        return true;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * @param head
     * @param n
     * @return
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
      /*  ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length  = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;*/
        //用于保存首节点的位置虚拟的一个头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy;
        ListNode sencod = dummy;
        for (int i = 1; i <= n+1; i++) {
            first = first.next;
        }
        while (first != null){
            first = first.next;
            sencod = sencod.next;
        }
        sencod.next = sencod.next.next;
        return dummy.next;

    }

}
