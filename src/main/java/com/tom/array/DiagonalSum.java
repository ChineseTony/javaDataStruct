package com.tom.array;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtao .
 * @createTime on 2020/10/11
 * @link https://leetcode-cn.com/problems/matrix-diagonal-sum/
 */

public class DiagonalSum {

    public static int diagonalSum(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0){
            return 0;
        }
        int sum = 0;
        int i = mat.length;
        int j = mat[0].length;

        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                //添加主对角线
                if (k == l){
                    sum += mat[k][l];
                }
            }
            //不是主对角线
            if (k != j - 1 -k){
                sum += mat[k][ j - 1 -k];
            }

        }
        return sum;

    }


    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length ==  1){
            return true;
        }
        Arrays.sort(arr);
        int sub = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i-1] != sub){
                return false;
            }
        }
        return true;
    }


    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i:nums){
            if (!map.containsKey(i)){
                map.put(i,1);
            }else {
                map.put(i,map.get(i)+1);
            }
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            if (entry.getValue() > len /2){
                return entry.getKey();
            }
        }
        return -1;
    }


    public static int majorityElement2(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        System.out.println(diagonalSum(arr));

        int[] tmp = new int[]{2,2,1,3,1,1,4,1,1,5,1,1,6};
        majorityElement2(tmp);
    }
}
