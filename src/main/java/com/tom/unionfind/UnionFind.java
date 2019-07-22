package com.tom.unionfind;

/**
 * @author WangTao
 */
public interface UnionFind {

    int getSize();

    boolean isConnect(int p,int q);

    void unionElement(int p,int q);
}
