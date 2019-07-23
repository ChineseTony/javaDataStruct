package com.tom.unionfind;

/**
 * @author WangTao
 */
public class QuickUnionCompress implements UnionFind {
    private int[] parent;

    //rank[i]表示以i为根节点 树的深度
    private int[] rank;

    public QuickUnionCompress(int size){
        parent = new int[size];
        rank = new int [size];
        for (int i = 0; i < size; i++) {
            //每一个元素 的根节点指向自己
            parent[i] = i;
            rank [i] = 1;
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
        if (rank[pParent] < rank [qParent]){
            parent [pParent]= qParent;
        }else if(rank [pParent] > rank [qParent]){
            parent [qParent] = pParent;
        }else {
            parent [qParent] = pParent;
            rank [pParent] += 1;
        }
    }

    private int find(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p index error");
        }
        while (p != parent [p]){
            //路径压缩降低树的高度
            parent [p] = parent [parent [p]];
            p = parent [p];
        }
        return p;
    }
}
