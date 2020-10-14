package com.tom.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayRankTransform {


    public static int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0){
            return new int[0];
        }
        int len = arr.length;
        int[] result = new int[len];
        System.arraycopy(arr,0,result,0,len);
        Arrays.sort(result);
        Map<Integer,Integer> map = new HashMap<>();
        int index = 1;
        for (int i = 0; i < len; i++) {
            if(i>0 && result[i]!=result[i-1]) {
                map.put(result[i], index);
                index++;
            }
            if(i==0) {
                map.put(result[i], index);
                index++;
            }
        }
        for (int i = 0; i < len; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{};
        int[] result = arrayRankTransform(arr);
        for (int i:result){
            System.out.print(i+"\t");
        }
    }
}
