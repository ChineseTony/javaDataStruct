package com.tom.array;

import java.util.*;

/**
 * @author wangtao .
 * @createTime on 2020/10/9
 * 
 * (1,4,9,16,25)
 *
 * 13  (9+4 = 13 1+......+1 = 13 ) return 4 * 9找出长度最短并且最大值是最大的 返回列表乘积
 * 12 (4 + 4 + 4 = 12 9+1+1+1 1+++.....+1 = 12)  return 4*4*4 = 16
 *
 * 解决思路 暴力遍历所有肯能的路径
 *
 */

public class ABCChian {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> result = new ArrayList<>();
        int inValue = scanner.nextInt();
        cal(inValue,result);
        result.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1.size() > o2.size()){
                    return 1;
                }else if (o1.size() < o2.size()){
                    return -1;
                }else {
                    //todo 解析长度相等的
                   return 0;
                }
            }
        });
        for (List<Integer> tmp:result){
            System.out.print(tmp+"\t");
        }

        List<Integer> min = result.get(0);
        int val = 1;
        for(int t:min){
            val *= t;
        }
        System.out.println(val);

    }

    private static void cal(int n,List<List<Integer>> tmp){
        int times = getTmp(n,n);
        for (int i = times; i > 0; i--) {
            int j = n;
            List<Integer> result = new ArrayList<>();
            while (j > 0){
                int tmpVal = getTmp(j,i);
                int multiplication = tmpVal * tmpVal;
                result.add(multiplication);
                j = j - multiplication;
            }
            tmp.add(result);

        }
    }

    private static int getTmp(int n,int times){
        int i=1;
        while (i * i <= n){
            i ++;
            if (i > times){
                return i == 1 ? 1 : (i -1);
            }
        }
        return i == 1 ? 1 : (i -1);
    }
}
