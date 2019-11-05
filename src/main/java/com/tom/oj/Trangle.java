package com.tom.oj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTao
 */
public class Trangle {

    private Trangle(){

    }

    public static int getMin(List<List<Integer>> trangels){
        if (trangels.size() == 0 || trangels.get(0).size() == 0){
            return 0;
        }
        int length = trangels.size();
        for (int i = length-2; i >= 0; i--) {
            //找到
            for (int j = trangels.get(i).size()-1; j >= 0 ; j--) {
                int min = Math.min(trangels.get(i+1).get(j+1),
                        trangels.get(i+1).get(j));
                min += trangels.get(i).get(j);
                trangels.get(i).set(j,min);
            }
        }
        return trangels.get(0).get(0);
    }

    public static int getMinValue(List<List<Integer>> trangels){
        if (trangels.size() == 0 || trangels.get(0).size() == 0){
            return 0;
        }
        int sum = 0;
        String path = "";
        return dfs(trangels,0,0,sum,path);
    }

    private static int dfs(List<List<Integer>> trangels, int i, int j, int sum, String path) {
        if (i == trangels.size() - 1){
            path += trangels.get(i).get(j)+"-->";
            sum += trangels.get(i).get(j);
            System.out.println(path);
            return sum;
        }
        path += trangels.get(i).get(j)+"-->";
        sum += trangels.get(i).get(j);
        dfs(trangels,i,j+1,sum,path);
        dfs(trangels,i+1,j+1,sum,path);
        return 0;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        System.out.println(Trangle.getMin(lists));
    }
}
