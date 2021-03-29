package com.tom.array;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tom
 * @link https://leetcode-cn.com/problems/reveal-cards-in-increasing-order/
 */
public class DeckRevealedIncreasing {

//
//    /**
//     * 思路先排序 然后循环插入数组
//     * @param deck
//     * @return
//     */
//    public static int[] deckRevealedIncreasing(int[] deck) {
//        Arrays.sort(deck);
//        int len = deck.length;
//        int[] tmp = new int[len];
//        int count = 0;
//        for (int i = 0; i < len; i++) {
//            int tmpValue = deck[i];
////           寻找合适的位置插入数据
//            if (count < len) {
//                tmp[count] = tmpValue;
//                count += 2;
//            }else {
//                int j = 0;
//                int tmpCount =0;
//                if (len % 2 == 1){
//                    tmpCount = 1;
//                }
//                while (j < len){
//                    if (tmp[j] == 0){
//                        if (tmpCount % 2 == 0){
//                            tmp[j] = tmpValue;
//                            break;
//                        }
//                        tmpCount ++;
//                        if (i == len-1){
//                            tmp[j] = tmpValue;
//                        }
//                    }
//                    j++;
//                }
//            }
//
//        }
//        return tmp;
//    }

    public static int[] deckRevealedIncreasing2(int[] deck) {
        int N = deck.length;
        Deque<Integer> index = new LinkedList<>();
        for (int i = 0; i < N; ++i) {
            index.add(i);
        }
        int[] ans = new int[N];
        Arrays.sort(deck);
        for (int card: deck) {
            ans[index.pollFirst()] = card;
            if (!index.isEmpty()) {
//                添加到队尾元素
                index.add(index.pollFirst());
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{17,13,11,2,3,5,7};
//        int[] arr = new int[]{17,13,15};
        Arrays.stream(deckRevealedIncreasing2(arr))
                .forEach(v -> {
                    System.out.print(v+" ");
                });
    }
}
