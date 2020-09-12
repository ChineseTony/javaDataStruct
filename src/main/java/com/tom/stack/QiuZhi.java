package com.tom.stack;
import java.util.ArrayDeque;
import java.util.Queue;
import	java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WangTao
 *
 * todo has some bugs to fix
 */
public class QiuZhi {


    public static boolean isNumericzidai(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static double cal(double a,double b,String s){
        if ("+".equals(s)){
            return a + b;
        }else if("*".equals(s)){
            return a * b;
        }else if ("-".equals(s)){
            return a - b;
        }else if ("/".equals(s)){
            return a / b;
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String tmp = s.nextLine();
        String[] arr = tmp.split("\\s");
        int len = arr.length;
        ArrayDeque<Double> numStack = new ArrayDeque<>(len);
        int num =0;
        int fuhao = 0;
        boolean flag = false;
        for (int i = len -1 ; i >= 0 ; i--) {
            String t = arr[i];
            try {
                if (isNumericzidai(t)){
                    numStack.push(Double.valueOf(t));
                    num++;
                }else {
                    double a = numStack.pop();
                    double b = numStack.pop();
                    numStack.push(cal(a,b,t));
                    fuhao++;
                }
            }catch (Exception e){
                flag = true;
            }

        }
        if (num -1 != fuhao || numStack.isEmpty() || flag){
            System.out.println("ERROR");
        }else {
            System.out.printf("%.1f" , numStack.pop());
        }
    }
}
