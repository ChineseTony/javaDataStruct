package com.tom.oj;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangTao
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }

    /**
     *  利用HashMap 的O(1)查找时间 降低复杂度
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] a = new int[2];
        //建议初始化数据结构容量
        Map<Integer,Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                a[0] = i;
                a[1] = map.get(target-nums[i]);
                break;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 7, 11, 15};
        int[] b = twoSum(a,9);
        for (int i : b){
            System.out.print(i+" ");
        }
        System.out.println();
        b = twoSum(a,9);
        for (int i : b){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
