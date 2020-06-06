package com.tom.oj;

public class MaxValue {

    public static int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                if(i == 0) {
                    //将左边的加到当前值
                    grid[i][j] += grid[i][j - 1] ;
                }else if(j == 0) {
                    //将上边的加到当前值
                    grid[i][j] += grid[i - 1][j];
                }else{
                    grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }



    public static void main(String[] args) {
        int[][] result = new int[][] {{1,2,5},{3,2,1}};
        int tmp = maxValue(result);
        System.out.println(tmp);

//        System.out.println(result[1][0]);
    }
}
