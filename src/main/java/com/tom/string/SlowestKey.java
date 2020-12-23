package com.tom.string;

public class SlowestKey {

    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int len = releaseTimes.length;
        int max = 0;
        char result = ' ';
        for (int i = 0; i < len; i++) {
            int tmp = i == 0 ? releaseTimes[i] :
                    releaseTimes[i] - releaseTimes[i-1] ;
            if (tmp > max){
                max = tmp;
                result = keysPressed.charAt(i);
            }else if (tmp == max && keysPressed.charAt(i) > result){
                result = keysPressed.charAt(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{19,22,28,29,66,81,93,97};
        String str = "fnfaaxha";
        System.out.println(slowestKey(nums,str));
    }
}
