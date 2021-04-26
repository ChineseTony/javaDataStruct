package com.tom.array;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/
 * capacity-to-ship-packages-within-d-days/solution/
 */
public class ShipWithinDays {

    private boolean canTravel(int[] weights,int d,int k){
        int count = 1;
        int singleTravel = 0;
        for (int i:weights){
            singleTravel += i;
            if (singleTravel > k ){
                count++;
                singleTravel = i;
            }
            if (count > d){
                return false;
            }
        }
        return true;
    }


    /**
     * 从 [max(weights),sum(weights)]找出满足条件的最小值
     * @param weights
     * @param d
     * @return
     */
    public int shipWithinDays(int[] weights, int d) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        while (left < right){
            int mid = (right - left )/2  + left;
            if (canTravel(weights,d,mid)){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }


    public static boolean isValid(String s) {
        if (s == null || s.length() <= 0){
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c:s.toCharArray()){
            if (c != 'c'){
                stack.push(c);
            }else {
                if (stack.size() < 2){
                    return false;
                }else {
                    char tmp = stack.pop();
                    if (tmp != 'b'){
                        return false;
                    }
                    tmp = stack.pop();
                    if (tmp != 'a'){
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }



    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(new ShipWithinDays().shipWithinDays(nums,5));

        System.out.println(isValid("abccba"));
    }
}
