package com.tom.oj;

/**
 * @author WangTao
 * 我们把符合下列属性的数组 MyLinkedListLeetcode 称作山脉：
 * leetcode 852
 *
 * MyLinkedListLeetcode.length >= 3
 * 存在 0 < i < MyLinkedListLeetcode.length - 1
 * 使得A[0] < MyLinkedListLeetcode[1] < ... MyLinkedListLeetcode[i-1] < MyLinkedListLeetcode[i] > MyLinkedListLeetcode[i+1] > ... > MyLinkedListLeetcode[MyLinkedListLeetcode.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 
 * MyLinkedListLeetcode[0] < MyLinkedListLeetcode[1] < ... MyLinkedListLeetcode[i-1] < MyLinkedListLeetcode[i] > MyLinkedListLeetcode[i+1] > ... > MyLinkedListLeetcode[MyLinkedListLeetcode.length - 1] 的 i 的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[0,1,0]
 * 输出：1
 * 示例 2：
 *
 * 输入：[0,2,1,0]
 * 输出：1
 *
 */
public class PeakIndexInMountainArray {

    public static int peakIndexInMountainArray(int[] A) {
        if (A == null){
            return -1;
        }
        int i = 0;
        int j = A.length-1;
        while (i < j){
            if (A[i] < A[j]){
                i++;
            }else {
                j--;
            }
        }
        return i;
    }

    //二分查找
    public static int peakIndexInMountainArray2(int[] A) {
        if (A == null){
            return -1;
        }
        int i = 0;
        int j = A.length-1;
        int mid = 0;
        while (i < j){
            mid = (i + j) / 2;
            //在山顶
            if (A[mid] > A[mid+1] && A[mid] > A[mid-1]){
                return mid;
            }else if (A[mid] > A[mid-1]){
                i = mid;
            }else {
                j = mid;
            }
        }
        return -1;
    }
}
