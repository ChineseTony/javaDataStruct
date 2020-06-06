package com.tom.oj;



import com.tom.util.ListNode;

import java.util.*;

/**
 * @author WangTao
 */
public class TreeSolution {


    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = getLeafNode(root1);
        List<Integer> list2 = getLeafNode(root2);
        int len1 = list1.size();
        int len2 = list2.size();
        if(len1 != len2 ){
            return false;
        }
        for (int i = 0; i < len1; i++) {
            if(list1.get(i) !=  list2.get(i)){
                return false;
            }
        }
        return true;

    }


    private List<Integer> getLeafNode(TreeNode root){
        List<Integer> result = new ArrayList<>();
        getNode(root,result);
        return result;

    }

    private void getNode(TreeNode root,List<Integer> tmp){
        if(root == null){
            return;
        }
        if(root.left != null){
            getNode(root.left,tmp);
        }
        if(root.left == null && root.right == null){
            tmp.add(root.val);
        }
        if(root.right != null){
            getNode(root.right,tmp);
        }
    }




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


    //判断是不是平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        if(Math.abs(getHigh(root.left)-getHigh(root.right)) <= 1){
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;

    }

    private int getHigh(TreeNode root){
        if(root==null) {
            return 0;
        }
        return Math.max(getHigh(root.left),getHigh(root.right))+1;
    }

    public int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        Queue<TreeNode> queue = new ArrayDeque<>(16);
        List<Integer> list = new ArrayList<>(16);
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            list.add(tmp.val);
            if (tmp.left != null){
                queue.offer(tmp.left);
            }
            if (tmp.right != null){
                queue.offer(tmp.right);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        if(tree == null){
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<ListNode> listNodes = new ArrayList<>();
        queue.offer(tree);
        while (!queue.isEmpty()){
            ListNode head = new ListNode(-1);
            ListNode rear = head;
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                ListNode tmp = new ListNode(node.val);
                rear.next = tmp;
                rear = rear.next;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            listNodes.add(head.next);
        }
        int size = listNodes.size();
        ListNode[] result = new ListNode[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNodes.get(i);
        }
        return result;
    }


    public List<List<Integer>> levelOrderBy(TreeNode root) {
        List<List<Integer>> mylist = new ArrayList<>();
        if(root == null){
            return mylist;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
//            之字型打印
            if(mylist.size() % 2 == 1) {
                Collections.reverse(tmp);
            }
            mylist.add(tmp);
        }
        return mylist;
    }


//    public List<List<Integer>> pathSum(TreeNode root, int sum) {
//
//    }

    private List<Integer> getAllLeafToRoot(TreeNode root,List<Integer> tmp){
        if(root == null){
            return tmp;
        }
        if(root.left != null){
            getAllLeafToRoot(root.left,tmp);
        }
        if(root.left == null && root.right == null){
          tmp.add(root.val);
        }
        if(root.left != null){
            getAllLeafToRoot(root.left,tmp);
        }
        return tmp;
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null){
            return false;
        }
        if (postorder.length == 1){
            return true;
        }
        return veryfiySquenceofBST(postorder,0,postorder.length-1);
    }

    private boolean veryfiySquenceofBST(int[] arr,int start,int end){
        if(start >= end){
            return true;
        }
        int i = start;
        //找到比根节点大的数 后序遍历的特性 最后一个值为根节点 结合 二分搜索树的特点
        //左子树全部小于
        while (arr[i] < arr[end]){
            i++;
        }
        for (int j = i; j < end; j++) {
            //右子树全部大于根节点
            if(arr[j] < arr[end]){
                return false;
            }
        }
        //递归调用左右子树
        return veryfiySquenceofBST(arr,start,i-1) && veryfiySquenceofBST(arr,i,end-1);

    }



    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
