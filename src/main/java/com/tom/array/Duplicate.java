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

    // arr数组  所有的元素值都必须在[1,length-1]之间
    public static int getDuplicate(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }
        int length = arr.length;
        int start = 1;
        int end = length-1;
        while (end >= start){
            int mid = ((end-start)>>1) + start;
            int count = countRange(arr,start,mid);
            if (end == start){
                if (count > 1){
                    return start;
                }else {
                    break;
                }
            }
            //重复元素在左边
            if (count > (mid-start+1)){
                end = mid;
            }else {
                start = mid+1;
            }

        }
        return -1;
    }

    private static int countRange(int[] arr,int start,int end){
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int count = 0;
        for (int i : arr) {
            if (i >= start && i <= end){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 3, 4};
        System.out.println(Duplicate.duplicate(arr));
        System.out.println(Duplicate.getDuplicate(arr));
    }
}
