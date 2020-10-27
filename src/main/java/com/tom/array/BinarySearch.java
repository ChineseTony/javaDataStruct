package com.tom.array;


public class BinarySearch<T extends Comparable<T>> {

    private BinarySearch(){

    }

    public int search(T[] tmp, T target){
        int left = 0;
        int right = tmp.length - 1;
        while (left <= right){
            int mid = (right - left) >> 1 + left;
            if(tmp[mid].compareTo(target) < 0){
                left = mid+1;
            }else if (tmp[mid].compareTo(target) > 0){
                right = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 查找第一个值等于给定值的元素
     * @param tmp
     * @param target
     * @return
     */
    public int search2(T[] tmp, T target){
        int left = 0;
        int right = tmp.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if(tmp[mid].compareTo(target) < 0){
                left = mid+1;
            }else if (tmp[mid].compareTo(target) > 0){
                right = mid-1;
            }else {
                if (mid == 0 || tmp[mid - 1] != target){
                    return mid;
                }else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }



    /**
     * 查找最后一个值等于给定值的元素
     * @param tmp
     * @param target
     * @return
     */
    public int search3(T[] tmp, T target){
        int left = 0;
        int right = tmp.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if(tmp[mid].compareTo(target) < 0){
                left = mid+1;
            }else if (tmp[mid].compareTo(target) > 0){
                right = mid-1;
            }else {
                if (mid == tmp.length-1 || tmp[mid + 1] != target){
                    return mid;
                }else {
                    left = mid +1;
                }
            }
        }
        return -1;
    }

//    查找第一个大于等于给定值的元素
    public int search4(T[] tmp, T target){
        int left = 0;
        int right = tmp.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if(tmp[mid].compareTo(target) >= 0){
                if (mid == 0 || tmp[mid-1].compareTo(target) < 0){
                    return mid;
                }else {
                    right = mid-1;
                }
            }else{
                left = mid +1;
            }
        }
        return -1;
    }


    /**
     * [2,1,1,2]
     * 2 + 2 = 4
     * @param nums
     * @return
     * @link https://leetcode-cn.com/problems/the-masseuse-lcci/
     */
    public int massage(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dp0 = 0, dp1 = nums[0];

        for (int i = 1; i < n; ++i){
            int tdp0 = Math.max(dp0, dp1); // 计算 dp[i][0]
            int tdp1 = dp0 + nums[i]; // 计算 dp[i][1]

            dp0 = tdp0; // 用 dp[i][0] 更新 dp_0
            dp1 = tdp1; // 用 dp[i][1] 更新 dp_1
        }
        return Math.max(dp0, dp1);

    }

    public static void main(String[] args) {
        BinarySearch<Integer> binarySearch = new BinarySearch<>();
        Integer[] tmp = new Integer[]{1,2,4,5,5,5,6,7,10};
        System.out.println(binarySearch.search(tmp,5));
        System.out.println(binarySearch.search2(tmp,5));
        System.out.println(binarySearch.search3(tmp,5));
        System.out.println(binarySearch.search4(tmp,8));

    }
}
