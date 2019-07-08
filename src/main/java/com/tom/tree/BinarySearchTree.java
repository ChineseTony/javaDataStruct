package com.tom.tree;

import java.util.Stack;

/**
 *
 * @author WangTao
 * 二分搜索树
 * 中序遍历为有序序列
 */
public class BinarySearchTree<Key extends Comparable,Value> {


    private Node root;

    private int lenght;

    public BinarySearchTree(){
        root = null;
        lenght = 0;
    }

    public int getLenght() {
        return lenght;
    }

    public boolean isEmpty(){
        return  lenght == 0;
    }


    public Value search(Key key){
        return search(root,key);
    }


    public void insertBSF(Key  key,Value value){
        root = insertBSF(root, key,value);
    }

    @SuppressWarnings("unchecked")
    private Node insertBSF(Node node,Key  key,Value value){
        if(node == null){
            lenght++;
            node = new Node(key,value);
            return node;
        }
        if((node.key.compareTo(key)) > 0){
            node.left  =   insertBSF(node.left,key,value);
        }else if ((node.key.compareTo(key)) < 0){
            node.right = insertBSF(node.right,key,value);
        } else {
            node.value = value;
        }
        return node;
    }

    //中序遍历递归调用
    public void order(){
        order(root);
    }

    //中序遍历非递归实现
    public void  travelBSF(){
        travelBSF1(root);
    }

    private void travelBSF1(Node node){
        if (node != null){
            Stack<Node> stack = new Stack<>();
            Node p = node;
            while (!stack.isEmpty() || p != null){
                while (p  != null){
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
    }

    private Value search(Node node,Key key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            return search(node.left,key);
        }else if (key.compareTo(node.key) > 0){
            return search(node.right,key);
        }else {
            return node.value;
        }
    }




    private void order(Node node){
        if (node != null){
            order(node.left);
            System.out.println(node.key+"----->"+node.value);
            order(node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer,String> tree = new BinarySearchTree<>();
        for (int i = 0; i < 5; i++) {
                tree.insertBSF(i,"a"+i);
        }
        System.out.println("length:"+tree.getLenght());
        tree.order();
        System.out.println(tree.search(1));
        tree.travelBSF();
    }

    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;

        public Node(Key key,Value value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        public Node(Key key){
            this.key = key;
            left = null;
            right = null;
        }

    }
}
