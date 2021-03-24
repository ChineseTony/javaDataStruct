package com.tom.sort;

/**
 * @author WangTao
 * 选择排序
 */
public class SelectSort<S extends Comparable<S>> implements Sort<S>{


    @Override
    public void sort(S[] s) {
        int length = s.length;
        for (int i = 0; i < length; i++) {
            S min = s[i];
            int index = i;
            for (int j = i; j < length; j++) {
                if (s[j].compareTo(min) < 0){
                    min = s[j];
                    //找到最小的数下标
                    index = j;
                }
            }
            swap(s,i,index);
        }
    }



    public static void main(String[] args) {
        Sort<Integer> s = new SelectSort<>();
        Integer[] b = new Integer[]{100,1,8,4,5,6,7};
        s.sort(b);
        for (int i : b){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
