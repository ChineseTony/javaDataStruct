package com.tom.tree;


import java.util.ArrayList;
import java.util.List;
import	java.util.Random;


/**
 * @author WangTao
 * 平衡因子
 */
@SuppressWarnings("unchecked")
public class AVLTree<K extends Comparable,V> {

    private Node root;

    private int size;


    public AVLTree(){
        root = null;
        size = 0;
    }



    public int getSize() {
        return size;
    }


    public void add(K key, V value){
        root = add(root,key, value);
    }



    private Node add(Node node,K key,V value){
        if (node == null){
            node = new Node(key, value);
            size++;
        }
        if (key.compareTo(node.key) < 0){
            node.left = add(node.left,key, value);
        }else if (key.compareTo(node.key) > 0){
            node.right = add(node.right,key, value);

        }else {
            node.value = value;
        }
        //更新height
        node.height = 1 + Math.max(getHeigth(node.left),getHeigth(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced"+balanceFactor);
        }
        return node;
    }


    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeigth(node.left) - getHeigth(node.right);
    }


    private int getHeigth(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }


    //判断树是不是二叉树
    public boolean isBST(){
        List<K> keys= new ArrayList<>(size);
        inorder(root, keys);
        int length = keys.size();
        for (int i = 1; i < length; i++) {
            if (keys.get(i-1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }


    public boolean isAVL() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node == null){
            return true;
        }
        int balancedFactory = getBalanceFactor(node);
        if (Math.abs(balancedFactory) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }


    private void inorder(Node node,List<K> keys){
        if (node != null){
            inorder(node.left,keys);
            keys.add(node.key);
            inorder(node.right,keys);
        }
    }

/*
    private void inderAVL(){
        inderAVL(root);
    }

    private void inderAVL(Node node){
       if (node != null){
           Stack<Node> stack = new Stack<>();
           Node p = node;
           while (!stack.isEmpty() || p != null){
               while (p != null) {
                   stack.push(p);
                   p = p.left;
               }
               if (!stack.isEmpty()){
                   p = stack.pop();
                   System.out.println(p.key+"----->"+p.value);
                   p = p.right;
               }
           }
       }
    }*/

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left;
        public Node right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            //叶子节点高度值为1
            this.height = 1;
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer,String> tree = new AVLTree<>();
        Random r = new Random();
        final int times = 10;
        for (int i = 0; i < times; i++) {
            int num = r.nextInt(100);
            tree.add(num,"a"+num);
        }
        System.out.println(tree.isBST());
        System.out.println(tree.isAVL());
    }
}
