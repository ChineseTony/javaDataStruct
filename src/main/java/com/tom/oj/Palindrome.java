package com.tom.oj;

public class Palindrome {


    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        int len = s.length();
        int left =0;
        int right = len -1;
        while (left < right){
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if(!Character.isDigit(leftChar) && !Character.isAlphabetic(leftChar)){
                left++;
                continue;
            }
            if(!Character.isDigit(rightChar) && !Character.isAlphabetic(rightChar)){
                right--;
                continue;
            }
            if(!equalsChar(leftChar,rightChar)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean equalsChar(char c,char d){
        String t1 = String.valueOf(c);
        String t2 = String.valueOf(d);
        return t1.equalsIgnoreCase(t2);
    }

    public static int[] finalPrices(int[] prices) {
        if(prices == null || prices.length == 0){
            return new int[0];
        }
        int len = prices.length;
        int[] result = new int[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            boolean flag = true;
            for (int j = i+1 ; j < len; j++) {
                if(prices[j] <= prices[i]){
                    result[index++] = prices[i] - prices[j] ;
                    flag = false;
                    break;
                }
            }
            if (flag){
                result[index++] = prices[i];
            }
        }
        return result;

    }


    public static void main(String[] args) {
        int[] tmp = new int[]{8};
        int[] arr=finalPrices(tmp);
        for(int i: arr){
            System.out.print(i+"\t");
        }
        System.out.println();

    }
}
