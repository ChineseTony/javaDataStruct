package com.tom.stack;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;


/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class EvalRPN {

    private EvalRPN(){

    }

    private static    Set<String> strings ;
    static {
        strings = new HashSet<>(4);
        strings.add("-");
        strings.add("+");
        strings.add("/");
        strings.add("*");
    }

    private static int cal(int b,int a,char c){
        if (c == '+') {
            return a + b;
        } else if (c == '-') {
            return a - b;
        } else if (c == '*') {
            return a * b;
        }else if (c == '/'){
            return a / b;
        }else {
            return 0;
        }
    }

    private static boolean isChar(String s){
      return strings.contains(s);
    }


    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0){
            return -1;
        }
        int len = tokens.length;
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            //弹出栈顶2个元素进行计算
//            适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
            if (isChar(tokens[i])){
                if (stack.size() >= 2){
                    result = cal(stack.pollLast(),stack.pollLast(),tokens[i].charAt(0));
                    stack.offer(result);
                }

            }else {
                stack.offer(Integer.parseInt(tokens[i]));
            }
        }
        if (!stack.isEmpty()){
            result =stack.pollLast();
        }
        return result;

    }

    public static void main(String[] args) {
        String[] tmp = new String[]{
                "10"
        };
        System.out.println(evalRPN(tmp));

    }
}
