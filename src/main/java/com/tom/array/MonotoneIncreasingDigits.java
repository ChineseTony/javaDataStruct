package com.tom.array;

public class MonotoneIncreasingDigits {

    private MonotoneIncreasingDigits(){

    }


    /**
     *   从右向左扫描数字，若发现当前数字比其左边一位（较高位）小，
     *   把其左边一位数字减1，并将该位及其右边的所有位改成9
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigits2(int n) {
        String s = String.valueOf(n);
        int length = s.length();
        char[] chars = s.toCharArray();
        int flag = length;
        for (int i = length - 1; i >= 1; i--) {
            if (chars[i] < chars[i - 1]) {
                flag = i;
                chars[i - 1]--;
            }
        }
        //把右边的元素，全部转成9
        for (int i = flag; i < length; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(new String(chars));

    }

    //时间复杂度过高
    public static int monotoneIncreasingDigits(int N) {
        for (int i = N; i >= 1; i--) {
            if (isIncreasing(String.valueOf(i))){
                return i;
            }
        }
        return -1;
    }

    private static boolean isIncreasing(String s){
        if (s == null || s.length() == 0){
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len-1; i++) {
            if (s.charAt(i) > s.charAt(i+1)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(963856657));
        System.out.println(monotoneIncreasingDigits2(963856657));

    }
}
