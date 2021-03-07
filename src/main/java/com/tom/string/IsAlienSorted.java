package com.tom.string;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tom
 */
public class IsAlienSorted {

    public boolean isAlienSorted(String[] words, String order) {
        int len = words.length;
        for (int i = 0; i < len - 1; i++) {
            if (!isAlien(words[i], words[i + 1], order)) {
                return false;
            }
        }
        return true;
    }


    private boolean isAlien(String s1, String s2, String order) {
        int len1 = s1.length();
        int len2 = s2.length();
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (order.indexOf(s1.charAt(i))
                    > order.indexOf(s2.charAt(j))) {
                return false;
            } else if (order.indexOf(s1.charAt(i))
                    < order.indexOf(s2.charAt(j))) {
                return true;
            }
            i++;
            j++;
        }
        if (i == len1 && j == len2) {
            return true;
        } else if (i == len1) {
            return true;
        } else {
            return false;
        }
    }


    public static String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                //前面一个字符  如果当前是第0个的话 字符就为‘ ’
                char ahead = i == 0 ? ' ' : chars[i - 1];
                //后面一个字符  如果当前是最后一个的话 字符就为‘ ’
                char behind = i == chars.length - 1 ? ' ' : chars[i + 1];
                //从a开始比较  如果等于前面或者后面的话 就+1
                char temp = 'a';
                while (temp == ahead || temp == behind) {
                    temp++;
                }
                //找到目标字符后 做替换
                chars[i] = temp;
            }
        }
        return new String(chars);
    }


    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int index = 0;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            int tmp = nums[i];
            int j = i;
            boolean flag = false;
            while (j < len){
                if (nums[j] > tmp){
                    result[index++] = nums[j];
                    flag = true;
                    break;
                }
                j++;
            }
            if (!flag){
                for (int k = 0; k < i; k++) {
                    if (nums[k] > tmp){
                        result[index++] = nums[k];
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    result[index++] = -1;
                }

            }

        }
        return result;
    }


    /**
     * 单调栈
     * @param nums
     * @return
     */
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

    public static void main(String[] args) {
        String[] words = new String[]{
                "word", "row"
        };
        String order = "worldabcefghijkmnpqstuvxyz";

        System.out.println(new IsAlienSorted().isAlienSorted(words,
                order));
        System.out.println(modifyString("?zs"));


    }
}
