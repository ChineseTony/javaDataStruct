package com.tom.list;

import com.tom.util.ListNode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */
public class ListOperateTest {

    private ListOperate listOperate;

    private ListNode head;
    private ListNode head1;
    private ListNode head2;

    @Before
    public void initData(){
        int[] nums = new int[]{1,1,2,2,3,3};
        listOperate = new ListOperate();
        head = new ListNode(nums);

        int[] nums1 = new int[]{1,2,9};
        int[] nums2 = new int[]{4,5,9};
        head1 = new ListNode(nums1);
        head2 = new ListNode(nums2);
       // System.out.println(listOperate.deleteDuplicates(head));
        //2->2->3->3->NULL

    }

    @Test
    public void removeElements(){
        assertEquals(listOperate.length(head),6);
        assertEquals(listOperate.removeElements3(head,1).toString(),
                "2->2->3->3->NULL");
        //暂时有问题
        //assertEquals(listOperate.length(head),4);

    }

    @Test
    public void mergeTwoLists(){
        assertEquals(listOperate.mergeTwoLists(head1,head2).toString(),
                "1->2->4->5->9->9->NULL");


    }

    @Test
    public void deleteDuplicates(){
        assertEquals(listOperate.deleteDuplicates(head).toString(),
                "1->2->3->NULL");

    }

    @Test
    public void sortList(){
        int[] nums = new int[]{6,9,2,3,8};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        //暂时有问题 进行2次排序后 打印的不是 2->3->6->8->9
        System.out.println(listOperate.insertsortList(head).toString());
        System.out.println(listOperate.insertsortList(head).toString());
    }

    @Test
    public void selectSortList(){
        int[] nums = new int[]{6,9,2,3,8};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        assertEquals(listOperate.selectSortList(head).toString(),
                "2->3->6->8->9->NULL");

    }

    @Test
    public void oddEvenList(){
        int[] nums = new int[]{1,2,3,4,5,6};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        assertEquals(listOperate.oddEvenList(head).toString(),
                "1->3->5->2->4->6->NULL");

    }

    @Test
    public void splitListToParts(){
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        ListNode[] listNodes = listOperate.splitListToParts(head,3);
        for (ListNode node : listNodes){
            System.out.println(node);
        }
    }


    @Test
    public void splitListToParts1(){
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        assertEquals(listOperate.swapPairs1(head).toString(),
                "2->1->4->3->6->5->8->7->NULL");

    }

    @Test
    public void reversePart(){
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        assertEquals(listOperate.reversePart(head,1,5).toString(),
                "5->4->3->2->1->6->7->8->NULL");

    }

    @Test
    public void reversePart2(){
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        assertEquals(listOperate.reversePart(head,2,5).toString(),
                "1->5->4->3->2->6->7->8->NULL");

    }


    @Test
    public void plusOne(){
        int[] nums = new int[]{9,8,7,6,5,4,3,2,1,0};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        assertEquals(listOperate.plusOne(head).toString(),
                "9->8->7->6->5->4->3->2->1->1->NULL");

    }


    @Test
    public void partitionList(){
        int[] nums = new int[]{9,8,7,6,5,4,3,2,1,0};
        listOperate = new ListOperate();
        head = new ListNode(nums);
        System.out.println(listOperate.partitionList(head,5));


    }


    @Test
    public void addTwoNumbers(){
        int[] nums1 = new int[]{5,3};
        int[] nums2 = new int[]{5,6,9};
        listOperate = new ListOperate();
        head = new ListNode(nums1);
        head2 = new ListNode(nums2);
        assertEquals(listOperate.addTwoNumbers(head,head2).toString(),
                "0->0->0->1->NULL");


    }


    @Test
    public void deleteDuplicates2(){
        int[] nums1 = new int[]{1,2,3,3,3,4,4,5,5};
        listOperate = new ListOperate();
        head = new ListNode(nums1);
        System.out.println(listOperate.deleteDuplicates2(head).toString());
    }





}
