package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author tom
 */
public class LargestNumber {

    public static String largestNumber(int[] nums) {
        int len = nums.length;
        List<String> list = new ArrayList<>(len);
        for(int i:nums){
            list.add(String.valueOf(i));
        }
        Collections.sort(list, (a, b) -> {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        });
        StringBuilder sb = new StringBuilder();
        if ("0".equals(list.get(0))){
            return "0";
        }
        for(String s:list){
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,2};
        System.out.println(largestNumber(nums));
    }
}
