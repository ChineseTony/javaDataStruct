package com.tom.oj;


import com.tom.unionfind.QuickUnion;
import com.tom.unionfind.QuickUnionSize;
import com.tom.unionfind.UnionFind;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author tom
 * @link https://pintia.cn/problem-sets/15/problems/840
 * 并查集算法
 * parent[i] = i
 */
public class PenYouQuan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
//        int[] all = new int[m];
        UnionFindSize unionFind = new UnionFindSize(m);
        while (n-- > 0){
            int times = scanner.nextInt();
            int len = times;
            int[] arr = new int[len];
            int index = 0;
            while (times -- >0){
                arr[index++] = scanner.nextInt();
            }
            for (int i = 1; i < len; i++) {
                //union 合并到一个集合中
                unionFind.unionElement(arr[0]-1,arr[i]-1);
            }
        }
        System.out.println(unionFind.getMaxSize());

    }

    static class UnionFindSize implements UnionFind{
        private int[] size;

        private int[] parent;

        public UnionFindSize(int len){
            parent = new int[len];
            size = new int[len];
            for (int i = 0; i < len; i++) {
                parent[i] = i;
                size[i] = 1;
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
            if (size [pParent] < size [qParent]){
                parent [pParent] = qParent;
                size [qParent] += size[pParent];
            }else{
                parent [qParent] = pParent;
                size [pParent] += size[qParent];
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

        public int getMaxSize(){
            int max = 0;
            for (int i = 0; i < size.length; i++) {
                max = Math.max(max,size[i]);
            }
            return max;
        }

    }


}
