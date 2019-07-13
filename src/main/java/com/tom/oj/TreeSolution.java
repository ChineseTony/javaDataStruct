package com.tom.oj;

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


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
