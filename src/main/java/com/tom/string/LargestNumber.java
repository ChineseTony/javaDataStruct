package com.tom.string;

import java.util.*;

public class LargestNumber {
    private LargestNumber(){

    }


    public static String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return "";
        }
        int len = nums.length;
        // 思路找首字母数字最大的 如果
        List<String> list = new ArrayList<>();
        for (int i=0;i<len;i++){
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);
            }
        });
        if ("0".equals(list.get(0))) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s:list){
            sb.append(s);
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        int[] tmp = new int[]{34323,3432};
        System.out.println(largestNumber(tmp));

    }
}
