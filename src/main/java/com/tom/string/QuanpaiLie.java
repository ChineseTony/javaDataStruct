package com.tom.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuanpaiLie {


    public boolean checkInclusion(String s1, String s2) {
        List<String> result = quanPaiLie(s1);

        for (String s:result){
            if (s2.contains(s)){
                return true;
            }
        }
        return false;

    }

    public List<String> quanPaiLie(String tmp){
        List<String> result = new ArrayList<>();
        if (tmp == null || tmp.length() == 0){
            return result;
        }
        char[] tmpArr = tmp.toCharArray();
        travel(result,tmpArr,0);
        Collections.sort(result);
        return result;
    }

    private void travel(List<String> tmp,char[] tmpArr,int start){
        //递归出口
        if (start == tmpArr.length - 1){
            String result = String.valueOf(tmpArr);
            // 排列中不包含
            if (tmp.indexOf(result) < 0){
                tmp.add(result);
            }
        }
        for(int i = start; i < tmpArr.length; i++) {
            //交换位置左边
            char temp = tmpArr[start];
            tmpArr[start] = tmpArr[i];
            tmpArr[i] = temp;

            travel(tmp, tmpArr, start+1);

            temp = tmpArr[start];
            tmpArr[start] = tmpArr[i];
            tmpArr[i] = temp;
        }
    }


    public static void main(String[] args) {
        QuanpaiLie q = new QuanpaiLie();
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i+1);
        }
        List<String> result = q.quanPaiLie(sb.toString());
        for (String s : result) {
            System.out.println(s);
        }

    }
}
