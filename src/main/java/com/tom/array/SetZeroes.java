package com.tom.array;

import java.util.ArrayList;
import java.util.List;

public class SetZeroes {

    private SetZeroes(){

    }

    public void setZeroes(int[][] matrix) {
        int i = matrix.length;
        int j = matrix[0].length;
//        统计那一行 那一列有0
        List<Integer> tmp = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                if (matrix[k][l] == 0){
                   tmp.add(k);
                   right.add(l);
                }
            }
        }
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                if (tmp.contains(k) || right.contains(l)){
                    matrix[k][l] = 0;
                }
            }
        }
    }


    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {

    }
}
