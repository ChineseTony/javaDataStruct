package com.tom.sort;

/**
 * @author WangTao
 * 希尔排序
 */
public class ShellSort<S extends Comparable<S>> implements Sort<S> {


    @Override
    public void sort(S[] t) {
        int length = t.length;
        int h = 1;
        while (h < length / 3){
            h = 3 * h + 1;
        }
        while (h >= 1){
            int i ,j;
            for (i = h; i < length; i++){
                for (j = i; j >= h && (t[j].compareTo(t[j-h]) < 0); j -= h){
                    swap(t,j,j-h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Sort<Integer> a = new ShellSort<>();
        Integer[] b = new Integer[]{2,-1,3,6,1,100,343};
        a.sort(b);
        for (int i : b){
            System.out.print(i + " ");
        }
        System.out.println();
    }


}
