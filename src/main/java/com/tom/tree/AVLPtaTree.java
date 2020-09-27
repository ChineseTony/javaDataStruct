package com.tom.tree;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author WangTao
 * @link https://pintia.cn/problem-sets/434/problems/6103
 */
public class AVLPtaTree {

    public static void main(String[] args) {
        MyAvlTree<Integer,Integer> tree = new MyAvlTree<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n-- >0){
            int tmp = scanner.nextInt();
            tree.add(tmp,tmp);
        }
        System.out.println(tree.getRootValue());
        System.out.println(tree.getSize());
        tree.inorderTravle();
    }
}
