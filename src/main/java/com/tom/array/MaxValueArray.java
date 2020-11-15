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


    /**
     * 主要考察 桶排序算法
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //第一步：将arr1中数据分配到各桶中
        int[] bucket=new int[1001];
        int[] res=new int[arr1.length];
        for (int data:arr1) {
            bucket[data]++;
        }
        //第二步：按照arr2的顺序收集桶中数据
        int j=0;
        for (int data:arr2){
            //在arr2中的数据 循环遍历 bucket 变成0
            while (bucket[data]-->0) {
                res[j++] = data;
            }
        }
        //第三步：按升序顺序收集桶中其他数据
        for (int i=0;i<1001;i++){
            while (bucket[i]-->0) {
                res[j++] = i;
            }
        }
        return res;

    }


    /**
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        int count = 0;
        double result = 1;
        while (result < n){
            result = Math.pow(3,count);
            count++;
        }
        return  result ==n;
    }

    public boolean isPowerOfThree2(int n) {
        return Integer.toString(n, 3).matches("^10*$");
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
