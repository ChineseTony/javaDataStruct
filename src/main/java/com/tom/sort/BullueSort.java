package com.tom.sort;

/**
 * @author WangTao
 */
public class BullueSort<S extends Comparable<S>> implements Sort<S> {


    @Override
    public  void sort(S[] t){
        int length = t.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (t[i].compareTo(t[j]) > 0){
                    swap(t,i,j);
                }
            }
        }
    }


    public static void main(String[] args) {
        Sort<Integer> a = new BullueSort<>();
        Integer[] b = new Integer[]{2,3,6,1};
        a.sort(b);
        for (int i : b){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
