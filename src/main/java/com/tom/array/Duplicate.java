package com.tom.array;

/**
 * @author WangTao
 */
public class Duplicate {

    /**
     * 判断数组是否有重复的元素
     * @param arr 数组必须符合一定的要求 比如 【1,2,3,4】
     *            所有的元素都必须在[0,length]之间
     * @return 是否有重复的元素·
     */
    public static boolean duplicate(int[] arr){
        if (arr == null || arr.length <= 0){
            return false;
        }
        int length = arr.length;
        //验证数组的合法性
        for (int i = 0; i < length; i++) {
            if (arr[i] < 0 || arr[i] > length - 1){
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            while (arr[i] != i){
                if (arr[i] == arr[arr[i]]){
                    //重复的数组
                    System.out.println(arr[i]);
                    return true;
                }
                swap(arr,arr[i],i);
            }
        }
        return false;
    }

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 3, 4};
        System.out.println(Duplicate.duplicate(arr));
    }
}
