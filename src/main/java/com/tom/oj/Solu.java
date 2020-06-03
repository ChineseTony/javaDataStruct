package com.tom.oj;


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


    public static int[] smallerNumbersThanCurrent(int[] nums) {
       /* if(nums == null || nums.length <= 0){
            return new int[0];
        }
        int len = nums.length;
        int[] result = new int[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            int tmp = nums[i];
            int count = 0;
            for (int j = 0 ; j < len; j++) {
                if(nums[j] < tmp && i != j){
                    count++;
                }
            }
            result[index++] = count;
        }
        return result;*/

        int[] bucket = new int[101];
        for(int i:nums) {
            bucket[i]++;
        }

        //桶中间处理，积累前面的桶结果
        for(int i=1;i<101;i++) {
            bucket[i] += bucket[i - 1];
        }

        for(int i=0;i<nums.length;i++) {
            nums[i] = nums[i] > 0 ? bucket[nums[i] - 1] : 0;
        }

        return nums;
    }




    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        Set<Character> set = new HashSet<>();
        int left = 0,right = 0;
        int result = 0;
        while (left < len && right < len){
            if (!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
                result = Math.max(result,right-left);
            }else {
                set.remove(s.charAt(left));
                left++;
            }

        }
        return result;
    }


    public int lengthOfLongestSubstring2(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int[] chars = new int[256];
        int left = 0;
        int result = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            left = Math.max(left,chars[c]);
            result = Math.max(result,i-left+1);
            chars[c] = i+1;
        }

        return result;
    }



    public boolean validPalindrome(String s) {
        if(s ==null || s.length() <= 0){
            return false;
        }
        int len = s.length();
        if(len == 1){
            return true;
        }
        int i =0,j=len-1;
        while (i < j){
            if(s.charAt(i) != s.charAt(j)){
                return validString(s,i+1,j) || validString(s,i,j-1);
            }
            i++;
            j--;
        }
        return true;

    }


    private boolean validString(String s,int i,int j){
        while (i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    //滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return nums;
        }
        int len = nums.length;
        int[] result = new int[len-k+1];
        int j = 0;
        //保存下标
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            //窗口超出k
            if(!queue.isEmpty() &&  i - queue.peek() >= k){
                queue.poll();
            }
            //删除窗口的最小值 不可能是最大值 所有移除
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offer(i);
            if(i >= k - 1) {
                result[j++] = nums[queue.peek()];
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

    public static int[][] transpose(int[][] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int R = A.length, C = A[0].length;
        int[][] ans = new int[C][R];
        for (int r = 0; r < R; ++r){
            for (int c = 0; c < C; ++c) {
                ans[c][r] = A[r][c];
            }
        }
        return ans;
    }

    public int countNegatives(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int R = grid.length, C = grid[0].length;
        int count = 0;
        for (int r = 0; r < R; r++){
            for (int c = 0; c < C; c++) {
                if(grid[r][c] < 0){
                    count++;
                }
            }
        }
        return count;
    }

    public int countNegatives2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int count = 0;
        for (int r = 0; r < m; r++){
            int left = 0;
            int n = grid[r].length;
            int right = n - 1;
            while (left <= right){
                int mid = left +  (right -left) / 2;
                if(grid[r][mid] < 0){
                    //全部为负数
                    if (mid == 0) {
                        count += n;
                        break;
                    }
                    if(grid[r][mid-1] >= 0){
                        count += n-mid;
                        break;
                    }else {
                        right = mid-1;
                    }

                }else{
                    left = mid+1;
                }
            }
        }
        return count;
    }


    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0){
            return false;
        }
        int i =0;
        int j = matrix.length-1;
        while (i < matrix[0].length && j >= 0){
            if(matrix[j][i] > target){
                j--;
            }else if(matrix[j][i] < target){
                i++;
            }else {
                return true;
            }
        }
        return false;
    }


    public static String decodeString(String s) {
        if(s == null || s.length() ==0){
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int len = chars.length;
        int i = 0;
        while (i < len) {
            if(chars[i] == ']'){
                StringBuilder sb = new StringBuilder();
                char c = stack.pop();
                // [] 号里面的数字
                while (c != '['){
                    sb.append(c);
                    c = stack.pop();
                }
                //循环的次数
                StringBuilder number = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())){
                    number.append(stack.pop());
                }
                int times = Integer.parseInt(number.reverse().toString());
                while(times-- > 0){
                    for (int j=sb.length()-1;j >= 0; j--){
                        stack.push(sb.charAt(j));
                    }
                }
            }else {
                stack.push(chars[i]);
            }
            i++;
        }
        StringBuilder stringBUilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBUilder.append(stack.pop());
        }
        return stringBUilder.reverse().toString();

    }

    public String removeOuterParentheses(String s) {
        if(s == null || s.length() <= 0){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == ')'){
                stack.pop();
            }
            if (!stack.isEmpty()){
                sb.append(s.charAt(i));
            }
            if (s.charAt(i) == '('){
                stack.push('(');
            }
        }
        return sb.toString();

    }

    public static int countCharacters2(String[] words, String chars) {
        int result = 0;
        char[] arr = new char[26];
        for(char c : chars.toCharArray()){
            arr[c-'a']++;
        }
        for (String tmp:words){
            boolean flag = true;
            char[] str = Arrays.copyOf(arr,arr.length);
            for(Character c : tmp.toCharArray()){
                if (str[c-'a'] <= 0){
                    flag=false;
                    break;
                }
                str[c-'a']--;

            }
            if (flag){
                result +=tmp.length();
            }
        }
        return result;

    }

    public static int countCharacters(String[] words, String chars) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<> (chars.length());

        for(char c:chars.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for (String tmp:words){
            boolean flag = true;
            for (char c : tmp.toCharArray()){
                if(!map.containsKey(c) ){
                    flag=false;
                    break;
                }else if(charCount(tmp,c) > map.get(c)){
                    flag=false;
                    break;
                }
            }
            if(flag){
                result += tmp.length();
            }
        }
        return result;
    }

    private static int charCount(String s,char c ){
        int count = 0;
        for (char  t : s.toCharArray()){
            if (c == t){
                count++;
            }
        }
        return count;
    }

    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int tmp = 0;
            for (int j = i; j < len; j++) {
                tmp += nums[j];
                if (tmp > max){
                    max = tmp;
                }
            }
        }
        return max;

    }

    public int maxSubArray2(int[] nums) {
        //动态规划
        if(nums == null || nums.length == 0){
            return 0;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sum <= 0) {
                sum = num;
            } else {
                sum += num;
            }
            //更新
            max = Math.max(max, sum);
        }
        return max;

    }

//    public int findNthDigit(int n) {
////        前10个数
//        if(n < 10){
//            return n;
//        }
//
//
//    }

    public static String minNumber(int[] nums) {
        if(nums == null || nums.length == 0){
            return "0";
        }
        String[] tmp = new String[nums.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = String.valueOf(nums[i]);
        }
//        字符串字典序列  0 1
        Arrays.sort(tmp,(x,y) -> (x+y).compareTo(y+x));
        for (String s : tmp){
//            保留前置0
            sb.append(s);
        }
        return sb.toString();
    }

    public static int strToInt(String str) {
       if (str == null || str.length() == 0) {
            return 0;
       }
       char[] chars = str.toCharArray();
       int len = chars.length;
       int i = 0;
       while (i < len && chars[i] == ' ') {
            i++;
       }
       //全部是空格
       if (i == len) {
           return 0;
       }
       boolean flag= true;
       if(chars[i] == '-' || chars[i] == '+') {
           if(chars[i] == '-'){
               flag = false;
           }
           i++;
       }
       if (i >= len || !Character.isDigit(chars[i])) {
               return 0;
       }
       long sum = 0L;
       while (i < len && Character.isDigit(chars[i])) {
           //求和操作
           sum = sum * 10 +  (chars[i] - '0');
           //判断溢出
           if(flag == true && sum > Integer.MAX_VALUE){
               return Integer.MAX_VALUE;
           }else if (flag == false && -sum < Integer.MIN_VALUE){
               return Integer.MIN_VALUE;
           }
           i++;
       }
       return flag ? (int)sum : (int) -sum;
    }

    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
//        int len = nums.length;
//        Map<Integer,Integer> map = new HashMap<>(len);
//        for(int i:nums){
//            map.put(i,map.getOrDefault(i,0)+1);
//        }
//        int avg = len / 2;
//        int result = -1;
//        for(Map.Entry<Integer, Integer> entry:map.entrySet()){
//            if(entry.getValue() > avg){
//                result = entry.getKey();
//            }
//        }
//        return result;
        //投票法
        int a = nums[0], count = 1;
        for(int i = 1; i < nums.length; ++i){
            if(a == nums[i]){
                count++;
            }else{
                count--;
            }
            if(count < 0){
                a = nums[i];
                count = 1;
            }
        }
        return a;

    }

    public String reverseWords(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        String tmp = s.trim();
        if(tmp.length() == 0){
            return tmp;
        }
        String[] strings = tmp.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i=strings.length-1;i >=0;i--){
            sb.append(strings[i]);
            if(i != 0){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(strToInt("-2000000"));
    }
}
