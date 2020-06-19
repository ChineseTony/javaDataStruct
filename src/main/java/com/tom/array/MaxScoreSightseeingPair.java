package com.tom.array;


/**
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）
 * ：景点的评分之和减去它们两者之间的距离。
 * 1.暴力求解 o(n2)
 * 2. A[i] + A[j] + i - j 改成 A[i]+i + A[j]-j 动态更新A[i]+[i] 和 A[j]-j
 *
 * 来源：力扣（LeetCode）
 * @https://leetcode-cn.com/problems/best-sightseeing-pair
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class MaxScoreSightseeingPair {

    public static int maxScoreSightseeingPair(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        int len = A.length;
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                int val = A[i] + A[j] + i - j;
                if (val > max){
                    max = val;
                }
            }
        }
        return max;

    }

    public static int maxScoreSightseeingPair2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int left=A[0],right;
        int MAX = 0;
        for(int j = 1 ; j < A.length ; j++){
            right = A[j]-j;
            MAX = Math.max(MAX,left+right);
            left = Math.max(left,A[j]+j);
        }
        return MAX;
    }

        public static void main(String[] args) {
        int[] arr = new int[]{8,1,5,2,6};
        System.out.println(maxScoreSightseeingPair(arr));
    }
}
