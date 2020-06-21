package com.tom.oj;


import java.util.ArrayDeque;
import java.util.Deque;

public class StackSequences {

    private StackSequences(){

    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length != popped.length){
            return false;
        }
        int len = pushed.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int j = 0;
        for (int i = 0; i < len; i++) {
            stack.push(pushed[i]);
            while (j < len && !stack.isEmpty() && stack.peek() == popped[j]){
                stack.poll();
                j++;
            }
        }
        return j == len;
    }

    public static boolean backspaceCompare(String S, String T) {
        return getLocalString(S).equals(getLocalString(T));
    }

    private static String getLocalString(String s){
        char[] chars = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : chars) {
            if(c == '#' ){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static int minAddToMakeValid(String S) {
        if(S == null || S.length() == 0){
            return 0;
        }
        Deque<Character> stack = new ArrayDeque<>();
        int len = S.length();
        for (int i = 0; i < len; i++) {
            if(!stack.isEmpty() && stack.peek() == '(' && S.charAt(i) == ')'){
                stack.pop();
            }else {
                stack.push(S.charAt(i));
            }

        }
        return stack.size();

    }

    public String minRemoveToMakeValid(String s) {

        return s;

    }

    public static void main(String[] args) {
//        int[] arr1 = new int[]{2,1,0};
//        int[] arr2 = new int[]{1,2,0};
//        System.out.println(validateStackSequences(arr1,arr2));
//        System.out.println(backspaceCompare("a##c","#a#c"));
        System.out.println(minAddToMakeValid("()))(("));
    }


}
