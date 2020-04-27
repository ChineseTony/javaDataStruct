package com.tom.oj;

import java.util.*;


/**
 * @author WangTao
 */
public class threen {


    public static void merge(int[] A, int m, int[] B, int n) {
        if(n <= 0){
            return;
        }
        int index = m + n - 1;
        int p = m - 1;
        int q = n - 1;
        while (p >= 0 && q >= 0){
            if (A[p] >= B[q]){
                A[index--] = A[p--];
            }else {
                A[index--] = B[q--];
            }
        }
        while (q >= 0){
            A[index--] = B[q--];
        }

    }


    public static List<Integer> getAll(int i){
        List<Integer> list = new ArrayList<>();
        while (i > 1){
            if (i % 2 == 0){
                i /= 2;
            }else {
                i = (3*i+1)/2;
            }
            list.add(i);
        }
        return list;
    }


    public static void main(String[] args) {
        /*Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        int[] arr = new int[i];
        for (int j = 0; j < i; j++) {
            arr[j] = s.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int j = 0; j < arr.length; j++) {
            if(!set.contains(arr[j])){
                result.add(arr[j]);
                set.addAll(getAll(arr[j]));
            }

        }
        List<Integer> list = new ArrayList<>();
        for(int t:result){
            if(!set.contains(t)){
                list.add(t);
            }

        }
        Collections.sort(list);
        int size = list.size();
        for (int j = size-1; j >= 0; j--) {
            System.out.print(list.get(j));
            if(j != 0){
                System.out.print(" ");
            }
        }
        s.close();*/
        int[] p = {4,0,0,0,0,0};
        int[] q = {1,2,3,5,6};
        merge(p,1,q,5);
        for(int i:p){
            System.out.println(i);
        }


    }
}
