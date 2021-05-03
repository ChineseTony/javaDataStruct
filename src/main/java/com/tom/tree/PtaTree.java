package com.tom.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author tom
 * @link https://pintia.cn/problem-sets/15/problems/843
 * todo fix
 */
public class PtaTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        TreeNode root = null;
        int len = m;
        List<Integer> tmp3 = new ArrayList<>(len);
        while (m-- >0){
            int val = scanner.nextInt();
            tmp3.add(val);
            root = insert(root,val);
        }
        PtaTree tree = new PtaTree();
        List<Integer> tmp = tree.inorder(root);
        List<Integer> tmp2 = tree.inorderReverse(root);
        if (tree.isSame(tmp,tmp3) || tree.isSame(tmp2,tmp3)){
            System.out.println("YES");
            List<Integer> list = tree.backOrder(root);
            for (int i = 0; i < len; i++) {
                System.out.print(list.get(i));
                if (i != len-1){
                    System.out.print(" ");
                }
            }
        }else {
            System.out.println("NO");
        }

    }

    private boolean isSame(List<Integer> a,List<Integer> b){
        if (a.size() != b.size()){
            return false;
        }
        int len = a.size();
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))){
                return false;
            }
        }
        return true;
    }
    public List<Integer> backOrder(TreeNode root){
        List<Integer> result = new ArrayList<>();
        back(root,result);
        return result;
    }

    private void back(TreeNode root,List<Integer> list){
        if (root != null){
            back(root.left,list);
            back(root.right,list);
            list.add(root.val);
        }
    }

    public List<Integer> inorder(TreeNode root){
        List<Integer> result = new ArrayList<>();
        inorder(root,result);
        return result;
    }

    private void inorder(TreeNode root,List<Integer> list){
        if (root != null){
            list.add(root.val);
            inorder(root.left,list);
            inorder(root.right,list);
        }
    }

    public List<Integer> inorderReverse(TreeNode root){
        List<Integer> result = new ArrayList<>();
        reverse(root,result);
        return result;
    }

    private void reverse(TreeNode root,List<Integer> list){
        if (root != null){
            list.add(root.val);
            reverse(root.right,list);
            reverse(root.left,list);
        }
    }


    public static TreeNode insert(TreeNode root,int val){
        if (root == null){
            root = new TreeNode(val);
            return root;
        }
        if (val < root.val){
            root.left = insert(root.left,val);
        }else{
            root.right = insert(root.right,val);
        }
        return root;
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
