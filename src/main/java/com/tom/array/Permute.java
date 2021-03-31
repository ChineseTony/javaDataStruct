package com.tom.array;

import java.util.ArrayList;
import java.util.List;

public class Permute {


    private static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        dfs(nums,tmp);
        return result;
    }

    private static void dfs(int[] nums,List<Integer> tmp){
        if (tmp.size() == nums.length){
            result.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])){
                continue;
            }
            tmp.add(nums[i]);
            dfs(nums,tmp);
            tmp.remove(tmp.size() -1);
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> tmp = permute(nums);
        for (List<Integer> t:tmp){
            System.out.println(t);
        }

    }
}
