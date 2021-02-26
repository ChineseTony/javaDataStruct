package com.tom.array;



import java.util.Arrays;


/**
 * @author wangtao
 * @link
 */
public class FlipAndInvertImage {

    public static int[][] flipAndInvertImage(int[][] a) {
        //1.逆序
        int i = a.length;
        int j = a[0].length;
        for (int k = 0; k < i; k++) {
            int[] tmp = Arrays.copyOf(a[k],i);
            for (int l = 0; l < j; l++) {
                a[k][l] = tmp[j - l - 1];
            }
        }

        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                if (a[k][l] == 0){
                    a[k][l] = 1;
                }else {
                    a[k][l] = 0;
                }
            }
        }
        return a;
    }


    public static int[][] transpose(int[][] matrix) {

        int i = matrix.length;
        int j = matrix[0].length;
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                int tmp = matrix[k][l];
                matrix[k][l] = matrix[l][k];
                matrix[l][k] = tmp;
            }
        }
        return matrix;

    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,1,0},{1,0,1},{0,0,0}
        };

        flipAndInvertImage(arr);
        System.out.println();

    }
}
