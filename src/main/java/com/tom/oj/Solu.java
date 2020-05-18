package com.tom.oj;


import sun.nio.cs.ext.MacArabic;

import java.util.*;


/**
 * @author WangTao
 */
public class Solu {



    public int maxProduct(int[] nums) {
       /* if(nums == null || nums.length == 0){
            return -1;
        }
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int tmp = nums[i];
            if (tmp > max){
                max = tmp;
            }
            for (int j = i+1; j < len; j++) {
                tmp = tmp * nums[j];
                if (tmp > max){
                    max = tmp;
                }
            }

        }
        return max;*/

        int n = nums.length;

        int a = 1, b = 1;
        int maxVal =  Integer.MIN_VALUE;

        for (int i = 0; i < n; ++i) {
            int aa = a * nums[i];
            int bb = b * nums[i];

            a = Math.min(nums[i], Math.min(aa, bb));
            b = Math.max(nums[i], Math.max(aa, bb));

            maxVal = Math.max(maxVal, b);
        }
        return maxVal;
    }


    public int[] replaceElements(int[] arr) {
/*        if(arr == null || arr.length == 0){
            return new int[0];
        }
        int len = arr.length;
        int[] result = new int[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i+1; j < len ; j++) {
                if(arr[j] > max){
                    max = arr[j];
                }
            }
            result[--index]= max;
        }
        result[index] = -1;
        return result;*/
        //从后往前遍历  max保存 左边最大的元素
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            if (temp > max) {
                max = temp;
            }
        }
        return arr;

    }



    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int result = 0;
        if(arr1 == null || arr1.length ==  0){
            return result;
        }
        if(arr2 == null || arr2.length ==  0){
            return result;
        }
        int len1 = arr1.length;
        int len2 = arr2.length;
        for (int i = 0; i < len1; i++) {
            int tmp = arr1[i];
            boolean flag = true;
            for (int j = 0; j < len2; j++) {
                if(Math.abs(tmp-arr2[j]) <= d){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result++;
            }
        }
        return result;
    }


    public int findLucky(int[] arr) {
        if(arr == null || arr.length ==  0){
            return -1;
        }
        Map<Integer,Integer> map = new HashMap<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else {
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        int result = -1;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int val = entry.getValue();
            if(key == val){
                if (key > result){
                    result = key;
                }
            }
        }
        return result;
    }







    public int subtractProductAndSum(int n) {
        int sum=0;
        int total = 1;
        while(n != 0){
            int digit = n % 10;
            sum += digit;
            total *= digit;
            n = n / 10;
        }
        return total - sum;

    }

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right;i++ ){
            if (isZichuShu(i)){
                result.add(i);
            }
        }
        return result;

    }

    private static   boolean isZichuShu(int n) {
        if(n < 10){
            return true;
        }
        int tmp = n;
        boolean flag = true;
        while(n != 0){
            int digit = n % 10;
            if(digit == 0 || tmp % digit != 0){
                flag = false;
                break;
            }
            n = n / 10;
        }
        return flag;

    }


    public static int[] createTargetArray(int[] nums, int[] index) {
        if(nums == null || nums.length == 0){
            return nums;
        }
        int len = nums.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = -1;
        }
        for (int i = 0; i < len; i++) {
            int indexValue = index[i];
            int val = nums[i];
            if(result[indexValue] == -1){
                result[indexValue] = val;
            }else {
//                for循环移动数据
                int k = indexValue;
                int j = indexValue;
                while (j < len && result[indexValue] != -1){
                    j++;
                }
                if(j == len){
                    j --;
                }
                for (int p = j; p > k; p--) {
                    result[p] = result[p-1];
                }
                result[k] = val;
            }
        }
        return result;
    }


    public List<String> buildArray(int[] target, int n) {

        int len = target.length;
        int start = 1;
        List<String> result = new ArrayList<>();
        for (int j = 0; j < len; j++) {
            while(start < target[j]){
                result.add("Push");
                result.add("Pop");
                start++;
            }
            if(start == target[j]){
                result.add("Push");
                start++;
            }
        }
        return result;

    }



    public int balancedStringSplit(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int result = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'R') {
                count++;
            }else{
                count--;
            }
            if(count == 0){
                result++;
            }
        }
        return result;
    }

    public boolean CheckPermutation(String s1, String s2) {
        if(s1 == null && s2 == null){
            return true;
        }else if (s1 == null || s2 == null){
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        int sum1 = 0;
        int sum2 = 0;
        if(len1 != len2){
            return false;
        }else {

            for (int i = 0; i < len1; i++) {
                sum1 += s1.charAt(i);
                sum2 += s2.charAt(i);
            }

        }
        return sum1 == sum2;
    }

    public static String compressString2(String s) {
        if(s== null || s.isEmpty() ){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        int times = 1;
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (c == s.charAt(i)) {
                times++;
            }else {
                sb.append(c).append(times);
                c = s.charAt(i);
                times = 1;
            }

        }
        //添加最后一个元素
        sb.append(c).append(times);
        return sb.length() < s.length() ? sb.toString() : s;
    }

    public String reverseLeftWords(String s, int n) {
        if(s == null || s.length() == 0){
            return s;
        }
        int len = s.length();
        if(n % len == 0){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < n + len ; i++) {
            sb.append(s.charAt(i % len));
        }
        return sb.toString();
    }


    public static int subarraySum(int[] nums, int k) {
        int times = 0;
        if(nums == null || nums.length == 0){
            return times;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++){
                sum += nums[j];
                if(sum == k){
                    times++;
                }
            }
        }
        return times;
    }


    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        if(candies == null || candies.length == 0){
            return result;
        }
        int max = Arrays.stream(candies).max().getAsInt();
        for (int i = 0; i < candies.length; i++) {
            if(candies[i] + extraCandies >= max){
                result.add(true);
            }else {
                result.add(false);
            }
        }
        return result;
    }

    public int[] decompressRLElist(int[] nums) {
//        if(nums == null || nums.length == 0){
//            return nums;
//        }
//        List<Integer> tmp = new ArrayList<>(nums.length * 2);
//        for (int i = 0; i < nums.length; i = i + 2) {
//            int frequency = nums[i];
//            int val = nums[i+1];
//            for (int j = 0; j < frequency; j++) {
//                tmp.add(val);
//            }
//        }
//        int size = tmp.size();
//        int[] result = new int[size];
//        for (int i = 0; i < size; i++) {
//            result[i] = tmp.get(i);
//        }
//        return result;


        int len=0;
        //先求出长度
        for(int i=0;i<=nums.length-2;i+=2){
            len+=nums[i];
        }
        int[] ans=new int[len];
        int count=0;
        for(int i=0;i<nums.length;){
            int freq=nums[i++];
            int val=nums[i++];
            while(freq!=0){
                ans[count++]=val;
                freq--;
            }
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {

        return -1;

    }

    public static  int[][] findContinuousSequence(int target) {
        int[] arr = new int[target];
        for (int i = 1; i < target; i++) {
            arr[i] = i;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= (target - 1) / 2; i++) {
            int j = i;
            int sum = 0;
            List<Integer> tmp = new ArrayList<>();
            while (sum < target){
                sum += j;
                tmp.add(j);
                j ++;
                if(sum == target){
                    result.add(tmp);
                    break;
                }
            }
        }
        int size = result.size();
        int[][] a = new int[size][];
        for (int i = 0; i < size; i++) {
            List<Integer>  t = result.get(i);
            a[i] = new int[t.size()];
            for (int j = 0; j < t.size(); j++) {
                a[i][j] = t.get(j);
            }
        }

        return a;
    }

    public static  int[][] findContinuousSequence2(int target) {


        int[][] a = new int[10][];

        return a;
    }


    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     * 输入：[3,4,5,1,2]
     * 输出：1
     * @param numbers
     * @return
     */
    public static int minArray(int[] numbers) {
        if(numbers == null || numbers.length <= 0){
            return -1;
        }
        int left = 0;
        int right = numbers.length - 1;
        if(numbers[left] < numbers[right]){
            return numbers[left];
        }else {
            int i;
            for (i = right; i > left ; i--) {
                if(numbers[i-1] > numbers[i]){
                    break;
                }
            }
            return numbers[i];
        }
    }

    public static int minArray2(int[] numbers) {
        if(numbers == null || numbers.length <= 0){
            return -1;
        }
        int left = 0;
        int right = numbers.length - 1;
        while (left < right){
            int mid = (right - left) / 2 + left;
            if(numbers[right] > numbers[mid]){
                left = mid+1;
            }else if(numbers[right] < numbers[mid]){
                right = mid;
            }else {
                right --;
            }
        }
        return numbers[left];
    }

    public int[] printNumbers(int n) {
        if(n <= 0){
            return new int[0];
        }
        int size = (int)Math.pow(10,n)-1;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i+1;
        }
        return result;
    }


    public static String compressString(String s) {
        if(s== null || s.isEmpty()  ){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int size = s.length();
        int times = 1;
        int i = 0;
        int j = 1;
        while (i < size && j < size) {
//            if(i == size -1 || s.charAt(i) != s.charAt(j)){
            if(s.charAt(i) != s.charAt(j)){
              sb.append(s.charAt(i)).append(times);
              i = j;
              times = 1;
            }else {
                times++;
            }
            j++;
        }
        if(j >= size){
            sb.append(s.charAt(i)).append(times);
        }
        return sb.length() < s.length() ? sb.toString() : s;
    }

    public static void main(String[] args) {
//        String s ="aabccccca";
//        System.out.println(compressString(s));
//        int[][] arr = findContinuousSequence(9);
//
//
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j]+"\t");
//            }
//            System.out.println();
//        }
//

        int[] arr={0,1,2,3,4};
        int[] s={0,1,2,2,1};
        int[] ar= createTargetArray(arr,s);
        for (int tmp:ar){
            System.out.print(tmp + "\t");
        }


    }
}
