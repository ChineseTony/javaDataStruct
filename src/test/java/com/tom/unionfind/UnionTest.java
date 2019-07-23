package com.tom.unionfind;

import com.tom.util.UFUtil;
import org.junit.Before;
import org.junit.Test;


/**
 * @author WangTao
 */
public class UnionTest {
    private UnionFind u;

    private int size;

    private int m;


    @Before
    public void initData(){
        size = 10000;
        m = 10000;
        u = new QuickFind(size);

    }
    @Test
    public void testTime(){
        System.out.println(UFUtil.testUF(u,m)+"s");
        u = new QuickUnion(size);
        System.out.println(UFUtil.testUF(u,m)+"s");
        u = new QuickUnionSize(size);
        System.out.println(UFUtil.testUF(u,m)+"s");
        u = new QuickUnionRank(size);
        System.out.println(UFUtil.testUF(u,m)+"s");
        u = new QuickUnionCompress(size);
        System.out.println(UFUtil.testUF(u,m)+"s");
    }
}
