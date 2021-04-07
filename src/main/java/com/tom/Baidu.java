package com.tom;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Baidu {


    /**
     *   4 5 1 2 3  入栈
     *  判断 5,4,3,2,1 是不是出栈顺序
     * @param list
     * @param sub
     * @return
     *
     * 4 5 入栈 出栈 5 4
     * 1 2 3 入栈  出栈 3 2 1
     */
    public static boolean isValid(List<Integer> list,
                           List<Integer> sub){
        if (list.size() != sub.size()){
            return false;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        for (int i:list){
            stack.push(i);
            while (!stack.isEmpty() &&
                            stack.peek().equals(sub.get(index))){
                        stack.pop();
                        index++;
           }
        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        List<Integer> list
                =new ArrayList<>(Arrays.asList(4,5,1,2,3));
        List<Integer> sub
                =new ArrayList<>(Arrays.asList(5,4,3,2,1));

        System.out.println(isValid(list,sub));
    }
}
