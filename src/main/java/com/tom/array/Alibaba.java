package com.tom.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * 3 4
 * n k 7
 *
 *
 * 3 3 1
 * 1 2 3 1
 * 2 3 3 1
 * 1 3 10 5
 */
public class Alibaba {

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int m = scanner.nextInt();
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        String val = scanner.nextLine();
//        String[] tmp = val.split(" ");
//        char[] arr = new char[n];
//        int len = n;
//        int[] chars = new int[26];
//        int charLen = 0;
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < len; i++) {
//            char tmpChar = tmp[i].charAt(0);
//            arr[i] = tmpChar;
//            if (tmpChar >= 'a' && tmpChar <= 'z') {
//                chars[tmpChar - 'a'] ++;
//                charLen ++ ;
//            }else {
//                //数字
//                list.add(tmpChar - '0');
//            }
//        }
//        Collections.sort(list);
//        List<String> result = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//        sb.append(list.get(0));
//        list.remove(0);
//        int times = 0;
//        StringBuilder tmpChars = new StringBuilder();
//        for (int i = 0; i < 26; i++) {
//            if (chars[i] != 0){
//                tmpChars.append((char)(i + 'a'));
//                times++;
//                chars[i]--;
//            }
//            if (times > 2){
//                break;
//            }
//        }
//
//        //需要从字符取2个 数字取一个
//        int i = 0;
////        需要从数字或者字符取的数字
//        int j = m - 3;
//        if (j == 0) {
//            String tmpValue = sb + tmpChars.toString();
//            result.add(tmpValue);
//            char c = ' ';
//            for (int k = 0; k < list.size(); k++) {
//                c = (char)(list.get(k) + '0');
//                result.add(c + tmpChars.toString());
//            }
//        }else {
//            int t = 0;
////            数字长度
//            if (j <= list.size()){
//                char c = ' ';
//                for (int k = 0; k < j; k++) {
//                    c = (char)(list.get(k) + '0');
//                    result.add(c + tmpChars.toString());
//                }
//            }
//        }
//        for(String tmpValue:result){
//            System.out.println(tmpValue);
//        }
//    }


    public static List<String> random(List<Character> list,int n){
        StringBuilder stringBuilder = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
            random(ans,stringBuilder,n,i,list);
            if (stringBuilder.length()>0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
        return ans;

    }

    private static void random(List<String> ans, StringBuilder stringBuilder,
                               int n, int i,List<Character> list) {
        if(stringBuilder.length() == n){
            ans.add(stringBuilder.toString());
            return;
        }
        if(i == list.size()-1){
            return;
        }
        if(i<list.size()-1) {
            random(ans, stringBuilder.append(list.get(i + 1)), n, i + 1, list);
        }
        if(stringBuilder.length()>0){
            random(ans,stringBuilder.deleteCharAt
                    (stringBuilder.length()-1),n,i+1,list);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] s1 = s.split(" ");
        int m=0,n=0;
        m = Integer.parseInt(s1[0]);
        n = Integer.parseInt(s1[1]);
        String s2 = in.nextLine();
        char[] chars = new char[n];
        String[] s3 = s2.split(" ");
        for (int i = 0; i < n; i++) {
            chars[i] = s3[i].charAt(0);
        }
        List<String> ans = new ArrayList<>();
        List<Character> nums = new ArrayList<>();
        List<Character> lowChar = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(chars[i] >='0' && chars[i] <= '9') {
                nums.add(chars[i]);
            }else {
                lowChar.add(chars[i]);
            }
        }
        Collections.sort(nums);
        Collections.sort(lowChar);
        for (int i = 1; i < m-1 && i<=nums.size() ; i++) {
            List<String> numss = random(nums,i);
            List<String> lowcharss = random(lowChar,m-i);
            for(String num:numss){
                for(String low:lowcharss){
                    ans.add(num+low);
                }
            }
        }
        Collections.sort(ans);
        if(ans.size()>666666){
            for (int i = 0; i < 666666; i++) {
                System.out.println(ans.get(i));
            }
        }else {
            for(String ss:ans){
                System.out.println(ss);
            }
        }
    }
}
