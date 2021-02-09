package com.tom.array;



/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/container-with-most-water/
 */
public class MaxArea {

    public static int maxArea(int[] height) {
        if (height == null || height.length <= 0){
            return 0;
        }
        int result = 0;
        int len = height.length;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len ; j++) {
                int temp = Math.min(height[i], height[j])
                        * (j -i);
                if (temp > result){
                    result = temp;
                }
            }
        }
        return result;

    }


    public static int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }else {
                --r;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
}
