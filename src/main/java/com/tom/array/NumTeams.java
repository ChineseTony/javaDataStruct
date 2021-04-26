package com.tom.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/count-number-of-teams/
 * todo 优化 timeout
 */
public class NumTeams {

    private static List<List<Integer>> arr = new ArrayList<>();

    public static int numTeams(int[] rating) {
        if (rating == null || rating.length <= 2){
            return 0;
        }
        int count  = 0;
        travel(rating,0,3,new ArrayList<>());
        for (List<Integer> tmp:arr){
            if (isOrderOrDesc(tmp)){
                count++;
            }
            System.out.println(Arrays.toString(tmp.toArray(new Integer[0])));
        }
        return count;

    }

    private static boolean isOrderOrDesc(List<Integer> list){
        if (list == null || list.size() <= 1){
            return true;
        }
        boolean flag = list.get(1) > list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if ((list.get(i) > list.get(i-1)) != flag){
                return false;
            }
        }
        return true;
    }

    private static void travel(int[] nums, int index, int i,
                        List<Integer> tmp){
        if (i == tmp.size()){
            arr.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = index; j < nums.length; j++) {
            tmp.add(nums[j]);
            travel(nums,j+1,i,tmp);
            tmp.remove(tmp.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] tmp = new int[]{2,5,3,4,1};
        int i = numTeams(tmp);
        System.out.println(i);

    }
}
