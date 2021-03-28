package com.tom.tree;



import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/binary-search-tree-iterator/
 */
public class BSTIterator2 {
    private TreeNode cur;
    private Deque<TreeNode> stack;

    public BSTIterator2(TreeNode root) {
        cur = root;
        stack = new ArrayDeque<>();
    }

    public int next() {
        while (cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int retVal = cur.val;
        cur = cur.right;
        return  retVal;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
