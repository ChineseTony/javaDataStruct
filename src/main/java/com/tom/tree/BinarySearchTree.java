package com.tom.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author WangTao
 * 二分搜索树
 * 中序遍历为有序序列
 */
@SuppressWarnings("unchecked")
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

    //后序遍历递归实现
    public void postOrder(){
        postOrder(root);
    }

 /*   public void reverse(){
        reverse(root);
    }*/

    //前序遍历
    public void inder(){
        inder(root);
    }

    //前序非递归实现
    public void inderBSF(){
        inderBSF(root);
    }

    //层次遍历
    public void level(){
        level(root);
    }

    //层次遍历
    private void level(Node node){
        Queue<Node> queue = new ArrayDeque<>(10);
        if (node == null){
            return;
        }
        queue.offer(node);
        while (!queue.isEmpty()){
            Node node1 = queue.poll();
            System.out.format("key:{%s}------->value:{%s}\n",node1.key,node1.value);
            if (node1.left != null){
                queue.offer(node1.left);
            }
            if (node1.right != null){
                queue.offer(node1.right);
            }
        }
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


    private void inder(Node node){
        if (node != null){
            System.out.println(node.key+"----->"+node.value);
            order(node.left);
            order(node.right);
        }
    }

    private void inderBSF(Node node){
        if (node != null){
            Stack<Node> stack = new Stack<>();
            Node p = node;
            stack.push(p);
            while (!stack.isEmpty()){
                p = stack.pop();
                System.out.println(p.key+"----->"+p.value);
                if (p.right != null){
                    stack.push(p.right);
                }
                if (p.left != null){
                    stack.push(p.left);
                }
            }

        }
    }

    private void postOrder(Node node){
        if (node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.format("key:{%s}------->value:{%s}\n",node.key,node.value);
        }

    }

/*    private void reverse(Node node){
        if (node == null ){
            return;
        }
        if (node.left == null && node.right == null){
            return;
        }
        Node temp = node.right;
        node.right = temp.left;
        node.left = temp;
        reverse(node.left);
        reverse(node.right);
    }*/


    private void order(Node node){
        if (node != null){
            order(node.left);
            System.out.println(node.key+"----->"+node.value);
            order(node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer,String> tree = new BinarySearchTree<>();
        Random random = new Random();
        final int nums = 1;
        for (int i = 0; i < nums; i++) {
                tree.insertBSF(random.nextInt(100),"a"+i);
        }
        System.out.println("length:"+tree.getLenght());
        tree.order();
        System.out.println(tree.search(1));
        tree.travelBSF();
        tree.level();
        tree.postOrder();
        tree.inderBSF();

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
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
