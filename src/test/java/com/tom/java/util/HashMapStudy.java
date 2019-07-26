package com.tom.java.util;

import java.util.HashMap;
import	java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * @author WangTao
 */
public class HashMapStudy {

    private Map<Integer, String> map;

    private static final int MAXIMUM_CAPACITY = 1 << 30;



    @Before
    public void initData(){
        map = new HashMap<>();

    }


    @Test
    public  void removeElements() {
        assertEquals(HashMapStudy.tableSizeFor(33) ,64);
    }

    //找到 >= cap 的2次幂 的数
    private static  int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
