package com.tom.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WangTao
 * @link https://leetcode-cn.com/problems/fair-candy-swap/
 * 888. 公平的糖果棒交换
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，
 * B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 *
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。
 * （一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。

 * 示例 1：
 *
 * 输入：A = [1,1], B = [2,2]
 * 输出：[1,2]
 * 示例 2：
 *
 * 输入：A = [1,2], B = [2,3]
 * 输出：[1,2]
 */
public class FairCandySwap {

    public static int[] fairCandySwap(int[] a, int[] b) {
        int sumA = Arrays.stream(a).parallel().sum();
        int sumB = Arrays.stream(b).parallel().sum();
        //求出差值
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<>();
        for (int num : a) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : b) {
            int x = y + delta;
            //并且 a 数组中有改元素
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {

    }
}
