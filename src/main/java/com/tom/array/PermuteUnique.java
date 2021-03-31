package com.tom.array;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


/**
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class PermuteUnique {

    private static List<List<Integer>> result = new ArrayList<>();

    private static boolean[] visited;

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums,0,tmp);
        return result;
    }

    private static void dfs(int[] nums,int index,
                            List<Integer> tmp){
        if (index == nums.length){
            result.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1]
                    && !visited[i - 1])) {
                continue;
            }
            tmp.add(nums[i]);
            visited[i] = true;
            dfs(nums,index+1,tmp);
            visited[i] = false;
            tmp.remove(index);
        }
    }



    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        List<List<Integer>> tmp = permuteUnique(nums);
        for (List<Integer> t:tmp){
            System.out.println(t);
        }
    }
}
