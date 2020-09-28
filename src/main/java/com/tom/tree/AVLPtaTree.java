package com.tom.tree;

import java.util.Scanner;

/**
 * @author WangTao
 * @link https://pintia.cn/problem-sets/434/problems/6103
 */
public class AVLPtaTree {

    public static void main(String[] args) {
        MyAvlTree<Integer,String> tree = new MyAvlTree<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n-- >0){
            int key = scanner.nextInt();
            String value = scanner.next();
            tree.add(key,value);
        }
        tree.inorderTravle();
    }
}
