package com.tom.oj;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author WangTao
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        int[] result = new int[2];
        while (i < j){
            if(nums[i] + nums[j] == target){
                result[0] = nums[i];
                result[1] = nums[j];
                break;
            }else if (nums[i] + nums[j] < target){
                i ++;
            }else {
                j--;
            }
        }
        return result;
    }


    public static int[] dailyTemperatures(int[] arr) {
        //73,74,75,71,69,72,76,73
        if(arr == null || arr.length == 0){
            return new int[0];
        }
        int len = arr.length;
        int[] result = new int[len];
        int i = 0;
        for (int j = 0; j < len; j++) {
            int tmpVal = arr[j];
            int k ;
            for (k = i+1; k < len; k++) {
                if(arr[k] > tmpVal){
                    result[i++] = k - j;
                    break;
                }
            }
            if(k == len){
                result[i++] = 0;
            }
        }
        return result;
    }


    public static int[] dailyTemperatures2(int[] arr) {
        if(arr == null || arr.length == 0){
            return new int[0];
        }
        int len = arr.length;
        int[] result = new int[len];
        int i=0;
        //保存当前元素的下标
        Deque<Integer> stack = new ArrayDeque<> ();
        while (i < len){
            //把栈顶元素小于 当前元素的值
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                    int index = stack.pop();
                    result[index] = i - index;
            }
            stack.push(i);
            i++;
        }
        //没有找到比当前元素大的值
        while (!stack.isEmpty()){
            result[stack.pop()] = 0;
        }
        return result;
    }


    public int[] sortArrayByParityII(int[] A) {
        if(A == null){
            return null;
        }
        int N = A.length;
        int[] ans = new int[N];

        int t = 0;
        for (int x: A){
            if (x % 2 == 0) {
                ans[t] = x;
                t += 2;
            }
        }

        t = 1;
        for (int x: A){
            if (x % 2 == 1) {
                ans[t] = x;
                t += 2;
            }
        }

        return ans;

    }



    public static boolean validMountainArray(int[] A) {
        if(A == null || A.length == 0){
            return false;
        }
        int len = A.length;
        if(len == 1){
            return false;
        }
        int i;
        int index = 0;

        for (i = 0; i < len-1; i++) {
            if (A[i+1] <= A[i]){
                index = i;
                break;
            }
        }
        if(index >= len-1 || index == 0){
            return false;
        }
        for (i=index;i<len-1;i++){
            if(A[i] <= A[i+1]){
                return false;
            }
        }
        return true;
    }



    //跟 最大子序列和思路一样 动态规划
    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLen = 0, len = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] <= nums[i]) {
                maxLen = Math.max(maxLen, len);
                //遇到比当前小的值 更新递增索引
                len = 1;
            } else {
                len++;
            }
        }
        return Math.max(maxLen, len);
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxSum = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length ; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static List<Integer> addToArrayForm(int[] arr, int val) {
        int len = arr.length;
        int lastNum =val;
        List<Integer> ret= new LinkedList<>();
        int i = len-1;
        while(i >=0 || lastNum > 0){
            //在数组范围内
            if(i >= 0){
                lastNum+=arr[i];
            }
            ret.add(0,lastNum%10);
            lastNum/=10;
            i--;
        }
        return ret;
    }


    public int maxScoreSightseeingPair(int[] A) {

        return -1;

    }


    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len =nums.length;
        int[] result = new int[len];
        int tmp = 0;
        for (int i = 0; i < len; i++) {
            tmp += nums[i];
            result[i] = tmp;
        }
        return result;
    }


    public static boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix[0] == null){
            return false;
        }
        for(int i=0;i<matrix.length-1;i++){
            for(int j=0;j<matrix[0].length-1;j++){
                //判断当前位置的右下角是否跟当前元素相等
                if(matrix[i][j] != matrix[i+1][j+1]){
                    return false;
                }
            }
        }
        return true;

    }






    public static void main(String[] args) {
//        int i = 6;
//        int times = 2;
//        int[] a = new int[i];
//        for (int j = 0; j < i; j++) {
//            a[j] = j+1;
//        }
//        printArray(a);
//        for (int j = 0; j < times; j++) {
//            reverse(a);
//        }
//        printArray(a);

//        int[] test = new int[]{4,3,2,7,8,2,3,1};
//        List<Integer> list = findDisappearedNumbers(test);
//        for (int tmp:list){
//            System.out.print(tmp+"\t");
//        }
//        System.out.println();
//        String[] s= new String[]{
//                "testemail@leetcode.com",
//                "testemail1@leetcode.com",
//                "testemail+david@lee.tcode.com"};
//        System.out.println(numUniqueEmails(s));

//
//        int[] t = new int[]{1,3,4,5,7};
//
//        int[] result = dailyTemperatures2(t);
//        for(int i:result){
//            System.out.print(i+"\t");
//        }
//        System.out.println();
//        System.out.println(findLengthOfLCIS(t));

//        int[] arr = new int[]{9,9,9,9};
//        System.out.println(addToArrayForm(arr,1));

        int[][] arr1 = new int[][]{
                {1,2,3},
                {5,1,2},
                {9,5,1}
        };
        System.out.println(isToeplitzMatrix(arr1));
    }

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if(len == 1 || len == 2){
            return len;
        }
        int result = 0;



        return result;
    }


    public int repeatedNTimes(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
//        int len = arr.length;
//        int result = 0;
//
//        Map<Integer,Integer> map = new HashMap<>(len);
//        for (int i:A){
//            map.put(i,map.getOrDefault(i,0)+1);
//        }
//        for (int i:A){
//            if(map.get(i) > 1){
//                return i;
//            }
//        }
//
//        return result;
        // 3个数中超过半数
        int len = arr.length;
        for (int i = 0; i < len - 2; i++) {
            if (arr[i] == arr[i+1] || arr[i] == arr[i+2]) {
                return arr[i];
            }
        }

        // 上面循环没找到，那必然是最后一个数，如[1,2,3,1]
        return arr[len - 1];
    }



    public static List<Integer>  findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tmp = Math.abs(nums[i]) -1;
            nums[tmp] =-Math.abs(nums[tmp]);
        }
        for (int i = 0; i < len; i++) {
            if(nums[i] > 0){
                result.add(i+1);
            }
        }
        return result;

    }


    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();
        int len = s.length();
        for (int i = 0; i <len;i++ ){
            Character c = s.charAt(i);
            //如果栈包含改元素直接跳过
            if(stack.contains(c)){
                continue;
            }
            // 当前栈元素小于 字典数 并且 后继元素 有栈顶元素值
            while(!stack.isEmpty() && stack.peek()>c &&s.indexOf(stack.peek(),i)!=-1) {
                stack.pop();
            }
            //压入栈顶
            stack.push(c);
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

    }


    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[n << 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            result[index++] = nums[i];
            result[index++] = nums[n+i];
        }
        return result;
    }


    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int len = A.length;
        int[] result = new int[len];
        int sum = 0;
        int q = 0;
        for(int i:A){
            if(i % 2 == 0){
                sum += i;
            }
        }
        for (int i = 0; i < len; i++) {
            int val = queries[i][0],index = queries[i][1];
            if(A[index] %  2 == 0 ){
                sum -= A[index];
            }
            A[index] += val;
            if(A[index] %  2 == 0 ){
                sum += A[index];
            }
            result[q++]= sum;
        }
        return result;

    }


    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int i = 0;
        int max = 0;
        int j= nums.length-1;
        while (i < j){
            if((nums[i] -1) * (nums[j] -1) > max){
                max = (nums[i] -1) * (nums[j] -1);
            }
            if(nums[i] > nums[j]){
                j--;
            }else {
                i++;
            }
        }
        return max;
    }


    public static int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0){
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String s:emails){
            StringBuilder sb = new StringBuilder();
            String[] tmp =s.split("@");

            String str=tmp[0].replace(".","");
            if(str.contains("+")){
                str = str.substring(0,str.indexOf("+"));
            }
            sb.append(str).append("@").append(tmp[1]);
            set.add(sb.toString());
        }
        return set.size();

    }



    private static void reverse(int[] a){
        int temp = a[a.length-1];
        int i;
        for (i = a.length -1; i > 0; i--) {
            a[i] = a[i-1];
        }
        a[0] = temp;
    }

    private static void printArray(int[] a){
        for (int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }


}
