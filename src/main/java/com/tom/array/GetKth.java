package com.tom.array;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/sort-integers-by-the-power-value/
 */
public class GetKth {

    public static int getKth(int lo, int hi, int k) {

        List<Wrapper> list = new ArrayList<>();
        for (int i = lo;i<= hi;i++){
            list.add(new Wrapper(i,getWeight(i)));
        }
        list.sort((o1, o2) -> {
            if (o1.value == o2.value){
                return Integer.compare(o1.key,o2.key);
            }else {
                return Integer.compare(o1.value,o2.value);
            }
        });
//        System.out.println(list);
        return list.get(k-1).key;
    }

    public static class  Wrapper{
        int key;
        int value;

        public Wrapper(int key,int value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "-->" +value;
        }
    }

    private static int getWeight(int k){
        int result = 0;
        while (k != 1){
            if (k % 2 == 1){
                k = 3 * k + 1;
            }else {
                k = k /2;
            }
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        int lo = 12, hi = 15, k = 2;
        System.out.println(getKth(lo,hi,2));
    }
}
