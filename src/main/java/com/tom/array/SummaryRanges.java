package com.tom.array;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {


    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return  result;
        }
        int len = nums.length;
        if (len == 1){
            result.add(String.valueOf(nums[0]));
            return  result;
        }
        int i = 0;
        while (i < len){
            String tmp = "";
            int start = 0;
            int end = 0;
            while (i < len-1){
                if (nums[i+1] - nums[i] == 1 ){
                    end++;
                }else {
                    if (start == end){
                        tmp = String.valueOf(nums[start]);
                    }else {
                        tmp = nums[start]+"->"+nums[end];
                    }
                    result.add(tmp);
                    start = i+1;
                    end = start;
                }
                i++;
            }
            if (start == end){
                result.add(String.valueOf(nums[start]));
            }else {
                result.add(nums[start]+"->"+nums[end]);
            }
            i++;
        }
        return result;
    }


    public static List<String> summaryRanges2(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) {
                continue;
            }
            if (i == j) {
                summary.add(nums[i] + "");
            }else {
                summary.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1;
        }
        return summary;

    }

    public static int lastRemaining2(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    public static int lastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; ++i) {
            f = (m + f) % i;
        }
        return f;
    }


    public int dominantIndex(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (maxIndex != i && nums[maxIndex] < 2 * nums[i]) {
                return -1;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,3,4,6,8,9,12};
        List<String> tmp = summaryRanges2(nums);
        tmp.forEach(System.out::println);

        System.out.println(lastRemaining2(5,3));
    }
}
