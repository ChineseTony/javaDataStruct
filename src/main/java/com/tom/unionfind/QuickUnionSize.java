package com.tom.unionfind;

/**
 * @author WangTao
 */
public class QuickUnionSize implements UnionFind {

    private int[] parent;

    //表示集合元素的个数
    private int[] sz;

    public QuickUnionSize(int size){
        parent = new int[size];
        sz = new int [size];
        for (int i = 0; i < size; i++) {
            //每一个元素 的根节点指向自己
            parent[i] = i;
            sz [i] = 1;
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
        //并查集对union操作的优化
        if (sz [pParent] < sz [qParent]){
            parent [pParent] = qParent;
            sz [qParent] += sz[pParent];
        }else{
            parent [qParent] = pParent;
            sz [pParent] += sz[qParent];
        }
    }

    private int find(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p index error");
        }
        while (p != parent [p]){
            p = parent [p];
        }
        return p;
    }
}
