package com.tom.sort;

import java.util.Arrays;

/**
 * @author WangTao
 */
public class HeapSort <S extends Comparable<S>> implements Sort<S> {


    /**
     * 堆排序思路 将无序数字 构建为一个大根堆 把根元素放到数组末尾
     * 然后从数组末尾到数组开头
     * @param t 排序数组
     */
    @Override
    public void sort(S[] t) {
        int len = t.length;
        //从一个非叶子节点中的父节点进行下沉操作 heapify 堆化
        for (int i = getParent(len-1); i >= 0 ; i--) {
            shiftDown(t,i,len);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=len - 1;j>0;j--){
            //将堆顶元素与末尾元素进行交换
            swap(t,0,j);
            //重新对堆进行调整
            shiftDown(t,0,j);
        }

    }

    private void shiftDown(S[] t, int i, int len) {
//        有左孩子
        while (getLeftChild(i) < len){
            int j = getLeftChild(i);
//            有右孩子 并且右孩子大于左孩子
            if (j + 1 < len
                    &&  t[j + 1].compareTo(t[j]) >0){
                j++;
            }
            //大于左右孩子节点值 j 保留左右孩子较大值
            if (t[i].compareTo(t[j]) >0) {
                break;
            }
            swap(t, i, j);
            //需要递归下沉
            i = j;
        }

    }


    public void sort2(S[] t){
        int len = t.length;
        //从最后一个节点进行上沉操作 heapify 堆化
        for (int i = len-1; i >= 0 ; i--) {
            shiftUp(t,i);
        }
        for(int j=len - 1;j>0;j--){
            //将堆顶元素与末尾元素进行交换
            swap(t,0,j);
            //重新对堆进行调整
            for (int i = j-1; i > 0 ; i--) {
                shiftUp(t,i);
            }
        }
    }

    private void shiftUp(S[] t, int i) {
        while (i > 0 && t[i].compareTo(t[getParent(i)]) < 0){
            swap(t,getParent(i),i);
            i = getParent(i);
        }

    }

    private int getLeftChild(int i){
        return 2 * i + 1;
    }

    private int getParent(int i){
        if (i == 0){
            throw  new RuntimeException("0 index isn't parent");
        }
        return (i - 1) / 2;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{7,6,7,11,5,12,3,0,1};
        System.out.println("排序前："+ Arrays.toString(arr));
        new HeapSort<Integer>().sort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
        new HeapSort<Integer>().sort2(arr);
        System.out.println("排序后："+Arrays.toString(arr));
    }
}
