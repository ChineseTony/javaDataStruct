package com.tom.digui;


import java.util.Scanner;

/**
 * @author tom
 */
public class HanLuoTai {

    public static void digui(int n,char a,char b,char c){
        if (n == 1){
            System.out.println(a + "----->" + c);
        }else {
            digui(n - 1, a, c, b);
            System.out.println(a + "----->" + c);
            digui(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        digui(n,'a','b','c');
    }
}
