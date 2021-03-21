package com.tom.oj;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wangtao
 * @link https://pintia.cn/problem-sets/434/problems/6180
 */
public class CommonParent {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] arr = new int[count];
        int i = 0;
        while (count -- > 0){
            arr[i++] = scanner.nextInt();
        }
        int tmp = scanner.nextInt()-1;
        int k = scanner.nextInt()-1;
        if (arr[tmp] == 0){
            System.out.printf("ERROR: T[%d] is NULL",tmp+1);
        }else if (arr[k] == 0){
            System.out.printf("ERROR: T[%d] is NULL",k+1);
        }else {
            List<Integer> tmpParent = new ArrayList<>();
            List<Integer> kParent = new ArrayList<>();
            while (tmp > 0){
                tmpParent.add(tmp);
                tmp = (tmp-1) / 2;

            }
            tmpParent.add(0);
            while (k > 0){
                kParent.add(k);
                k = (k-1) / 2;
            }
            kParent.add(0);
            System.out.println(tmpParent.toString());
            System.out.println(kParent.toString());
            int len1 = tmpParent.size();
            int len2 = kParent.size();
            int index1 = 0;
            int index2 = 0;
            if (len1 > len2){
                index1 = len1 - len2;
            }else if (len1 < len2){
                index2 = len2 - len1;
            }
            while (index1 < len1 && index2 < len2){
                if (tmpParent.get(index1)
                .equals(kParent.get(index2))){
                    int tmpVal = tmpParent.get(index1);
                    System.out.println((tmpVal+1)+" "+arr[tmpVal]);
                    break;
                }
                index1++;
                index2++;
            }
        }

    }

}
