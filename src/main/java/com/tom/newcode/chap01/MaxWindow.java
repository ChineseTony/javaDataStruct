package com.tom.newcode.chap01;

import java.util.LinkedList;

/**
 * @author WangTao
 *
 */
public class MaxWindow {

    /**
     * forexample arr =[5,4,1,3] w =2 放回 [5,4,3]
     * @param arr
     * @param w
     * @return
     */
    public static int[] getMaxWindow(int[] arr,int w){
        if(arr == null || w < 1 || w > arr.length){
            return null;
        }
        int length = arr.length;
//        双端队列 队首元素保存w数组的最大值数组下标
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[length-w+1];
        int index = 0;
        for (int i = 0; i < length; i++) {
            //保证队首元素时w数组中的最大值
            while (!qmax.isEmpty() && arr[qmax.peekLast()] < arr[i]){
                qmax.pollLast();
            }
            //添加到队列元素中
            qmax.addLast(i);
            // 移动到下一个w窗口  将队首元素删除
            if (qmax.peekFirst() == i - w){
                qmax.pollFirst();
            }
            //将最大值保存到res数组中
            if (i >= w-1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {4,5,2,3};
        int[] maxData = MaxWindow.getMaxWindow(data, 2);
        for (int i:maxData){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
