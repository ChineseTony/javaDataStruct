package com.tom.tree;

import java.util.*;

public class ValidateBinaryTreeNodes {


    /**
     * todo fix me
     * @link https://leetcode-cn.com/problems/validate-binary-tree-nodes/
     * @param n
     * @param leftChild
     * @param rightChild
     * @return
     */
    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> vised = new HashSet<>();
        int count = 0,t = 0;
        queue.offer(0);
        while (!queue.isEmpty()){
            t = queue.peek();
            queue.poll();
            if(vised.contains(t)) {
                return false;
            }
            vised.add(t);
            count++;
            if(leftChild[t]!=-1){
                queue.offer(leftChild[t]);
            }
            if(rightChild[t]!=-1){
                queue.offer(rightChild[t]);
            }
        }
        return count == n;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] nums1 = new int[]{3,-1,1,-1};
        int[] nums2 = new int[]{3,-1,1,-1};
        System.out.println(validateBinaryTreeNodes(n,
                nums1,nums2));
    }
}
