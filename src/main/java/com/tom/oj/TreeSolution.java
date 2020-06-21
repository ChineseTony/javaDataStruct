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

    private void  getAllLeafToRoot(TreeNode root,List<Integer> tmp,
                                   List<List<Integer>> all){
        if(root == null){
            return ;
        }
        boolean isLeaf = root.left == null && root.right == null;
        tmp.add(root.val);
        if (isLeaf){
            all.add(new ArrayList<>(tmp));
        }
        if(root.left != null){
            getAllLeafToRoot(root.left,tmp,all);
        }
        if(root.left != null){
            getAllLeafToRoot(root.left,tmp,all);
        }
        tmp.remove(tmp.size()-1);
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


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(0,tmp);
        }
        return result;

    }

    public int sumRootToLeaf(TreeNode root) {
        return sumLeaf(root,0);
    }

    private int sumLeaf(TreeNode node,int sum){
        if(node == null){
            return 0;
        }
        sum = sum * 2 + node.val;
        //返回叶子节点 元素值
        if(node.left == null && node.right == null){
            return sum;
        }
        return sumLeaf(node.left,sum) + sumLeaf(node.right,sum);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        int currentSum=0;
        List<Integer> list = new ArrayList<>();
        getPath(root,result,list,currentSum,sum);
        return result;
    }

    private void getPath(TreeNode root,List<List<Integer>> result,
                         List<Integer> tmp,
                         int currentSum,int expectSum){
        boolean isLeaf = root.left == null && root.right == null;
        currentSum += root.val;
        tmp.add(root.val);
        if (currentSum == expectSum && isLeaf){
            result.add(new ArrayList<>(tmp));
        }
        if (root.left != null){
            getPath(root.left,result,tmp,currentSum,expectSum);
        }
        if (root.right != null){
            getPath(root.right,result,tmp,currentSum,expectSum);
        }
        //回溯删除最后一个元素
        tmp.remove(tmp.size()-1);
    }

    private int ret = Integer.MIN_VALUE;


    /**
     对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
     1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
     2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径

     1.只有当前节点
     2.当前节点+左子树
     3.当前节点+右子书
     4.当前节点+左右子树
     **/
    public int maxPathSum(TreeNode root) {
        getMax(root);
        return ret;
    }

    private int getMax(TreeNode r) {
        if(r == null) {
            return 0;
        }
        //判断是否加入 左子树 如果大于0就加入该子树
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        ret = Math.max(ret, r.val + left + right);
        return Math.max(left, right) + r.val;
    }



//    将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {

        return sortArray(nums,0,nums.length-1);

    }

    private TreeNode sortArray(int[] nums,int left,int right){
        if(left > right){
            return null;
        }
        int mid = (right - left) / 2 + left;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortArray(nums,left,mid-1);
        root.right = sortArray(nums,mid+1,right);
        return root;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        treePath(root,ret,tmp);
        return ret;
    }

    private void treePath(TreeNode root,List<String> reuslt,
                          List<Integer>  tmp){
        if(root == null){
            return;
        }
        tmp.add(root.val);
        if(root.left == null && root.right == null){
            StringBuilder sb = new StringBuilder();
            int len = tmp.size();
            for (int i = 0; i < len; i++) {
                sb.append(tmp.get(i));
                if(i != len -1){
                    sb.append("->");
                }
            }
            reuslt.add(sb.toString());
        }
        if(root.left != null){
            treePath(root.left,reuslt,tmp);
        }
        if(root.right != null){
            treePath(root.right,reuslt,tmp);
        }
        tmp.remove(tmp.size()-1);
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
