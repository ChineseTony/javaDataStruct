package com.tom.string;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wangtao
 */
public class LicenseKeyFormatting {


    public static String licenseKeyFormatting(String s, int k) {
        if (s == null || s.length() <=0 || k< 0){
            return null;
        }
        String[] strings = s.split("-");
        System.out.println(Arrays.toString(strings));
        StringBuilder sb = new StringBuilder();
        for (String tmp:strings){
            sb.append(tmp.toUpperCase());
        }
        StringBuilder result = new StringBuilder();
        int tmpLen = sb.length();
        int t = tmpLen % k ;
        int count = tmpLen / k ;
        if (t != 0){
            result.append(sb,0,t);
            if (count != 0) {
                result.append("-");
            }
        }
        for (int i = 1; i < count+1 ; i ++) {
            result.append(sb,t + (i-1) * k,t + i * k);
            if(i != count) {
                result.append("-");
            }
        }
        return result.toString();
    }



    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        if (row * col != r * c) {
            return nums;
        }
        int[][] ans = new int[r][c];
        int n = row * col;
        for (int i = 0; i < n; i++) {
            ans[i / c][i % c] = nums[i / col][i % col];
        }
        return ans;
    }

    public int maxScore2(String s) {
        int res = 0, cnt1 = 0, cnt0 = 0;
        //cnt1统计右边1的个数，同理cnt0左边0的个数
        for(int i = 0; i < s.length(); i++){
            cnt1 += s.charAt(i)-'0';
            //先统计1的个数
        }

        //由于左右区域的数至少为1，所以i不能等于len-1
        //点i分为左右两个区域
        for(int i = 0; i < s.length()-1; i++){
            if(s.charAt(i) == '0') {
                cnt0++;      //遇到01就统计，动态更新左右区域01个数
            } else {
                cnt1--;
            }
            res = Math.max(res, cnt0+cnt1);
        }
        return res;
    }


    public static int maxScore(String s) {
        if (s == null || s.length() <= 0){
            return 0;
        }
        int result = 0;
        int len = s.length();
        int index = 1;
        for (int i = index; i < len; i++) {
            String left = s.substring(0,i);
            String right = s.substring(i);
            int tmpValue = countChar(left,'0')
                    +countChar(right,'1');
            result = Math.max(result,tmpValue);
        }
        return result;

    }

    private static int countChar(String s,char c){
        if (s == null || s.length() <= 0){
            return 0;
        }
        int count = 0;
        for (char tmp:s.toCharArray()){
            if (tmp == c){
                count++;
            }
        }
        return count;
    }


    public static int minDeletionSize(String[] strs) {
        int count = 0;
        char[][] chars = new char[strs.length][strs[0].length()];
        for (int i = 0; i < strs.length; i++) {
            chars[i] = strs[i].toCharArray();
        }
        for (int i = 0; i < chars[0].length; i++) {
            for (int j = 0; j < chars.length - 1; j++) {
                if (chars[j][i] > chars[j + 1][i]) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    public static boolean kLengthApart(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1){
                result.add(i);
            }
        }
        int listLen = result.size();
        for (int i = 0; i < listLen - 1; i++) {
            if (result.get(i+1) - result.get(i) <= k){
                return false;
            }
        }
        return true;
    }


    public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxValue = 0;
        for (int i = 1; i <= n; i++) {
            int key = countNumber(i);
            map.put(key,map.getOrDefault(key,0) + 1);
            maxValue = Math.max(maxValue,map.get(key));
        }
        int count = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            if (entry.getValue() == maxValue){
                count++;
            }
        }
        return count;
    }

    private static int countNumber(int number){
        int result = 0;
        while (number != 0){
            result += number % 10;
            number = number / 10;

        }
        return result;
    }



    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(16);
        int max = 0;
        for (int i : nums){
            map.put(i,map.getOrDefault(i,0) + 1);
            max = Math.max(max,map.get(i));
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            if (entry.getValue() == max){
                result.add(entry.getKey());
            }
        }
        int retValue = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < result.size(); i++) {
            int tmpValue = result.get(i);
            int left = 0;
            int right = len -1;
            for (int j = 0; j < len; j++) {
                if (tmpValue == nums[j]){
                    left = j;
                    break;
                }
            }
            for (int j = right; j >= 0; j--) {
                if (tmpValue == nums[j]){
                    right = j;
                    break;
                }
            }
            retValue = Math.min(retValue,right-left+1);
        }
        return retValue;
    }



    public int findShortestSubArray2(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>(16);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;
    }


    public static void main(String[] args) {
//        String s = "2-5g-3-J";
        String s = "5F3Z-2e-9-w";
        int k = 4;
        System.out.println(licenseKeyFormatting(s,k));


        int[][] nums = {{1, 2},{3, 4}};
        int r = 2, c = 2;
        int[][] result = matrixReshape(nums,r,c);
        System.out.println(Arrays.toString(result[0]));
        System.out.println(Arrays.toString(result[1]));

        System.out.println(maxScore("011101"));

        String[] strings = new String[]{"c","d"};

        System.out.println(minDeletionSize(strings));

    }
}
