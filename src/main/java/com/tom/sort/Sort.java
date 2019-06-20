package com.tom.sort;

/**
 * @author WangTao
 */
public interface Sort<T>  {

    /**
     * 排序接口
     * @param t 排序数组
     */
    void sort(T[] t);


    /**
     * 交换数组位置
     * @param t 数组
     * @param i 第一个数的索引
     * @param j 第二个数的索引
     */
    default void swap(T[] t,int i,int j){
        T temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }


}
