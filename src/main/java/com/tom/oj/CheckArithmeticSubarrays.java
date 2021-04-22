package com.tom.oj;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tom
 */
public class CheckArithmeticSubarrays {

    public static List<Boolean> checkArithmeticSubarrays(int[] nums,
                                                  int[] l, int[] r) {
        int len = l.length;
        List<Boolean> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            result.add(checkArr(nums,l[i],r[i]));
        }
        return result;

    }

    private static boolean checkArr(int[] nums,int left,int right){
        int len = right - left + 1;
        if (len <= 2){
            return true;
        }
        int[] tmp = new int[len];
        System.arraycopy(nums,left,tmp,0,len);
        Arrays.sort(tmp);
        int sub = tmp[1] - tmp[0];
        for (int i = 1; i < len-1; i++) {
            if (tmp[i+1] - tmp[i] != sub){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,6,5,9,3,7};
        int[] l = new int[]{0,0,2};
        int[] r = new int[]{2,3,5};
        System.out.println(checkArithmeticSubarrays(nums,l,r));
    }
}
