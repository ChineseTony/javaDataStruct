package com.tom.oj;

import java.util.*;


/**
 * @author WangTao
 */
public class threen {


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
        Scanner s = new Scanner(System.in);
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
        s.close();

    }
}
