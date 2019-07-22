package com.tom.unionfind;

/**
 * @author WangTao
 */
public class QuickFind implements UnionFind {

    //存放
    private int[] id;

    public QuickFind(int size){
        id = new int[size];
        for (int i = 0; i < size; i++) {
            //每一个元素都在不同的集合类
            id[i] = i;
        }
    }


    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if(id[i] == pId){
                id[i]  = qId;
            }
        }
    }

    //查找元素p所在的集合编号
    private int find(int p){
        if (p < 0 || p >= id.length){
            throw new RuntimeException("p index error");
        }
        return id[p];
    }
}
