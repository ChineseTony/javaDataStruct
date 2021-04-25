package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tom
 */
public class MaxIceCream {


    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int len = costs.length;
        int result = 0;
        int i = 0;
        while (i < len ){
            if (costs[i] <= coins){
                coins -= costs[i++];
                result++;
            }else {
                break;
            }
        }
        return result;
    }

    public static List<String> tmpList = new ArrayList<>();
    private static Set<String> all = new HashSet<>();
    public static String[] permutation(String s) {
        permute(s,new StringBuilder(),new ArrayList<>());
        for(String tmp:tmpList){
            System.out.println(tmp);
        }
        return tmpList.toArray(new String[0]);
    }

    private static void permute(String s, StringBuilder sb
            , List<Integer> indexList){
        if (sb.length() == s.length()){
            if (!all.contains(sb.toString())){
                tmpList.add(sb.toString());
                all.add(sb.toString());
            }
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (indexList.contains(i)){
                continue;
            }
            indexList.add(i);
            sb.append(s.charAt(i));
            permute(s,sb,indexList);
            sb.deleteCharAt(sb.length()-1);
            indexList.remove(indexList.size()-1);
        }
    }

    public static void main(String[] args) {
        permutation("qeq");
    }
}
