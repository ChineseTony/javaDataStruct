package com.tom.string;


/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/long-pressed-name/
 */
public class LongPressedName {


    /**
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     * 输入：name = "leelee", typed = "lleeelee"
     * 输出：true
     * @param name
     * @param typed
     * @return
     */
    public static boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }


    /**
     * 输入：arr = [2,3,4,7,11], k = 5
     * 输出：9
     * 解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
     * @link https://leetcode-cn.com/problems/kth-missing-positive-number
     * @param arr
     * @param k
     * @return
     */
    public static int findKthPositive(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0){
            return -1;
        }
        int len = arr.length;
        int tmp = 1;
        for (int i = 0; i < len; ) {
            if (tmp != arr[i]){
                k--;
                tmp++;
            }else {
                i++;
                tmp++;
            }
            if (k == 0){
                return tmp-1;
            }
        }
        while (k != 0){
            tmp++;
            k--;
        }
        return tmp -1 ;
    }

    public static void main(String[] args) {
        System.out.println(isLongPressedName("leelee","lleeelee"));

        int[] nums = new int[]{2,3,4,7,11};
//缺失的正整数包括 [1,5,6,8,9,10,12,13,...]
        System.out.println(findKthPositive(nums,8));
    }
}
