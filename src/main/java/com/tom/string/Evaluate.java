package com.tom.string;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/evaluate-the-bracket-pairs-of-a-string/
 */
public class Evaluate {

    public static String evaluate(String s, List<List<String>> knowledge) {
        if (s == null || s.length() <= 0){
            return s;
        }
        int len = knowledge.size();
        Map<String,String> mapping = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            mapping.put(knowledge.get(i).get(0),
                    knowledge.get(i).get(1));
        }
        Deque<Character> stack = new ArrayDeque<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c != ')') {
                stack.push(c);
            }else {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }
                stack.pop();
                String key = sb.reverse().toString();
                if (!mapping.containsKey(key)){
                    stack.push('?');
                }else {
                   String t = mapping.get(key);
                    for (int j = 0; j < t.length(); j++) {
                        stack.push(t.charAt(j));
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

    }



    public String evaluate2(String s, List<List<String>> knowledge) {
        Map<String, String> mapper = new HashMap<>(knowledge.size());
        for (List<String> item : knowledge){
            mapper.put(item.get(0), item.get(1));
        }
        // builder 用来对括号内的字符进行保存
        StringBuilder builder = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        boolean flag = false;
        for (char c : s.toCharArray()) {
            if (c == ')') {
                flag = false;
                String key = builder.toString();
                builder = new StringBuilder();
                ans.append(mapper.getOrDefault(key, "?"));
            } else if (c == '(') {
                flag = true;
            } else if (flag){
                builder.append(c);
            }else{
                ans.append(c);
            }
        }
        return ans.toString();
    }


    public List<Integer> lexicalOrder(int n) {
        List<String> ans = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            ans.add(String.valueOf(i));
        }
        Collections.sort(ans);
        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            res.add(Integer.parseInt(ans.get(i)));
        }
        return res;

    }


    public static void main(String[] args) {
        String s = "(name) is (age) years old";
        List<List<String>> knowledge = new ArrayList<>();
        knowledge.add(new ArrayList<>(Arrays.asList("name","bob")));
        knowledge.add(new ArrayList<>(Arrays.asList("age","two")));
        System.out.println(evaluate(s,
                knowledge));


    }
}
