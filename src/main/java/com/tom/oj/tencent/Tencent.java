package com.tom.oj.tencent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Tencent {

    public TreeNode solve (TreeNode root) {
        if (root == null){
            return root;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        int i = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            TreeNode tmp = queue.poll();
            if (tmp.left != null){
                queue.offer(tmp.left);
            }
            if (tmp.right != null){
                queue.offer(tmp.right);
            }
            if (size == i){
                list.add(tmp);
            }
            i = i *2;
        }
        int times = 1;
        while (2 * (times - 1) < list.size()){
            int tmpCount = 2 * (times - 1);
//            进行
            for (i = tmpCount ; i < list.size() && 2 * i + 1 <
                    list.size(); i++) {
                list.get(i).left = list.get(2 * i + 1);
                list.get(i).right = list.get(2 * i + 2);
                i = 2 * i +1;
            }
            times++;
        }
        System.out.println("###");
        for(TreeNode tmp:list){
            System.out.println(tmp.val);
        }
        System.out.println("###");
        return list.get(0);
    }

    private void remove(TreeNode  root){
        if (root != null){
            remove(root.left);
            remove(root.right);
            root = null;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode t =new Tencent().solve(root);
//        System.out.println(t.val);
//        System.out.println(t.left.val);
//        System.out.println(t.left.val);
//        System.out.println(t.right.val);

    }


      public static class TreeNode {
        int val = 0;
        TreeNode left = null;
       TreeNode right = null;

       public TreeNode(int a){
           val = a;
       }
      }

}
