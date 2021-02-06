package com.tom.array;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class MaxProfit {

    private MaxProfit(){

    }


    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < n; ++i) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

// @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                result = Math.max(result,prices[j]-prices[i]);

            }
        }

        return  result;

    }


    /**
     * [7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int len = prices.length;
        int result = 0;
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < len; i++) {
            if(queue.isEmpty()){
                queue.offer(prices[i]);
            }else if (prices[i] > queue.peek()){
                result = Math.max(result,prices[i] - queue.peek());
            }else {
                queue.poll();
                queue.offer(prices[i]);
            }
        }
        return  result;

    }


    public int maxProfit3(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                //记录最低点
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                //保存最大利润值
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }


    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(prices,2));
        int[] prices2 = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit2(prices2));
    }


}
