package com.tom.array;

/**
 * @author WangTao
 *
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class MergeOrderArray {


    /**
     * 开辟一个新的空间 空间复杂度比较高
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] c = new int[m+n];
        int i = 0,j = 0;
        int index = 0;
        while (i < m && j < n){
            if (nums1[i] <= nums2[j]){
               c[index++] = nums1[i++];
            }else {
                c[index++] = nums2[j++];
            }
        }
        while (i < m){
            c[index++] = nums1[i++];
        }
        while (j < n){
            c[index++] = nums2[j++];
        }
        for (int k = 0; k < m+n; k++) {
            nums1[k]  = c[k];
        }

    }

    /**
     * 从后往前插入输出
     * 节省空间复杂度
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int pos = m + n - 1;
        while (n > 0) {
            if (m > 0 && nums1[m - 1] > nums2[n - 1]) {
                nums1[pos--] = nums1[--m];
            }else {
                nums1[pos--] = nums2[--n];
            }
        }
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        merge2(nums1,3,nums2,3);
        for (int i : nums1){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
