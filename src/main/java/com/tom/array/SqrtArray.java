package com.tom.array;

import java.util.*;

/**
 * 977. 有序数组的平方r
 * @author WangTao
 *
 * 给定一个按非递减顺序排序的整数数组 MyLinkedListLeetcode，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */
public class SqrtArray {

    public static int[] sortedSquares(int[] A) {
        int length = A.length;
        for (int i = 0; i < length; i++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }


    /**
     * 算法思路  [-4,-1,0,3,10] 将数组分割成 负数和正数部分 进行归并
     * [-4,-1],[0,3,10] 将二个有序的数组进行归并
     * @param A
     * @return
     */
    public static int[] sortedSquares2(int[] A) {
        int length = A.length;
        int j = 0;
        while (j < length && A[j] < 0){
            j++;
        }
        int i = j -1;
        int[] B = new int[length];
        int index = 0;
        while (i >= 0 && j < length){
            if (A[i] * A[i] < A[j] * A[j]){
                B[index++] = A[i] * A[i];
                i--;
            }else {
                B[index++] = A[j] * A[j];
                j++;
            }
        }
        while (i >= 0){
            B[index++] = A[i] * A[i];
            i--;
        }
        while (j < length){
            B[index++] = A[j] * A[j];
            j++;
        }
        return  B;
    }


    public int[] getNoZeroIntegers(int n) {
        int[] result = new int[2];

        for (int i = 1; i < n; i++) {
            int b = n - i;
            if (containsZero(i) && containsZero(b)){
                result[0] = i;
                result[1] = b;
                return result;
            }
        }
        return result;
    }

    private boolean containsZero(int i){
        while (i != 0){
            int t = i % 10;
            if (t == 0){
                return false;
            }

            i = i / 10;
        }
        return true;
    }

    /**
     * 输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
     * 输出：true
     * 解释：依次连接 [91]、[4,64] 和 [78]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/check-array-formation-through-concatenation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param arr
     * @param pieces
     * @return
     */
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        int len = arr.length;
        int i = 0;
        while (i < len){
            int tmp = arr[i];
            //在二维数组中找
            int j;
            boolean flag = true;
            for (j = 0; j < pieces.length; j++) {
                //找到 pieces的值
                if (pieces[j][0] == tmp){
                    int pieceLen = pieces[j].length;
                    for (int k = 0; k < pieceLen; k++) {
                        if (pieces[j][k] != arr[i]){
                            return false;
                        }
                        i++;
                    }
                    flag = false;
                }
            }
            if (flag){
                return false;
            }
        }

        return true;

    }

    public static boolean canFormArray2(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }
        for (int i = 0; i < arr.length;) {
            if (!map.containsKey(arr[i])) {
                return false;
            }
            int[] array = map.get(arr[i]);
            for (int j = 0; j < array.length; j++, i++) {
                if (arr[i] != array[j]) {
                    return false;
                }
            }
        }
        return true;

    }


    public static int[] sortByBits(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = arr.length;
        List<Integer> list = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            map.put(arr[i],countOne(arr[i]));
            list.add(arr[i]);
        }
        list.sort((o1, o2) -> {
            if (!map.get(o1).equals(map.get(o2))) {
                return map.get(o1) - map.get(o2);
            } else {
                return o1 - o2;
            }
        });
        for (int i = 0; i < len; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static int countOne(int a){
        int result = 0;
        while (a != 0){
            result = result +a % 2;
            a = a / 2;
        }
        return result;
    }


    /**
     * @link https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
     * @param arr
     * @return
     */
    public static boolean canThreePartsEqualSum(int[] arr) {
        if (arr == null || arr.length < 3){
            return false;
        }
        int sum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
        }
        if (sum % 3 != 0){
            return false;
        }
        int tmp = sum  / 3;
        sum = 0;
        int count =0;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
            if (sum == tmp){
                count++;
                sum = 0;
            }
        }
        return count >= 3;
    }

    public static void main(String[] args) {
        int[] A = new int[]{-4,-1,0,3,10};
        int[] B = sortedSquares2(A);
        for (int i : B){
            System.out.print(i+" ");
        }
        System.out.println();
        int[] arr = new int[]{1,2};
        int[][] pieces = new int[][]{{1},{3}};
        System.out.println(canFormArray(arr,pieces));

        int[] tmp = new int[]{0,1,2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(sortByBits(tmp)));

        tmp = new int[]{10,-10,10,-10,10,-10,10,-10};
        System.out.println(canThreePartsEqualSum(tmp));
    }
}
