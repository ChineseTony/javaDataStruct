package com.tom.unionfind;

/**
 * @author WangTao
 */
public class QuickUnion implements UnionFind {

    private int[] parent;

    public QuickUnion(int size){
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            //每一个元素 的根节点指向自己
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnect(int p, int q) {
        return false;
    }

    @Override
    public void unionElement(int p, int q) {
        parent[p] = q;
    }
}
