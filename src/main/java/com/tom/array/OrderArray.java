package com.tom.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @author tom
 * 2个有序数组求交集
 */
public class OrderArray {

    public static int[] searchCommon(int[] arr1,int[] arr2){
        List<Integer> result = new ArrayList<>();
        for (int i:arr1){
            if (binarySearch(arr2,i) != -1){
                result.add(i);
            }
        }
        int len= result.size();
        int[] resultArray = new int[len];
        for (int i = 0; i < len; i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    private static int binarySearch(int[] arr,int target){
        int i = 0;
        int j = arr.length-1;
        while (i <= j){
            int mid = (j - i) + i / 2;
            if (arr[mid] < target){
                i = mid + 1;
            }else if (arr[mid] > target){
                j = mid -1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,3,5,6,9};
        int[] arr2 = new int[]{3,4,5,7,10,11};
        Arrays.stream(searchCommon(arr1, arr2))
                .forEach(v -> System.out.print(v + " "));

    }
}
