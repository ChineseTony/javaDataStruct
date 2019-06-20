package com.tom.array;

/**
 * @author WangTao
 */
public class MergeArray {


    /**
     *
     * @param a 有序数组
     * @param b 有序数组
     * @return 任然有序数组
     */
    public static int[] merge(int[] a,int[] b){
        int length1 = a.length;
        int length2 = b.length;
        int[] c = new int[length1+length2];
        int i = 0 ,j = 0,index =0;
        while (i < length1 && j < length2){
            if(a[i] <= b[j]){
                c[index++] = a[i++];
            }else {
                c[index++] = b[j++];
            }
        }
        while (i < length1){
            c[index++] = a[i++];
        }
        while (j < length2){
            c[index++] = b[j++];
        }
        return c;
    }

    public static void main(String[] args) {
        int[] a = {1,3,5};
        int[] b = {2,4,6};
        int[] c = merge(a,b);
        for (int i : c){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
