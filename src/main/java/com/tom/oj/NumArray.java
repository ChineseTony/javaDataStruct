package com.tom.oj;

import com.tom.tree.SegmentTree;

/**
 * @author WangTao
 *
 * 题目描述
 * 评论 (135)
 * 题解(8)New
 * 提交记录
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    private Integer[]  data;

    private Integer[] nums;

    private int[] sum;




    public NumArray(int[] nums) {
        if (nums.length > 0){
            data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
            segmentTree = new SegmentTree<>(data,(a,b) -> a + b);
        }

    }

    public int sumRange(int i, int j) {
        if (segmentTree == null){
            throw new RuntimeException("error");
        }
        return segmentTree.query(i,j);
    }
    
    public int sumRange2(int i,int j){
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += data[k];
        }
        return sum;
    }

    public int sumRange3(int i,int j){
        return sum[j+1] - sum[i];
    }
}
