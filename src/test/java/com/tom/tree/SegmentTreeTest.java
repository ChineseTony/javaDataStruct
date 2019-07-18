package com.tom.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */
public class SegmentTreeTest {
    private SegmentTree<Integer> segmentTree;

    @Before
    public void initData(){
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        segmentTree = new SegmentTree<>(nums,(a,b)-> a+b);
    }


    @Test
    public void test(){
        System.out.println(segmentTree);
        assertEquals(segmentTree.query(0,2).intValue(),1);
    }
}
