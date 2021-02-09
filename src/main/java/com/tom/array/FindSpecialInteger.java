package com.tom.array;


import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import java.util.Map;


/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/sum-of-unique-elements/
 */
public class FindSpecialInteger {

    public int findSpecialInteger(int[] arr) {
        int len = arr.length;
        int count = 0;
        int tmp = arr[0];
        for(int i=0;i<len;i++){
            if(arr[i] == tmp){
                count++;
            }else{
                count = 1;
                tmp = arr[i];
            }
            if(count * 4 > len){
                return tmp;
            }
        }
        return -1;
    }


    public int sumOfUnique(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(16);
        int sum = 0;
        for (int i : nums){
            if(!map.containsKey(i)){
               map.put(i,1);
            }else {
                map.put(i,map.get(i) + 1);
            }
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            if (entry.getValue() == 1){
                sum += entry.getKey();
            }
        }
        return sum;
    }


    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int result = nums[len-1];
        Set<Integer> set = new HashSet<>();

        for(int i = len-1;i>=0;i--){
            set.add(nums[i]);
            if (set.size() == 3){
                return nums[i];
            }
        }
        return result;
    }
}
