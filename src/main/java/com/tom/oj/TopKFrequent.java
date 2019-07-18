package com.tom.oj;

import java.util.*;

/**
 * @author WangTao
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class TopKFrequent {

    public  List<Integer> topKFrequent(int[] nums, int k) {

        //统计次数
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else {
                map.put(nums[i],1);
            }
        }
        //java优先队列默认是最小堆
        PriorityQueue<Freq> queue = new PriorityQueue<>();
        for (int key:map.keySet()){
            if (queue.size() < k){
                queue.offer(new Freq(key,map.get(key)));
            }else if (map.get(key) > queue.peek().times){
                queue.poll();
                queue.offer(new Freq(key,map.get(key)));
            }
        }
        List<Integer> list = new ArrayList<>(queue.size());
        while (!queue.isEmpty()){
            list.add(queue.poll().e);
        }
        return list;

    }

    private class Freq implements Comparable<Freq>{
        int times;
        int e;

        public Freq(int e,int times) {
            this.times = times;
            this.e = e;
        }

        @Override
        public int compareTo(Freq o) {
            if (this.times > o.times){
                return 1;
            }else if (this.times < o.times){
                return -1;
            }else {
                return 0;
            }
        }
    }

    private class FreqComparator implements Comparator<Freq>{

        @Override
        public int compare(Freq o1, Freq o2) {
            return o1.times - o2.times;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,1,-1,2,-1,2,3};
        TopKFrequent t = new TopKFrequent();
        List<Integer> list = t.topKFrequent(arr,2);
        for (Integer i : list){
            System.out.println(i);
        }
    }
}
