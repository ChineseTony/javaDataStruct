package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumAbsDifference {

    private MinimumAbsDifference(){

    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int min = arr[1] - arr[0];
        int len = arr.length;
        for (int i = 0; i < len-1; i++) {
            min = Math.min(arr[i+1] - arr[i] ,min);
        }
        for (int i = 0; i < len-1; i++) {
            if(arr[i+1] - arr[i] == min){
                List<Integer> tmp = new ArrayList<>();
                tmp.add(arr[i]);
                tmp.add(arr[i+1]);
                result.add(tmp);
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,2,1,3};
        List<List<Integer>> tmp = minimumAbsDifference(arr);
        System.out.println(tmp);
    }
}
