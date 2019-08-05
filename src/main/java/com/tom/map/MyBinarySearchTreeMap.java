package com.tom.map;

import com.tom.tree.BinarySearchTree;

/**
 * @author WangTao
 */
public class MyBinarySearchTreeMap<K extends Comparable,V>
        implements MyMap<K,V> {

    private BinarySearchTree<K,V> binarySearchTree;

    public MyBinarySearchTreeMap(){
        binarySearchTree = new BinarySearchTree<>();
    }

    @Override
    public void add(K key, V value) {
        binarySearchTree.insertBSF(key,value);
    }

    @Override
    public V remove(K key) {
        return binarySearchTree.delete(key);
    }

    @Override
    public boolean contains(K key) {
        return binarySearchTree.search(key) != null;
    }

    @Override
    public int getSize() {
        return binarySearchTree.getLenght();
    }

    @Override
    public boolean isEmpty() {
        return binarySearchTree.isEmpty();
    }

    @Override
    public void set(K key, V value) {
        if (contains(key)){
            binarySearchTree.insertBSF(key,value);
        }
    }

    @Override
    public V get(K key) {
        return binarySearchTree.search(key);
    }

    public static void main(String[] args) {
        MyMap<Integer,String> map
                = new MyBinarySearchTreeMap<>();
        map.add(1,"MyLinkedListLeetcode");
        map.add(1,"B");
        System.out.println(map.get(1));
        System.out.println(map.getSize());
    }
}
