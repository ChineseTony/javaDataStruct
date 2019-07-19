package com.tom.tree;

/**
 * @author WangTao
 * 线段树
 */
@SuppressWarnings("unchecked")
public class SegmentTree<T> {

    private T[] data;

    // 线段树索引 通过 时间换取空间
    private T[] tree;

    private Merge<T> merge;

    public SegmentTree(T[] t,Merge<T> merge){
        this.merge = merge;
        data = (T[]) new Object[t.length];
        for (int i = 0; i < t.length; i++) {
            data[i] = t[i];
        }
        tree = (T[]) new Object[t.length*4];
        buildSegmentTree(0,0,data.length-1);
    }

    public int getLength(){
        return data.length;
    }

    public T get(int index){
        if (index < 0 || index >= data.length){
            throw new RuntimeException("index error");
        }
        return data[index];
    }

    public T query(int queryL,int queryR){
        if(queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0,0,data.length-1,queryL,queryR);

    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private T query(int treeIndex, int l, int r, int queryL, int queryR){
        if (r == queryR && l == queryL){
            return tree[treeIndex];
        }
        int mid = l + (r - l)/ 2;

        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);
        if (queryL >= mid+1){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }else if (queryR <= mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }
        // [queryL,queryR] 落在不同的区级  擦分成 2个区级递归查询
        T leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        T rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merge.merge(leftResult, rightResult);


    }


    /**
     * 在treeIndex  [left,right]为区级的线段树
     * @param treeIndex
     * @param left
     * @param right
     */
    private void  buildSegmentTree(int treeIndex,int left,int right){

        if (left == right){
            tree[treeIndex] = data[left];
            return;
        }
        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);

        int mid = left + (right - left )/ 2;

        buildSegmentTree(leftTreeIndex,left,mid);
        buildSegmentTree(rightTreeIndex,mid+1,right);
        //业务相关 综合二个线段的信息
        tree[treeIndex] = merge.merge(tree[leftTreeIndex],tree[rightTreeIndex]);


    }

    private int getLeftChild(int i){

        return 2 * i + 1;
    }

    private int getRightChild(int i){
        return  2 * i + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null){
                sb.append(tree[i]);
            }else {
                sb.append("null");
            }
            if (i != tree.length-1){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
