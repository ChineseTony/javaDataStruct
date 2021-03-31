package com.tom.meituan;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * 时间限制： 3000MS
 * 内存限制： 1048576KB
 * 题目描述：
 *      小美超级幸运，这不今天又双叒叕中奖了！！！主办方给了小美 n 个积分球，从左到右排成一行，第 i 个积分球的积分为 ai 。让小美自行选取其中一段连续的积分球，最多选取k 个。假设小美选取了 [l, r] 这个区间中的积分球，小美获得的积分为al⊕al+1⊕…⊕ar-1⊕ar。例如，小美选取了 [3, 4, 2] 这三个积分球那么小美最终获得的积分为 3⊕4⊕2 = 5。
 *
 *      小美想要获得最多的积分。小美虽然幸运，但是却被这个难题难倒了，请你帮帮小美，帮她获得更多积分。
 *
 *      ⊕：异或运算的数学符号。其运算法则相当于不带进位的二进制加法：二进制下用1表示真，0表示假，则异或的运算法则为：0⊕0=0，1⊕0=1，0⊕1=1，1⊕1=0（同为0，异为1），这些法则与加法是相同的，只是不带进位，所以异或常被认作不进位加法。例如：3⊕5=(011)2 ⊕ (101) 2 = (110) 2 = 6。
 *
 *      异或在C语言中表示为^。
 *
 *
 *
 * 输入描述
 *  第一行两个数 n, k。(1 ≤ n, k ≤ 100000)。
 *
 *  第二行 n 个数，第 i 个数是 ai。(0 ≤ ai ≤ 1000000000)。
 *
 * 输出描述
 *  输出一个数，代表小美能够获得的最多积分。
 *
 *
 * 样例输入
 * 3 2
 * 1 2 4
 * 样例输出
 * 6
 */
public class Meituan4 {

    public static void main(String[] args) {

        Scanner scanner =new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int len = n;
        Queue<Integer> queue = new ArrayDeque<>();
        int result = 0;
        //    使用滑动窗口 使用 数组算了
        int[] arr = new int[len];
        int index = 0;
        while (n-- > 0){
            int tmpVal = scanner.nextInt();
            arr[index++] = tmpVal;
          /*  if (queue.isEmpty() ){
                result = Math.max(result,tmpVal);
                queue.offer(tmpVal);
            }else if (queue.size() < k){
                //对队中元素进行异或操作
                queue.offer(tmpVal);
                int t = 0;
                Integer[] objects =queue.toArray(new Integer[0]);
                for (int i = 0; i < queue.size(); i++) {
                    t = t ^ objects[i];
                }
                result = Math.max(result,t);
            }else {
                queue.poll();
                queue.offer(tmpVal);
                int t = 0;
                Integer[] objects =  queue.toArray(new Integer[0]);
                for (int i = 0; i < queue.size(); i++) {
                    t = t ^ objects[i];
                }
                result = Math.max(result,t);
            }*/

        }
//        暴力
        for (int i = 0; i < len; i++) {
            int j = i;
            result = Math.max(arr[i],result);
            if (i +k <= len){
                int tmp = 0;
                for (int l = i; l < i + k  ; l++) {
                    tmp = tmp ^ arr[l];
                }
                result = Math.max(tmp,result);
            }

        }
        System.out.println(result);
    }
}
