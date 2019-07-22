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
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pParent = find(p);
        int qParent = find(q);
        if (pParent == qParent){
            return;
        }
        //合并操作
        parent[pParent] = qParent;
    }

    private int find(int p){
        if (p < 0 || p >= parent.length){
            throw new RuntimeException("p index error");
        }
        //查找根节点
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }
}
