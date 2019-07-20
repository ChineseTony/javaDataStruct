package com.tom.tree;

import java.util.*;

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

    public Value getMin(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        return getMin(root);
    }



    public Value getMax(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        return getMax(root);
    }

    public Value removeMin(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        Value min = getMin();
        root = removeMin(root);
        return min;
    }

    public Value removeMax(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        Value max = getMax();
        root = removeMax(root);
        return max;
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

    //中序遍历非递归实现
    public void  travelBSF(){
        travelBSF1(root);
    }


    //返回的value暂时还有问题 删除算法已经成功
    public Value delete(Key key){
        Node p = root;
        Node pParent = null;
        Value value = null;
        while (p != null && !p.key.equals(key)){
            pParent = p;
            if (key.compareTo(p.key) > 0){
                p = p.right;
            }else {
                p = p.left;
            }
        }
        if (p == null){
            return null;
        }
        if (p.left != null && p.right != null){
            Node minParent = p;
            Node min = p.right;
            while (min.left != null){
                minParent = min;
                min = min.left;
            }
            value = p.value;
            p.key = min.key;
            p.value = min.value;
            pParent = minParent;
            p = min;
        }
        value = p.value;

        // 删除的是根节点
        if (pParent == null){
            root = null;
        }
        else if(pParent.left == p) {
            pParent.left = p.left;
            p.left = null;
        }else{
            pParent.right = p.right;
            p.right = null;
        }

        lenght--;
        return value;
    }
    
    public Value deleteMin(){
        if (root == null){
            throw new RuntimeException("tree is empty");
        }
        Value value;
        if (root.left == null && root.right == null){
            value = root.value;
            root = null;
        }else if (root.right != null && root.left == null){
            Node temp = root;
            value = temp.value;
            root = root.right;
            //从树中断开
            temp.right = null;
        }else {
            Node pre = root;
            Node cur = root.left;
            while (cur.left != null) {
                pre = cur;
                cur = cur.left;
            }

            if (cur.right != null) {
                value = cur.value;
                pre.left = cur.right;
                cur.right = null;
            } else {
                value = cur.value;
                pre.left = null;
            }
        }
        lenght--;
        return value;
    }

    public void remove(Key key){
        root = remove(root,key);
    }


    private Node remove(Node node,Key key){
            if (node == null){
                return null;
            }
            if (key.compareTo(node.key) < 0){
                node.left = remove(node.left,key);
                return node;
            }else if (key.compareTo(node.key) > 0){
                node.right = remove(node.right,key);
                return node;
            }else {
                //删除 节点值为key 的值
                if (node.left == null){
                    Node rightNode = node.right;
                    node.right = null;
                    lenght--;
                    return rightNode;
                }
                if (node.right == null){
                    Node leftNode = node.left;
                    node.left = null;
                    lenght--;
                    return leftNode;
                }
                Node succsor = getM(root.right);
                succsor.right = removeMin(node.right);
                succsor.left = node.left;
                node.left = node.right = null;
                return succsor;
            }
    }


    private Node getM(Node node){
        if (node.left == null){
            return node;
        }
        return getM(node.left);
    }


    private Value getMin(Node node){
        if (node.left == null){
            return node.value;
        }
        Node tmp = node.left;
        while (tmp.left != null){
            tmp = tmp.left;
        }
        return tmp.value;
    }



    //层次遍历
    private void level(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new ArrayDeque<>(10);
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


    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            lenght --;
            return leftNode;
        }
        node = removeMax(node.right);
        return node;
    }

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            lenght --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }





    private Value getMax(Node node){
        if (node.right == null){
            return node.value;
        }
        Node tmp = node.right;
        while (tmp.right != null){
            tmp = tmp.right;
        }
        return tmp.value;
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
