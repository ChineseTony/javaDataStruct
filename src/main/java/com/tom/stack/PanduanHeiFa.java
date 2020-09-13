package com.tom.stack;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author WangTao
 *
 * 4 10
 * SSSXXSXXSX
 * SSSXXSXXS
 * SSSSSSSSSSXSSXXXXXXXXXXX
 * SSSXXSXXX
 */
public class PanduanHeiFa {


    /**
     * @param s
     * @param maxStackSize
     * @return
     */
    public static boolean checkXuLie(String s,int maxStackSize){
        //s 入栈 x 出栈
        ArrayDeque<Character> stack = new ArrayDeque<> ();
        for (char c : s.toCharArray()){
            if (c == 'S'){
                stack.push(c);
                if (stack.size() > maxStackSize){
                    return false;
                }
            }else if (c == 'X'){
                if (stack.isEmpty()){
                    return false;
                }else {
                    stack.pop();
                }

            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int maxStackSize = scanner.nextInt();
        List<String> list = new ArrayList<>(count);
        if (maxStackSize <= 50){
            while (count-- > 0){
                String tmp = scanner.next();
                list.add(tmp);
            }
            scanner.close();
            for(String t:list){
                if (t.length() > 100){
                    System.out.println("NO");
                    continue;
                }
                if (checkXuLie(t,maxStackSize)){
                    System.out.println("YES");
                }else {
                    System.out.println("NO");
                }
            }
        }


    }
}
