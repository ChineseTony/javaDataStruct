package com.tom.oj;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author wangtao
 * @link https://pintia.cn/problem-sets/434/problems/5405
 */
public class SimpleCaculate {


    public static String caculate(String s){
        return s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String t = scanner.nextLine().replace("=","");
        Deque<String> stack = new ArrayDeque<>();
        Deque<String> queue = new ArrayDeque<>();
        int index = 0;
        int len = t.length();
        while (index < len) {
            StringBuilder sb = new StringBuilder();
            if (Character.isDigit(t.charAt(index))){
                while (index < len
                && Character.isDigit(t.charAt(index))){
                    sb.append(t.charAt(index));
                    index++;
                }
                queue.offer(sb.toString());
            }else {
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty()) {
                        queue.offer(stack.pop());
                    }
                }
                stack.push(String.valueOf(t.charAt(index)));
                index++;
            }
        }
        while (!stack.isEmpty()){
            queue.offer(stack.pop());
        }

        Deque<String> stack2 = new ArrayDeque<>();
        int reuslt = 0;
        while (!queue.isEmpty()){
            String tmp = queue.poll();
            if ("+".equals(tmp)){
                reuslt = Integer.parseInt(stack2.pop())
                        + Integer.parseInt(stack2.pop());
                stack2.push(String.valueOf(reuslt));
            }else if ("-".equals(tmp)){
                reuslt =  - (Integer.parseInt(stack2.pop())
                        - Integer.parseInt(stack2.pop()));
                stack2.push(String.valueOf(reuslt));
            }else if ("*".equals(tmp)){
                reuslt =Integer.parseInt(stack2.pop())
                        * Integer.parseInt(stack2.pop());
                stack2.push(String.valueOf(reuslt));
            }else if ("/".equals(tmp)){
                String a = stack2.pop();
                String b = stack2.pop();
                if ("0".equals(a)) {
                    System.out.println("ERROR");
                    break;
                }else {
                    reuslt = Integer.parseInt(b)
                            / Integer.parseInt(a);
                    stack2.push(String.valueOf(reuslt));
                }
            }else {
                stack2.push(tmp);
            }
        }
        if (!stack2.isEmpty()){
            System.out.println(stack2.peek());
        }
        scanner.close();
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = 0, b = 0;
//        boolean flag = true;
//        char ch = 0;
//        String s;
//        s = sc.nextLine();
//        for(int i = 0;i < s.length();i ++)
//        {
//            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
//            {
//                b = s.charAt(i) - '0';
//                while(s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9')
//                {
//                    i++;
//                    b *= 10;
//                    b += s.charAt(i) - '0';
//                }
//                if(ch == 0){
//                    a = b;
//                }else{
//                    if(ch == '+')a += b;
//                else if(ch == '-')a -= b;
//                else if(ch == '*')a *= b;
//                else if(ch == '/')
//                    {
//                        if(b == 0){flag = false; break;}
//                        else a /= b;
//                    }
//                else {flag = false;break;}
//                }
//            }
//        else
//            {
//                if(s.charAt(i) == '=')break;
//                ch = s.charAt(i);
//            }
//        }
//        if(flag)System.out.print(a);
//        else System.out.print("ERROR");
//    }
}
