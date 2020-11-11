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


    /**
     * 字符串求和
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();

    }


    /**
     * @link https://leetcode-cn.com/problems/student-attendance-record-i/
     * @param s
     * @return
     * "PPALLPLLP"
     * true
     */
    public static boolean checkRecord(String s) {
        if (s == null || s.length() == 0){
            return true;
        }
        int len = s.length();
        int count = 0;
        int i = 0;
        while (i < len) {
            if (s.charAt(i) == 'A'){
                count++;
            }else if (s.charAt(i) == 'L'){
                int tmp = 1;
                while (i < len-1 && s.charAt(i+1) == 'L'){
                    tmp++;
                    if (tmp > 2){
                        return false;
                    }
                    i++;
                }
            }
            i++;
            if (count > 1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isLongPressedName("leelee","lleeelee"));

        int[] nums = new int[]{2,3,4,7,11};
//缺失的正整数包括 [1,5,6,8,9,10,12,13,...]
        System.out.println(findKthPositive(nums,8));

        System.out.println(checkRecord("PLLL"));
    }
}
