package com.tom.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author wangtao
 */
public class CheckInclusion {


    /**
     * @link https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
     * @param s
     * @return
     */
    public static String[] permutation(String s) {
        if (s == null || s.length() <= 0){
            return null;
        }
        int len = s.length();
        Set<String> strings = new HashSet<>();
        perm(s.toCharArray(),0,len-1,strings);
        int setSize = strings.size();
        int index = 0;
        String[] result = new String[setSize];
        for (String tmp:strings){
            result[index++] = tmp;
        }
        System.out.println(Arrays.toString(result));
        return result;

    }

    private static void perm(char[] s, int left, int right, Set<String> set){
        if (right < 0){
            return;
        }
        if (left == right){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= right; i++) {
                sb.append(s[i]);
            }
            set.add(sb.toString());
        }else {
            for (int j = left; j <= right; j++) {
                swap(s, j, left);
                perm(s, left + 1, right, set);
                swap(s, j, left);
            }
        }
    }

    private static void swap(char[] arr,int i,int j){
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * @link https://leetcode-cn.com/problems/permutation-in-string/
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {

        if (s1 == null || s1.length() <= 0){
            return false;
        }
        int len = s1.length();
        Set<String> strings = new HashSet<>();
        perm(s1.toCharArray(),0,len-1,strings);
        for (String tmp:strings){
            if (s2.contains(tmp)){
                return true;
            }
        }
        return false;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
       List<Integer> result = new ArrayList<>();
       int len = nums.length ;
       int[] tmp  =new int[len];
       for (int i = 0; i < len; i++) {
            tmp[nums[i] - 1]++;
        }
        for (int i = 0; i < len; i++) {
            if (tmp[i] == 0) {
                result.add(i + 1);
            }
        }
       return result;

    }



    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] > 0) {
                nums[Math.abs(nums[i]) - 1] = - nums[Math.abs(nums[i]) - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                results.add(i + 1);
            }
        }
        return results;
    }


    public int numIdenticalPairs(int[] nums) {
        int result = 0;
        if(nums == null || nums.length <= 0){
            return result;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tmp = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[i] == nums[j]){
                    tmp ++ ;
                }
            }
            result += tmp;
        }
        return  result;

    }

    public int numIdenticalPairs2(int[] nums) {
        int[] temp = new int[101];
        for(int i:nums){
            ++temp[i];
        }
        int count=0;
        for(int i:temp){
            if(i != 0){
                count+=(i*(i-1)/2);
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String s = "abc";
        permutation(s);
        int[] t = new int[]{4,3,2,7,8,2,3,1};
        findDisappearedNumbers(t)
                .forEach(System.out::println);

    }


}
