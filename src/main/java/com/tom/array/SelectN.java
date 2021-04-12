package com.tom.array;


import java.util.ArrayList;
import java.util.List;

/**
 * @author tom
 */
public class SelectN {
    private static List<List<Integer>> result =  new ArrayList<>();
    /**
     * 从 [0~m-1] 选n个
     * @param m
     * @param n
     * @return
     */
    public static List<List<Integer>>  select(int m,int n){
        if (m <= 0 || n <= 0){
            return result;
        }
        List<Integer> tmp = new ArrayList<>();
        dfs(m,1,n,tmp);
        return result;
    }

    private static void dfs(int m,int start,int n,List<Integer> list){
        //结束调节
        if (n == list.size()){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= m; i++) {
            list.add(i);
            dfs(m,i+1,n,list);
            list.remove(list.size()-1);
        }
    }


    public int findMaximumXOR(int[] nums) {
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for(int j=i;j <len;j++){
                max = Math.max(max,nums[i] ^ nums[j]);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        List<List<Integer>> result =
                select(5,2);
        result.forEach(System.out::println);
    }
}
