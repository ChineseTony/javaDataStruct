package com.tom.oj;

import java.util.Arrays;
import java.util.Scanner;


/**
 * @author tom
 * @link https://pintia.cn/problem-sets/15/problems/866
 */
public class Magic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] arr = new int[m];
        int index = 0;
        while (m-- >0){
            arr[index++] = scanner.nextInt();
        }
        m = scanner.nextInt();
        index = 0;
        int[] another = new int[m];
        while (m-- > 0){
            another[index++] = scanner.nextInt();
        }
        Arrays.sort(arr);
        Arrays.sort(another);
        int i = arr.length;
        int j = another.length;
        m =0;
        int n=0;
        int sum = 0;
        while (n <i && m < j && arr[m] < 0 && another[n] < 0 ){
            sum += arr[m++] * another[n++];
        }
        n = i-1;
        m = j -1;
        while (n >= 0 && m >=0  && arr[n] > 0 && another[m] > 0 ){
            sum += arr[n--] * another[m--];
        }
        System.out.println(sum);


    }
}
