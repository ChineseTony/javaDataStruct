package com.tom.sort;

/**
 * @author WangTao
 *
 * 快速排序主要思想 分而治之 核心方法partition
 */
@SuppressWarnings("unchecked")
public class QuickSort<S extends Comparable<S>> implements Sort<S> {


    @Override
    public void sort(S[] t) {
        sort(t,0, t.length-1);
    }

    private void sort(S[] t,int low,int high){
        if (low < high){
            int location = partition(t,low,high);
            //递归调用
            sort(t,low, location-1);
            sort(t,location+1,high);
        }
    }


    private int  partition(S[] t,int low,int high){
        S t1 = t[low];
        while (low < high){
            //找到比t[low] 小的数
            while ( low < high && t1.compareTo(t[high]) < 0 ){
                high--;
            }
            // 交换位置
            swap(t,low,high);
            while ( low < high && t1.compareTo(t[low]) >0 ){
                low++;
            }
            swap(t,low,high);
        }
        //将t1插入到合适的位置
        t[low] = t1;
        return low;
    }

    public static void main(String[] args) {
        Sort<Integer> a = new QuickSort<>();
        Integer[] b = new Integer[]{2,3,6,1,10,5,30,90,-1};
        a.sort(b);
        for (int i : b){
            System.out.print(i + " ");
        }
        System.out.println();
    }



}
