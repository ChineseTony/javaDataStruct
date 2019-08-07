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


}
