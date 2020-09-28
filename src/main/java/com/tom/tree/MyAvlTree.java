package com.tom.tree;



/**
 * @author WangTao
 *
 * @linke https://blog.csdn.net/hm108106/article/details/72736075
 */
public class MyAvlTree<Key extends Comparable,Value> {

    private Node root;

    private int size;


    public MyAvlTree(){
        root = null;
        size = 0;
    }

    public void add(Key key, Value value){
        root = insert(root,key, value);
    }



    private Node  insert(Node node,Key key,Value value){
        if (node == null){
            node = new Node(key,value);
            size++;
        }else {
            if (key.compareTo(node.key) < 0){
                node.left = insert(node.left,key,value);
                //判断情况是否需要左旋 右旋
                if (getHeigth(node.left) - getHeigth(node.right) == 2){
                    if(key.compareTo(node.left.key) < 0){
                        node=singleLeftRotation(node);//左单旋
                    }else if(key.compareTo(node.left.key) > 0){
                        node=doubleLeftRightRotation(node);
                    }
                }

            }else if (key.compareTo(node.key) >0){
                node.right  = insert(node.right,key,value);
                if (getHeigth(node.left) - getHeigth(node.right) == -2){
                    if(key.compareTo(node.right.key) > 0){
                        node=singleRightRotation(node);//右单旋
                    }else if(key.compareTo(node.right.key) < 0){
                        node=doubleRightLeftRotation(node);
                    }
                }
            }else {
                node.value = value;
            }
        }
        //更新平衡因子
        node.height = Math.max(getHeigth(node.left),getHeigth(node.right)) +1;
        return node;
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return  root == null;
    }


    public Value getRootValue(){
        if (root == null){
            return null;
        }else {
            return root.value;
        }
    }


    //左旋
    private Node singleLeftRotation(Node node){
        Node tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        node.height = Math.max(getHeigth(node.left),getHeigth(node.right)) +1;
        tmp.height = Math.max(getHeigth(tmp.left),getHeigth(tmp.right)) +1;
        return tmp;
    }


    //右旋
    private Node singleRightRotation(Node node){
        Node tmpRight = node.right;
        node.right = tmpRight.left;
        tmpRight.left = node;
        node.height = Math.max(getHeigth(node.left),getHeigth(node.right)) +1;
        tmpRight.height = Math.max(getHeigth(tmpRight.left),getHeigth(tmpRight.right)) +1;

        return tmpRight;
    }


    private Node doubleLeftRightRotation(Node node){
        node.left=singleRightRotation(node.left);
        return singleLeftRotation(node);
    }


    private Node doubleRightLeftRotation(Node node){
        node.right=singleLeftRotation(node.right);
        return singleRightRotation(node);
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


//    todo delete node by key
//
//    public void delete(Key key){
//        root  =  delete(root,key);
//    }
//
//
//    private Node delete(Node node,Key key){
//
//        if (key.compareTo(node.key) < 0){
//            node.left =  delete(node.left,key);
//            return node;
//        }else if (key.compareTo(node.key) > 0) {
//            node.right =  delete(node.right,key);
//            return node;
//        }else {
////            找到 值为key的节点 删除改节点
//            //删除叶子节点
//            if (node.left == null && node.right == null){
//               node = null;
//               return node;
//            }
//
//            if (node.left == null){
//
//            }
//            if (node.right == null){
//
//            }
//
//            return null;
//        }
//
//    }




    private class Node {
        public Key key;
        public Value value;
        public int height;
        public Node left;
        public Node right;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            //叶子节点高度值为1
            this.height = 1;
        }
    }
}
