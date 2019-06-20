package com.tom.sort;

/**
 * @author WangTao
 * 插入排序
 */
public class InsertSort<S extends Comparable> implements Sort<S> {

    @Override
    public void sort(S[] t) {
        /*int length = t.length;
        int i,j;
        for (i = 1; i < length; i++) {
            S temp = t[i];
            for (j = i; j > 0 && (t[j-1].compareTo(temp) > 0) ; j--) {
                t[j] = t[j-1];
            }
            t[j] = temp;
        }*/

        int length = t.length;
        int i,j;
        for (i = 1; i < length; i++) {
            for (j = i; j > 0 ; j--) {
                if (t[j].compareTo(t[j-1]) < 0){
                    swap(t,j,j-1);
                }else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Sort<Integer> a = new InsertSort<>();
        Integer[] b = new Integer[]{2,3,6,1};
        a.sort(b);
        for (int i : b){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
