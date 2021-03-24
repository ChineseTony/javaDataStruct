package com.tom.sort;


/**
 * @author WangTao
 * 由于无法创建范式数组
 *
 * 归并排序主要思想 分而治之 核心方法归并函数
 */
@SuppressWarnings("unchecked")
public class MergeSort <S extends Comparable<S>> implements Sort<S> {

    @Override
    public void sort(S[] t) {
        S[] temp =(S[]) new Comparable[t.length];
        sort(t, 0, t.length-1,temp);
    }



    private  void sort(S[] t,int left,int right,S[] temp){
        if (left < right){
            int mid = (right - left) / 2 + left;
            sort(t,left,mid,temp);
            sort(t,mid+1,right,temp);
            merge(t,left,mid,right,temp);
        }
    }

    /**
     * 将二个数组归并 成一个有序数组
     * [left,mid) [mide,right)
     * @param t 泛型数组
     * @param left 左边下标
     * @param mid 中间下标
     * @param right 右边下标
     */
    private  void merge(S[] t, int left, int mid, int right,S[] temp) {
        int i = left;
        int j = mid+1;
        int tmp = 0;
        while (i <= mid && j<=right){
            if(t[i].compareTo(t[j]) < 0){
                temp[tmp++] = t[i++];
            }else {
                temp[tmp++] = t[j++];
            }
        }
        while(i<=mid){
            temp[tmp++] = t[i++];
        }
        while(j<=right){
            temp[tmp++] = t[j++];
        }
        tmp = 0;
        while(left <= right){
            t[left++] = temp[tmp++];
        }
    }



    public static void main(String[] args) {
        Sort<Integer> a = new MergeSort<>();
        Integer[] b = new Integer[]{2,3,6,1,10,5,30,90,-1};
        a.sort(b);
        for (int i : b){
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
