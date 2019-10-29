package com.tom.offer.ch01;

/**
 * @author WangTao
 * 刷题了解算法的解题思路 手写算法 一定要白板编程实现常用的算法和数据结构
 */
public class FindDuplicate {

    private FindDuplicate(){

    }

    // 找出元素中重复的元素  [0,1,1] 输出 1 不存在输出-1
    public static int findDuplicate(int[] arrs){
        if (arrs == null || arrs.length <= 0){
            return -1;
        }
        int length = arrs.length;
        //检查 数组元素是不是在 0-length-1之间
        for (int i = 0; i < length; i++) {
            if (arrs[i] < 0 || arrs[i] > length-1){
                return -1;
            }
        }
        for (int i = 0; i < length; i++) {
//          判读 坐标是不是和对应的元素一致
            while (arrs[i] != i){
                //不一致
                if (arrs[i] == arrs[arrs[i]]){
                    return arrs[i];
                }
                //不在对应的位置 将元素放到对应的位置上 位置0对应的元素是0
                swap(arrs,i,arrs[i]);
            }
        }
        return -1;
    }

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,4,3,1,3,5};
        System.out.println(findDuplicate(arr));
        printArray(arr);

    }

    private static void printArray(int[] arr){
        for (int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
