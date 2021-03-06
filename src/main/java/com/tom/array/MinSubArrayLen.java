package com.tom.array;

import java.util.Arrays;

public class MinSubArrayLen {

    private MinSubArrayLen(){

    }

    //1.暴力破解
    public static int minSubArrayLen(int s, int[] nums) {
        int result = 0;
        if (nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tmpVal = nums[i];
            if (tmpVal >= s){
                return 1;
            }
            for (int j = i+1; j < len; j++) {
                tmpVal += nums[j];
                if (tmpVal >= s){
                    int tmp = j -  i + 1;
                    if (result == 0){
                        result = tmp;
                    }else {
                        result = Math.min(result,tmp);
                    }
                    System.out.println(i + " --- "+j  +"--->" +tmpVal);
                    break;
                }
            }
        }
        return  result;
    }


    // 滑动窗口
    public static int minSubArrayLen2(int s, int[] nums) {
        int result = 0;
        if (nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;
        int sum = 0;
        int j = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            while (sum >= s){
                result = result == 0 ? i - j + 1 : Math.min(i-j+1,result);
                sum -= nums[j++];
            }
        }

        return  result;
    }


    //todo fix bug
    public boolean checkSubarraySum(int[] nums, int k) {

        if (nums == null || nums.length == 0 ){
            return false;
        }
        k = Math.abs(k);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tmp = nums[i];
            if (tmp % k >=  2 ){
                return true;
            }
            for (int j = i+1; j < len; j++) {
                tmp += nums[j];
                if (tmp  % k >= 2){
                    return true;
                }
            }
        }

        return false;
    }

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1){
            return 0;
        }
        int len = nums.length;
        int i = 0;
        int j =len - 1;
        int[] tmp = new int[len];
        System.arraycopy(nums,0,tmp,0,len);
        Arrays.sort(tmp);
        int result = 0;
        while (i < j){
            if (tmp[i] == nums[i]){
                i++;
            }
            if (tmp[j] == nums[j]){
                j--;
            }
            if (tmp[i] != nums[i] && tmp[j] != nums[j]){
                result = j - i +1;
                break;
            }
        }
        return result;
    }


    public static int findUnsortedSubarray1(int[] nums) {
        if (nums == null || nums.length < 2){
            return 0;
        }
        int len = nums.length;
        int result = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i+1] < nums[i]){
                left = i;
                break;
            }
        }

        for (int i = len-1; i > 0 ; i--) {
            if (nums[i] < nums[i - 1]){
                right = i;
                break;
            }
        }

        return left == right ? 0 : right - left + 1;
    }



    public static int reverse(int x) {
        long sum = 0L;
        while (x != 0){
            sum = sum * 10 + x % 10;
            if( sum > Integer.MAX_VALUE ||  sum < Integer.MIN_VALUE){
                sum = 0;
                break;
            }
            x /= 10;
        }
        return (int)sum;

    }

    public double average(int[] salary) {
        int len = salary.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        double sum = 0;
        for (int tmp:salary){
            sum += tmp;
            if (tmp < min){
                min = tmp;
            }
            if (tmp > max){
                max = tmp;
            }
        }
        return (sum - max - min ) / (len-2);

    }



    public static void main(String[] args) {
        int[] nums = new int[]{9, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(nums));
        System.out.println(findUnsortedSubarray1(nums));
        System.out.println(reverse(-2147483648));
    }
}
