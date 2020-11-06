package com.tom.array;

public class DivingBoard {

    private DivingBoard(){

    }

    public static int[] divingBoard(int shorter, int longer, int k) {
        if(k <= 0){
            return new int[0];
        }else if (shorter == longer){
            return new int[]{shorter * k};
        }
        int[] result = new int[k+1];
        int index = 0;
        int sum = shorter * k;
        while (k-- >= 0){
            result[index] = sum - index * shorter + index * longer;
            index++;
        }
        return  result;

    }


    /**
     * @link https://leetcode-cn.com/problems/water-bottles/
     * @param numBottles
     * @param numExchange
     * @return
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        int result = numBottles;
        int t = numBottles / numExchange;
        while (t != 0){
            t = numBottles / numExchange;
            result += t;
            numBottles = t + numBottles % numExchange;
        }
        return result;

    }

    public static void main(String[] args) {
        int[] tmp = divingBoard(1,1,10);
        for(int i:tmp){
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println(numWaterBottles(9,3));
    }
}
