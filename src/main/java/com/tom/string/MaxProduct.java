package com.tom.string;

import java.util.Arrays;

/**
 * @author tom
 */
public class MaxProduct {

    public int maxProduct(String[] words) {
        if (words == null || words.length <= 0){
            return 0;
        }
        int len = words.length;
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (!containsSameChar(words[i],words[j])){
                    result = Math.max(result,words[i].length()
                            * words[j].length());
                }
            }
        }
        return result;
    }

    private boolean containsSameChar(String s,String other){
        int[] tmp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            tmp[s.charAt(i) -'a']++;
        }
        for (char c:other.toCharArray()){
            if (tmp[c-'a'] != 0){
                return true;
            }
        }
        return false;
    }

    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length <=0 ){
            return ;
        }
        Arrays.sort(nums);
        if(nums.length <= 2 ){
            return;
        }
        for(int i=1;i< nums.length;i += 2){
            swap(nums,i,i-1);
        }
    }
    private void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j]= tmp;
    }

    public static int xorOperation(int n, int start) {
        int tmp = 0;
        int count = 0;
        while(count++ < n){
            tmp = tmp ^ start;
            start += 2;
        }
        return tmp;

    }

    public static void main(String[] args) {
        String[] strings = new String[]{
                "abcw","baz","foo","bar","xtfn","abcdef"
        };
        System.out.println(new MaxProduct().maxProduct(strings));

    }
}
