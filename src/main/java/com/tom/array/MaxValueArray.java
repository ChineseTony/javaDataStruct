package com.tom.array;

/**
 * @author WangTao
 */
public class MaxValueArray {

    public static int getMax(int[][] arr){
        if (arr == null || arr[0] == null){
            return -1;
        }
        int row = arr.length;
        int col = arr[0].length;
        int[][] maxValue = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int left = 0;
                int up = 0;
                if (i > 0){
                    left = maxValue[i-1][j];
                }
                if (j > 0){
                    up = maxValue[i][j-1];
                }
                maxValue[i][j]=Math.max(left,up)+arr[i][j];
            }
        }
        return maxValue[row-1][col-1];
    }

    public static int getMax2(int[][] arr){
        if (arr == null || arr[0] == null){
            return -1;
        }
        int row = arr.length;
        int col = arr[0].length;
        int[] max = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int left = 0;
                int up = 0;
                if (i > 0){
                    up = max[j];
                }
                if (j > 0){
                    left = max[j-1];
                }
                max[j]=Math.max(left,up)+arr[i][j];
            }
        }
        return max[col-1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[2][4];
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j]=++count;
            }
        }
        printArray(arr);
        System.out.println(getMax(arr));
        System.out.println(getMax2(arr));
    }

    private static void printArray(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
    }

}
