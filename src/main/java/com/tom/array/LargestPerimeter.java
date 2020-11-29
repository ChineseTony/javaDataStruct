package com.tom.array;

import java.util.Arrays;

public class LargestPerimeter {
    
    private LargestPerimeter(){
        
    }


    public static int largestPerimeter(int[] A) {
        if (A == null || A.length <= 2){
            return 0;
        }
        int len = A.length;
        Arrays.sort(A);
        int i = A[len-1];
        int j = A[len-2];
        int k = A[len-3];
        for (int count = len-1; count >= 3; count--) {
            if (j+k > i){
                break;
            }else {
                i = A[count-1];
                j = A[count-2];
                k = A[count-3];
            }
        }
        return j+k > i ? i+j+k : 0;
    }


    public static int largestPerimeter2(int[] A) {
        if (A == null || A.length <= 2){
            return 0;
        }
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; --i) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }


    public int maximumWealth(int[][] accounts) {
        int i = accounts.length;
        int j = accounts[0].length;
        int max = 0;
        for (int k = 0; k < i; k++) {
            int tmp = 0;
            for (int l = 0; l < j; l++) {
                tmp += accounts[k][l];
            }
            max = Math.max(tmp,max);
        }
        return max;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,6,2,3};
        System.out.println(largestPerimeter(nums));
        
    }
}
