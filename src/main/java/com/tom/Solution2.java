package com.tom;


import java.util.HashMap;
import java.util.Map;

/**
 * @author WangTao
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3     i = 0 j = 3 < 3
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 *
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * 1.暴力 从第一个数匹配 O(n^2)
 * 2.hashmap 0->1 1->2 3->3 1->4contains 1 >k
 */
public class Solution2 {

    public static boolean judgeArr(int[] arr,int k){
        if(arr == null || arr.length == 0){
            return false;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len ; j++) {
                if(arr[i] == arr[j] && j - i <= k){
                    return true;
                }
            }
        }
        return false;
    }

//  2.hashmap 1->0 2->1 3->3 1-4 contains 1 >k
    public static boolean judgeArr2(int[] arr,int k){
        if(arr == null || arr.length == 0){
            return false;
        }
        int len = arr.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            if(map.containsKey(arr[i])){
                int index = map.get(arr[i]);
                if (i - index <= k){
                    return true;
                }else{
//                    更新下标
                    map.put(arr[i],i);
                }

            }else {
                map.put(arr[i],i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,0,4,1,1};
//        0 - >3
        int k = 2;
        System.out.println(judgeArr(nums,k));
        System.out.println(judgeArr2(nums,k));

    }
}
