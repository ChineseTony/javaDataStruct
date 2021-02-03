package com.tom.string;


/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/longest-repeating-character-replacement/
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class CharacterReplacement {


    public static int characterReplacement(String s, int k) {
        if(s == null || s.length() <= 0 || k <= 0){
            return 0;
        }
        int len = s.length();

        return 0;
    }


    public static int maxCommonCharacter(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        int len = s.length();
        int max = 0;
        int index = 0;
        int tmp = 1;
        while(index < len-1){
            if(s.charAt(index) != s.charAt(index + 1)){
                max = Math.max(tmp,max);
                tmp = 1;
            }else {
                tmp++;
            }
            index++;
        }
        max = Math.max(tmp,max);
        return max;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
//        System.out.println(characterReplacement(s, k));
        System.out.println(maxCommonCharacter("AABABBA"));
        System.out.println(maxCommonCharacter("ABBCCCD"));

    }
}
