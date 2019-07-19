package com.tom.oj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */
public class NumArrayTest {

    private NumArray numArray;
    private NumArray2 numArray2;

    @Test
    public void removeElements(){
        int[] nums =  {-2, 0, 3, -5, 2, -1};
        numArray = new NumArray(nums);
        //用线段树的方法求区间和
        assertEquals(numArray.sumRange(0,2),1);
        assertEquals(numArray.sumRange2(0,2),1);
        assertEquals(numArray.sumRange3(0,2),1);

    }

    @Test
    public void test(){
        int[] nums2 =  {-2, 0, 3, -5, 2, -1};
        numArray2 = new NumArray2(nums2);
        //用线段树的方法求区间和
        assertEquals(numArray2.sumRange(0,2),1);
        numArray2.update(0,1);
        assertEquals(numArray2.sumRange(0,2),4);
    }
}
