package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMedianSortedArrays {

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        if (n <= 0){
            return list;
        }
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0){
                list.add("FizzBuzz");
            }else if (i % 3 == 0){
                list.add("Fizz");
            }else if (i % 5 == 0){
                list.add("Buzz");
            }else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if ((len1 + len2) % 2 == 0){
            return -1;
        }else {
            int mid = (len1 + len2) /2 + 1;
            int index = 0;
            int i = 0;
            int j = 0;
            while (i < len1 && j < len2 && index < mid){
                if (nums1[i] <= nums2[j]){
                    i ++;
                }else {
                    j++;
                }
                index++;
            }
            if (i == len1 && index != mid){
                while (index < mid){
                    j++;
                    index++;
                }
                return nums2[j];
            }else if (j == len2 && index != mid){
                while (index < mid){
                    i++;
                    index++;
                }
                return nums2[i];
            }else {
                return -1;
            }
        }

    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] tmp = new int[nums1.length+nums2.length];
        int i = 0;
        for (int j = 0; j < nums1.length; j++) {
            tmp[i++] = nums1[j];
        }
        for (int j = 0; j < nums2.length; j++) {
            tmp[i++] = nums2[j];
        }
        Arrays.sort(tmp);
        int len = tmp.length;
        if (len % 2 == 1){
            return tmp[len / 2 ];
        }else {
            return (tmp[len / 2] + tmp[len / 2 - 1] ) / 2.0;
        }
    }
}
