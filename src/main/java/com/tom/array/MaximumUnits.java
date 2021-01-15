package com.tom.array;


import java.util.*;

/**
 * @author WangTao
 */
public class MaximumUnits {


    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        int ans = 0;
        int i = 0;
        while (truckSize != 0 && i < boxTypes.length) {
            if (truckSize >= boxTypes[i][0]) {
                truckSize -= boxTypes[i][0];
                ans += boxTypes[i][0] * boxTypes[i][1];
            } else {
                ans += truckSize * boxTypes[i][1];
                truckSize = 0;
            }
            ++i;
        }
        return ans;
    }


    public void rotate(int[] nums, int k) {
        if(nums == null || k <= 0){
            return;
        }
        int len = nums.length;
        k = k % len;
        while (k-- > 0){
            int tmp = nums[len - 1];
            for (int i = len-1; i > 0; i--) {
                nums[i] = nums[i-1];
            }
            nums[0] = tmp;
        }

    }

    public List<Boolean> prefixesDivBy5(int[] a) {
        List<Boolean> result = new ArrayList<>();
        if(a == null | a.length == 0){
            return result;
        }
        int len = a.length;
        int prefix = 0;
        for(int i=0;i<len;i++){
            prefix = ((prefix << 1) + a[i]) % 5;
            result.add(prefix == 0);
        }
        return result;

    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3},{5,5},{2,5},{4,2},{4,1},{3,1},{2,2},{1,3},{2,5},{3,2}};
        int truckSize = 35;
        System.out.println(maximumUnits(arr,truckSize));
    }
}
