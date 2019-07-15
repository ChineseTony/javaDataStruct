package com.tom.oj;

import java.util.Stack;

/**
 * @author WangTao
 * Leetcode 1047
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 示例：
 *
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb"
 * 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
 * 之后我们得到字符串 "aaca"，其中又只有 "aa"
 * 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *

 */
public class RemoveDuplicates {

    public static String removeDuplicates(String S) {
        if (S == null){
            return S;
        }
        char[] c = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < c.length; i++) {
            if (stack.isEmpty() || stack.peek() != c[i]){
                stack.push(c[i]);
            }else {
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /**
     * leetcode 26
     * 给定一个排序数组，你需要在原地删除重复出现的元素，
     * 使得每个元素只出现一次，返回移除后数组的新长度。

     * 不要使用额外的数组空间，你必须在原地修改输入数组
     * 并在使用 O(1) 额外空间的条件下完成。
     *
     * 示例 1:
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null){
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
