package com.tom.array;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class SearchMatrix {


    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix[0].length;
        int j = matrix.length;
        for (int k = 0; k < j; k++) {
            if (matrix[k][0] <= target && matrix[k][i-1] >= target){
                int bottom = 0;
                int top = i -1;
                while (bottom <= top){
                    int mid = (top - bottom) / 2 + bottom;
                    if (matrix[k][mid] == target){
                        return true;
                    }else if (matrix[k][mid] < target){
                        bottom = mid+1;
                    }else {
                        top = mid-1;
                    }
                }
            }
        }
        return false;
    }



    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,3,5,7},{10,11,16,20},{23,30,34,60}

        };
        System.out.println(searchMatrix(arr,13));
    }

}
