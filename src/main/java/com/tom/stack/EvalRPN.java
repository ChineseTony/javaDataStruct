package com.tom.stack;



import java.util.*;


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


    public static boolean checkPerfectNumber(int num) {
        if(num == 1) {
            return false;
        }
        int sum = 1;
        for(int i = 2, max = num/2; i < max; i++){
            if(num % i == 0){
                sum += i + num/i;
                max = num/i;
            }
        }
        return sum == num;
    }


    public static String reformat(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        Deque<Character> charDeque = new ArrayDeque<>();
        Deque<Character> numberDeque = new ArrayDeque<>();
        for (char c:s.toCharArray()){
            if (c >= '0' && c <= '9'){
                numberDeque.offer(c);
            }else {
                charDeque.offer(c);
            }
        }
        int charLength = charDeque.size();
        int numberLength = numberDeque.size();
        if (Math.abs(charLength-numberLength) > 1){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (charLength > numberLength){
            int tmp = numberLength;
            while (tmp > 0){
                sb.append(charDeque.poll()).append(numberDeque.poll());
                tmp --;
            }
            sb.append(charDeque.poll());
        }else if (numberLength > charLength){
            int tmp = charLength;
            while (tmp > 0){
                sb.append(numberDeque.poll()).append(charDeque.poll());
                tmp --;
            }
            sb.append(numberDeque.poll());
        }else{
            int tmp = charLength;
            while (tmp > 0){
                sb.append(numberDeque.poll()).append(charDeque.poll());
                tmp --;
            }
        }

        return sb.toString();
    }


    public static String reformat2(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        int charLen = 0;
        int numberLen = 0;
        for (char c:s.toCharArray()){
            if (c >= '0' && c <= '9'){
                numberLen++;
            }else {
                charLen++;
            }
        }
        if (Math.abs(numberLen-charLen) > 1){
            return "";
        }
        if (numberLen > charLen){
            numberLen = 0;
            charLen = 1;
        }else {
            charLen =0;
            numberLen = 1;
        }
        char[] chars = new char[s.length()];
        for (char c:s.toCharArray()){
            if (c >= '0' && c <= '9'){
                chars[numberLen] = c;
                numberLen += 2;
            }else {
                chars[charLen] = c;
                charLen += 2;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] tmp = new String[]{
                "10"
        };
        System.out.println(evalRPN(tmp));

        System.out.println(checkPerfectNumber(100000000));

//        "covid2019"
        System.out.println(reformat("a0b1c2"));

    }
}
