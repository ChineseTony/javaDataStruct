package com.tom.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        assertEquals(tree.getMin(),"a1");
        assertEquals(tree.removeMin(),"a1");
        assertEquals(tree.getLenght(),6);
    }

    @Test
    public void delete(){
        tree.inderBSF();
        System.out.println("======");
        assertEquals(tree.delete(80),"a0");
        assertEquals(tree.getLenght(),6);
        tree.inderBSF();
    }

    @Test
    public void getCommonParent(){
        assertEquals(tree.getCommonParent(97,99),"a3");
    }

    @Test
    public void convertToList(){
        //输出值 a0--->a1--->a5--->a6--->a3--->a4--->a2--->
        tree.travle();

    }

    @Test
    public void findPath(){
        BinarySearchTree<Integer,Integer> tree1 = new BinarySearchTree<>();
        tree1.insertBSF(5,5);
        tree1.insertBSF(10,10);
        tree1.insertBSF(4,4);

        List<List<Integer>> list =tree1.findPath(9);
        for(List<Integer> tmp:list){
            tmp.forEach(System.out::println);
        }

    }

    @Test
    public void test(){
        System.out.println(1.0/12);
    }
}
