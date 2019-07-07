package com.tom.recursion;

/**
 * @author WangTao
 */
public class Sum {

    public static int sum(int[] arr){
        return sum(arr,0);
    }

    //计算arr[index,length)所有数之和
    private static int sum(int[] arr,int index){
        if(index == arr.length){
            return 0;
        }else {
            return arr[index] + sum(arr,index+1);
        }

    }
}
