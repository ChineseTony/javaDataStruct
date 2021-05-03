package com.tom.array;


import com.tom.unionfind.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tom
 */
public class MakeConnected {

    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n -1){
            return -1;
        }
        UF uf = new UF(n);
        for (int i = 0; i < connections.length; i++) {
            uf.unionElement(connections[i][0],connections[i][1]);
        }
        return uf.getSize()-1;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] nums = new int[][]{
                {0,1},{0,2},{0,3},{1,2},{2,4}};
        System.out.println(makeConnected(n,nums));

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String,List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String key =new String(c);
            if (map.containsKey(key)) {
                 map.get(key).add(strs[i]);
            }else {
                List<String> tmp = new ArrayList<>();
                tmp.add(strs[i]);
                map.put(key,tmp);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<String,List<String>> entry:map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }

    private String getKey(String s){
        if (s == null || s.length() <= 0){
            return s;
        }
        char[] chars = new char[26];
        for (char c:s.toCharArray()){
            chars[c -'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    static class UF implements UnionFind{
        private int[] parent;
        private int len;
        public UF(int len) {
            this.len = len;
            parent = new int[len];
            for (int i = 0; i < len; i++) {
                //每一个元素 的根节点指向自己
                parent[i] = i;
            }
        }


        @Override
        public int getSize() {
            return len;
        }

        @Override
        public boolean isConnect(int p, int q) {
            int pParent = getParent(p);
            int qParent = getParent(q);
            return pParent == qParent;
        }

        @Override
        public void unionElement(int p, int q) {
            int pParent = getParent(p);
            int qParent = getParent(q);
            if (pParent == qParent){
                return;
            }
            parent[pParent] = qParent;
            len--;
        }

        private int getParent(int p){
            if (p < 0 || p >= parent.length){
                throw new RuntimeException("p index error");
            }
            while (parent[p] != p){
                p = parent[p];
            }
            return p;
        }
    }
}
