package com.tom.array;


import java.util.*;

public class IsPossible {


    public static boolean isPossible(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i:nums){
            if (!map.containsKey(i)){
                map.put(i,new PriorityQueue<>());
            }
            if (map.containsKey(i-1)){
                int prevLen = map.get(i-1).poll();
                if (map.get(i-1).isEmpty()){
                    map.remove(i-1);
                }
                map.get(i).offer(prevLen+1);
            }else {
                map.get(i).offer(1);
            }
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>>  entry:entrySet){
            if (entry.getValue().peek() < 3){
                return false;
            }
        }
        return true;
    }


    public static boolean isPossible2(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        Map<Integer, Integer> countNum = new HashMap<>();
        for (int num : nums) {
            countNum.put(num, countNum.getOrDefault(num, 0) + 1);
        }
        //定义一个哈希表记录最长的子序列
        Map<Integer, Integer> tail = new HashMap<>();
        for (int num : nums) {
            int count = countNum.getOrDefault(num, 0);
            if (count <= 0) {//当前元素已经用完，直接跳过
                continue;
            } else if (tail.getOrDefault(num - 1, 0) > 0) {
                //前面还有数字，可以构成以num结尾的子序列
                countNum.put(num, count - 1);
                tail.put(num - 1, tail.get(num - 1) - 1);//覆盖当前最长的子序列
                tail.put(num, tail.getOrDefault(num, 0) + 1);//当前以num结尾的子序列+1
            } else if (countNum.getOrDefault(num + 1, 0) > 0
                    && countNum.getOrDefault(num + 2, 0) > 0) {
                //前面无数字构成子序列后，判断能不能跟后面的构成子序列
                countNum.put(num, count - 1);
                countNum.put(num + 1, countNum.get(num + 1) - 1);
                countNum.put(num + 2, countNum.get(num + 2) - 1);
                tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);//当前以num+2结尾的子序列+1
            } else
                return false;//前后不能构成子序列直接返回false
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,4,5};

        System.out.println(isPossible(nums));
    }
}
