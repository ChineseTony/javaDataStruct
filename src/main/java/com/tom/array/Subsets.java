package com.tom.array;

import java.util.ArrayList;
import java.util.List;


/**
 * @author tom
 * @link https://leetcode-cn.com/problems/subsets/
 */
public class Subsets {

    private  List<List<Integer>> result = new ArrayList<>();

    private  List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums,0);
        return result;
    }

    private void dfs(int[] nums,int i){
//        满足结束条件
        if (i == nums.length){
            result.add(new ArrayList<>(tmp));
            return;
        }
        dfs(nums,i+1);
        tmp.add(nums[i]);
        dfs(nums,i+1);
        //取消选择
        tmp.remove(tmp.size() -1);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        new Subsets().subsets(nums)
                .forEach(System.out::println);
    }
}
