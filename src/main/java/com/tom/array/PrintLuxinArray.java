package com.tom.array;

public class PrintLuxinArray {

    private PrintLuxinArray(){

    }

    public static void printArray(int[][] arr){
        int width = arr[0].length;
        int height = arr.length;
        int count = 0;
        int total = width * height;
        int i = 0,j =0;
        while (i < width || j < height){
            //从左到右
            for (int k = j; k < width - j && count < total; k++) {
                System.out.print(arr[i][k]+"\t");
                count++;
            }
            //从上到下
            for (int k = i+1; k < height - i && count < total; k++) { ;
                System.out.print(arr[k][width - i -1]+"\t");
                count++;
            }
            //从右到左
            for (int k = width - j - 2  ; k >= j && count < total ; k--) {
                System.out.print(arr[height-1-i][k]+"\t");
                count++;
            }
            //从下到上
            for (int k = height - i - 2; k >= i+1 && count < total; k--) {
                System.out.print(arr[k][j]+"\t");
                count++;
            }
            i++;
            j++;
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
//                {9,8,7,6,5},
//                {0,-1,-2,-3,5},
//                {1,2,3,4,5},
                {98,95,93},
                {42,37,81},
                {53,20,76},
                {58,60,76}
        };
        printArray(arr);
    }
}
