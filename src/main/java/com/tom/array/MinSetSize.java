package com.tom.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author tom
 * @link https://leetcode-cn.com/problems/reduce-array-size-to-the-half/
 */
public class MinSetSize {

    public static int minSetSize(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>(arr.length);
        for(int i:arr){
            if (!map.containsKey(i)) {
                map.put(i,1);
            }else {
                map.put(i,map.get(i)+1);
            }
        }

        List<Integer> al = new ArrayList<>();
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            al.add(set.getValue());
        }
        Collections.sort(al);
        int sum = 0;
        for (int i = 0; i < al.size(); i++) {
            sum = sum + al.get(al.size() - 1 - i);
            if (sum >= arr.length / 2) {
                return i + 1;
            }
        }

        return 0;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,3,3,3,5,5,5,2,2,7};
        minSetSize(arr);


    }
}
