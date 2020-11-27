package com.tom.array;

import java.util.*;

/**
 * @author WangTao
 * 输入：[1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 */
public class HightCheck {

    public static int heightChecker(int[] heights) {
        int number = 0;
        int length = heights.length;
        int[] tmp = new int[length];
        System.arraycopy(heights,0,tmp,0,length);
        Arrays.sort(heights);
        for (int i = 0; i < length; i++) {
            if (tmp[i] != heights[i]){
                number++;
            }
        }
        return number;
    }


    //基数排序思想

    public static int heightChecker2(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (heights[j++] != i){
                    count++;
                }
            }
        }
        return count;
    }


    public static int[] frequencySort(int[] nums) {
        if(nums == null || nums.length == 0){
            return nums;
        }
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }else {
                map.put(nums[i],map.get(nums[i]) + 1);
            }
        }
        List<Fre> freList = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            freList.add(new Fre(entry.getKey(),entry.getValue()));
        }
        freList.sort((o1, o2) -> {
            if (o1.value > o2.value){
                return 1;
            } else if (o1.value < o2.value) {
                return -1;
            }else {
                if (o1.key > o2.key){
                    return -1;
                }else if (o1.key < o2.key){
                    return 1;
                }else {
                    return 0;
                }
            }
        });
        int i = 0;
        for (Fre fre:freList){
            while (fre.value -- >0){
                nums[i++] = fre.key;
            }
        }
        return nums;
    }

    static class Fre{
        public int key;
        public int value;

        public Fre(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Fre{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,1,4,2,1,3};
        System.out.println(heightChecker(a));
        int[] nums = new int[]{2,3,1,3,2};
        Arrays.stream(frequencySort(nums)).forEach(System.out::println);
    }
}
