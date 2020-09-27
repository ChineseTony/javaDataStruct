package com.tom.tree;


import java.util.Scanner;

/**
 * @author WangTao
 */
public class MyAvlTree<K extends Comparable,V> {

    private Node root;

    private int size;


    public MyAvlTree(){
        root = null;
        size = 0;
    }

    public void add(K key, V value){
        root = insert(root,key, value);
    }



    private Node  insert(Node node,K key,V value){
        if (node == null){
            node = new Node(key,value);
            size++;
        }else {
            if (key.compareTo(node.key) < 0){
                node.left = insert(node.left,key,value);
                //判断情况是否需要左旋 右旋
                if (getHeigth(node.left) - getHeigth(node.right) == 2){
                    if(key.compareTo(node.left.key) < 0){
                        node=SingleLeftRotation(node);//左单旋
                    }else if(key.compareTo(node.left.key) > 0){
                        node=DoubleLeftRightRotation(node);
                    }
                }

            }else if (key.compareTo(node.key) >0){
                node.right  = insert(node.right,key,value);
                if (getHeigth(node.left) - getHeigth(node.right) == -2){
                    if(key.compareTo(node.right.key) > 0){
                        node=SingleRightRotation(node);//右单旋
                    }else if(key.compareTo(node.right.key) < 0){
                        node=DoubleRightLeftRotation(node);
                    }
                }
            }else {
                node.value = value;
            }
        }
        //更新高度
        node.height = Math.max(getHeigth(node.left),getHeigth(node.right)) +1;
        return node;
    }


    public int getSize(){
        return size;
    }

    public V getRootValue(){
        if (root == null){
            return null;
        }else {
            return root.value;
        }
    }


    //左旋
    private Node SingleLeftRotation(Node node){
        Node tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        node.height = Math.max(getHeigth(node.left),getHeigth(node.right)) +1;
        tmp.height = Math.max(getHeigth(tmp.left),getHeigth(tmp.right)) +1;
        return tmp;
    }


    //右旋
    private Node SingleRightRotation(Node node){
        Node tmpRight = node.right;
        node.right = tmpRight.left;
        tmpRight.left = node;
        node.height = Math.max(getHeigth(node.left),getHeigth(node.right)) +1;
        tmpRight.height = Math.max(getHeigth(tmpRight.left),getHeigth(tmpRight.right)) +1;

        return tmpRight;
    }


    private Node DoubleLeftRightRotation(Node node){
        node.left=SingleRightRotation(node.left);
        return SingleLeftRotation(node);
    }


    private Node DoubleRightLeftRotation(Node node){
        node.right=SingleLeftRotation(node.right);
        return SingleRightRotation(node);
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


    public void inorderTravle(){
        inorderTravel(root);
    }

    private void inorderTravel(Node node){
        if (node != null){
            inorderTravel(node.left);
            System.out.println(node.key+"--->"+node.value);
            inorderTravel(node.right);
        }
    }




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
}
