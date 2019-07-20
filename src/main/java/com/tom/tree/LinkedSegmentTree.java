package com.tom.tree;

/**
 * @author WangTao
 */
public class LinkedSegmentTree<T> {

    //根节点
    private Node root;


    private class Node{
        int l;
        int ri;
        Node left;
        Node right;
    }
}
