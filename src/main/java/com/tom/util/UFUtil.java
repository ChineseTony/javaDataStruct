package com.tom.util;

import com.tom.unionfind.UnionFind;

import java.util.Random;

/**
 * @author WangTao
 */
public class UFUtil {
    public static double testUF(UnionFind uf, int m){

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();


        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElement(a, b);
        }

        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnect(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

}
