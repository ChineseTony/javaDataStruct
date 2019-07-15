package com.tom.oj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author WangTao
 */
public class TreeSolution {

    public int minDepth(TreeNode root) {

        int depth = 0;
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            depth = 1;
        }else if(root.left != null && root.right != null){
            depth = 1+Math.min(minDepth(root.left) , minDepth(root.right)) ;
        }else {
            if (root.left != null){
                depth = 1 + minDepth(root.left);
            }
            if (root.right != null){
                depth = 1 + minDepth(root.right);
            }
        }
        return depth;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode node = root;
        Queue<TreeNode> queue = new ArrayDeque<>(10);
        queue.offer(node);
        List<Double> list = new ArrayList<>();
        while (!queue.isEmpty()){
            double sum = 0.0;
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node1 = queue.poll();
                if (node1.left != null){
                    queue.offer(node1.left);
                }
                if (node1.right != null){
                    queue.offer(node1.right);
                }
                sum += node1.val;
            }
            list.add(sum /size);

        }
        return list;
    }

    //给定一个二叉树，检查它是否是镜像对称的。leetcode 101
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null){
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        return isSymmetric2(root,root);
    }

    public boolean isSymmetric2(TreeNode t1,TreeNode t2) {
        if (t1 == null && t2 == null){
            return true;
        }
        if (t1 == null || t2 == null){
            return false;
        }
        return t1.val == t2.val && isSymmetric2(t1.left,t2.right)
                && isSymmetric2(t2.right,t1.left);
    }



    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
