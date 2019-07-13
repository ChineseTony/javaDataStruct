package com.tom.tree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */


public class BinarySearchTreeTest {
    private BinarySearchTree<Integer,String> tree;

    @Before
    public void initData(){
        tree = new BinarySearchTree<>();
        tree.insertBSF(80,"a0");
        tree.insertBSF(90,"a1");
        tree.insertBSF(100,"a2");
        tree.insertBSF(98,"a3");
        tree.insertBSF(99,"a4");
        tree.insertBSF(96,"a5");
        tree.insertBSF(97,"a6");
    }

    @Test
    public void testLength(){
        assertEquals(tree.getLenght(),5);
    }

    @Test
    public void testSearch(){
        assertEquals(tree.search(90),"a1");
    }

    //中序遍历二分搜索树
    @Test
    public void testOrder(){
        tree.order();
        tree.travelBSF();
        //
        tree.postOrder();
    }

    //前序遍历二分搜索树
    @Test
    public void inderBSF(){
        tree.inder();
        tree.inderBSF();
    }

    //层次遍历二分搜索树
    @Test
    public void testLevel(){
        tree.level();
    }

    @Test
    public void getMin(){
        assertEquals(tree.getMin(),"a3");
        assertEquals(tree.removeMin(),"a3");
        assertEquals(tree.getLenght(),4);
    }

    @Test
    public void delete(){
        tree.inderBSF();
        System.out.println("======");
        assertEquals(tree.remove(90),"a1");
        assertEquals(tree.getLenght(),6);
        tree.inderBSF();
    }
}
