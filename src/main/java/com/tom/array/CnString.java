package com.tom.array;



import java.util.ArrayList;
import java.util.List;

/**
 * 从字符串中取出长度为n的子集合
 */
public class CnString {

    private  static List<String> result = new ArrayList<>();


    public static List<String> getResult(String str, int n){
        List<Character> tmp = new ArrayList<>();
        dfs(new StringBuffer(str),n,0,tmp);
        return  result;
    }

    private static void dfs(StringBuffer str,int n,int index,
                            List<Character> tmp){
        if (tmp.size() == n){
            StringBuffer sb = new StringBuffer();
            for (char c:tmp){
                sb.append(c);
            }
            result.add(sb.toString());
            return;
        }
        for (int i = 0; index <= n && i <  str.length(); i++) {
            if (tmp.contains(str.charAt(i))){
                continue;
            }
            tmp.add(str.charAt(i));
            dfs(str,n, index+1,tmp);
            tmp.remove(tmp.size() -1);
        }
    }

    public static void main(String[] args) {
        String s =  "abcd";
        getResult(s,3).forEach(System.out::println);

    }
}
