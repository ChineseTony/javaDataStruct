package com.tom.stack;

import java.util.*;

/**
 * @author WangTao
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class Valid {

    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] ==  '(' || chars[i] == '[' || chars[i] == '{'){
                stack.push(chars[i]);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                char top = stack.pop();
                if (top == '[' && chars[i] != ']'){
                    return false;
                }
                if (top == '{' && chars[i] != '}'){
                    return false;
                }
                if (top == '(' && chars[i] != ')'){
                    return false;
                }
            }
        }
        return stack.isEmpty() ;
    }

    public static boolean isValid2(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>(16);
        map.put('}','{');
        map.put(')','(');
        map.put(']','[');

        for (int i = 0; i < chars.length; i++) {
            Character c = chars[i];
            if (!map.containsKey(c)) {
                //左括号 入栈
                stack.push(c);
            } else {
                //栈为空 或者 括号不匹配
                if (stack.isEmpty() || stack.pop().charValue() != map.get(c)){
                    return false;
                }
            }
        }
        return stack.isEmpty() ;
    }


    /**
     * 834. 移除多余字符
     * 中文
     * English
     * 给定一个字符串s由小写字符组成，移除多余的字符使得每个字符只出现一次。
     * 你必须保证结果是字典序是最小的合法字符串。
     *
     * 样例
     * 样例1
     *输入: s = "cbacdcbc"
     * 输出: "acdb"
     *
     * 字典序 暂时还没有理解
     *
     * @param s
     * @return
     */

    public static String removeDuplicateLetters(String s) {
        // write your code here
        if(s==null || s.length()<=0) {
            return "";
        }else{
            int[] count=new int[26];
            for(int i=0;i<s.length();i++) {
                count[s.charAt(i) - 'a']++;
            }
            int pos=0;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)<s.charAt(pos)) {
                    pos = i;
                }
                count[s.charAt(i)-'a']--;
                if(count[s.charAt(i)-'a']==0) {
                    break;
                }
            }
//            System.out.println(s.charAt(pos)+"     "+s.substring(pos+1).replace(""+s.charAt(pos),""));
            String res = s.charAt(pos)+
                    removeDuplicateLetters(
                            s.substring(pos+1).replace(""+s.charAt(pos),
                                    ""));
            return res;
        }
    }

    public static void main(String[] args) {

//        String s = "{}[]";
//        System.out.println(isValid(s));
//        System.out.println(isValid2(s));
        //acdb   abcd
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }
}
