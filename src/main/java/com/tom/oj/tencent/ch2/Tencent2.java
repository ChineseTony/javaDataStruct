package com.tom.oj.tencent.ch2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tencent2 {

    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        while (n-- > 0){
            String t = scanner.next();
            String b = scanner.next();
//            System.out.println(t+"-->"+b);
            if (isSame(t,b)){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
        new Tencent2().permutation("qwe");
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static boolean isSame(String a,String b){
        if (a.length() != b.length()){
            return false;
        }
        int len = a.length();
        if (len % 2 != 0){
            for (int i = 0; i < len; i++) {
                if (a.charAt(i) != b.charAt(i)){
                    return false;
                }
            }
            return true;
        }else {
            String a1 = a.substring(0,len/2);
            String a2 = a.substring(len/2,len);
            String b1 = b.substring(0,len/2);
            String b2 = b.substring(len/2,len);
//            System.out.println(a1+"->"+a2);
            return (isSame(a1,b1) && isSame(a2,b2))
                    || (isSame(a1,b2) && isSame(a2,b1));
        }
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length <= 0){
            return 0;
        }
        int len = nums.length;
        int left = 0;
        for (int i =0; i < len; i++) {
            if (nums[i] != val){
                nums[left] = nums[i];
                left++;
            }
        }
        return left;

    }


    private static List<String> result = new ArrayList<>();

    public String[] permutation(String s) {
        premu(s,new StringBuilder());
        return result.toArray(new String[0]);
    }

    private void premu(String s,StringBuilder sb){
        if (sb.length() == s.length()){
            result.add(new String(sb));
            return;
        }
        for (int i = 0; i < s.length() ; i++) {
            //去除路径重复的字母
            if (sb.indexOf(""+s.charAt(i)) != -1){
                continue;
            }
            sb.append(s.charAt(i));
            premu(s,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
