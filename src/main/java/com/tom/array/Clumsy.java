package com.tom.array;


import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wangtao
 */
public class Clumsy {

    public static int clumsy(int n) {
        if (n <= 1){
            return n;
        }
        int tmp = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(n);
        n--;
        int count = 0;
        while (n > 0){
            if (count % 4 == 0){
                stack.push(stack.pop() * n);
            }else if (count % 4 == 1){
                stack.push(stack.pop() / n);
            }else if (count % 4 == 2){
                stack.push(n);
            }else {
                stack.push(-n);
            }
            n--;
            count++;
        }
        while (!stack.isEmpty()){
            tmp += stack.pop();
        }
        return tmp;
    }


    public static void main(String[] args) {
//        clumsy(9);
//        System.out.println(clumsy(9));
        System.out.println(clumsy(4));
//        System.out.println(clumsy(10));

    }
}
