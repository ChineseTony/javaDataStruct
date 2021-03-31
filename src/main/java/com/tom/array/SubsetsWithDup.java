package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @link https://leetcode-cn.com/problems/subsets-ii/
 */
public class SubsetsWithDup {

    private   List<List<Integer>> result = new ArrayList<>();

    private   List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false,nums,0);
        return result;
    }

    private void dfs(boolean pre,int[] nums,int i){
//        满足结束条件
        if (i == nums.length){
            result.add(new ArrayList<>(tmp));
            return;
        }
        dfs(false,nums,i+1);
//        过滤函数
        if (!pre && i > 0 && nums[i - 1] == nums[i]) {
            return;
        }
        tmp.add(nums[i]);
        dfs(true,nums,i+1);
        //取消选择
        tmp.remove(tmp.size() -1);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        SubsetsWithDup subsetsWithDup = new SubsetsWithDup();

        List<List<Integer>> result =  subsetsWithDup.subsetsWithDup(nums);
        for (List<Integer> tmp:result){
            System.out.println(tmp);
        }
    }
}
