package com.tom.oj.tencent.ch2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Tencent3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int tmp = n;
        int index = 1;
        //忘记考虑重复的key了
        Map<Integer,Integer> map = new HashMap<>();
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        while (tmp-- > 0){
             int value = scanner.nextInt();
             map.put(value,index);
             queue.add(new Pair(value,1));
             index++;
        }
//        System.out.println(queue.size());
        while (k-- >0){
            Pair pair = queue.poll();
            System.out.println(map.get(pair.key));
            queue.offer(new Pair(pair.key,pair.value + 1));
        }

    }
    static class Pair implements Comparable<Pair>{
        int key;
        int value;


        public Pair(int key,int value){
            this.key = key;
            this.value = value;
        }


        @Override
        public int compareTo(Tencent3.Pair o) {
            if (this.key * this.value
            == o.key * o.value){
                return Integer.compare(this.key,o.key);
            }else{
                return Integer.compare(this.key * this.value,
                        o.key * o.value);
            }
        }

        @Override
        public String toString() {
            return this.key+"-->"+this.value;
        }
    }

//    private static int getMin(int[] arr,int k){
//
//    }

    public ListNode solve (ListNode S) {
        // write code here
        if(S == null || S.next == null){
            return S;
        }
        ListNode tmp = new ListNode();
        tmp.val = -1;
        List<Integer> list = new ArrayList<>();
        ListNode p = S;
        while (p != null){
            list.add(p.val);
            p = p.next;
        }
        List<List<Integer>> tmpValue = new ArrayList<>();
        int len = list.size();
        for (int i = 0; i <= len; i++) {
            tmpValue.add(new ArrayList<>(list));
            int last = list.remove(len-1);
            list.add(0,last);

        }
        Collections.sort(tmpValue, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                for (int i = 0; i < o1.size(); i++) {
                    if (!o1.get(i).equals(o2.get(i))){
                        return Integer.compare(o1.get(i),o2.get(i));
                    }
                }
                return 1;
            }
        });
        List<Integer> a = tmpValue.get(0);
        ListNode pre = new ListNode();
        ListNode tmpNode = pre;
        for (int i = 0; i < a.size(); i++) {
            ListNode tmp1 = new ListNode();
            tmp1.val = a.get(i);
            tmpNode.next = tmp1;
            tmpNode = tmpNode.next;
        }
        return pre.next;


    }


  public class ListNode {
    int val;
    ListNode next = null;
  }
}
