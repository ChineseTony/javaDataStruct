package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxIncreaseKeepingSkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int i = grid.length;
        int j = grid[0].length;
        List<Integer> tmp = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int k = 0; k < i; k++) {
            int max = grid[k][0];
            for (int l = 1; l < j; l++) {
                max = Math.max(max,grid[k][l]);
            }
            tmp.add(max);
        }
        for (int k = 0; k < j; k++) {
            int max = grid[0][k];
            for (int l = 0; l < i; l++) {
                max = Math.max(max,grid[l][k]);
            }
            right.add(max);
        }
        int result = 0;
        for (int k = 0; k < j; k++) {
            for (int l = 0; l < i; l++) {
                result += Math.min(right.get(l),tmp.get(k)) - grid[k][l];
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }


}
