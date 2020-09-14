package com.tom.map;


import java.util.*;

/**
 * @author WangTao
 *
 * 1
 * abcd abed
 * abed 1 2
 */
public class PhoneCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.valueOf(scanner.nextLine());
        Map<String,Integer> map = new HashMap<>(num);
        while (num -- > 0){
            String s1 = scanner.next();
            String s2 = scanner.next();
            if (!map.containsKey(s1)){
                map.put(s1,1);
            }else {
                map.put(s1,map.get(s1)+1);
            }
            if (!map.containsKey(s2)){
                map.put(s2,1);
            }else {
                map.put(s2,map.get(s2)+1);
            }
        }
        scanner.close();
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                //根据 value排序 再根据key排序
                int tmp = o1.getValue().compareTo(o2.getValue());
                //key 相同
                if (tmp == 0){
                    //根据String 字典排序
                    return o1.getKey().compareTo(o2.getKey());
                }else {
                    return tmp;
                }
            }

        });
        int count = 0;
        int len = list.size();
        int max = list.get(len-1).getValue();
        for (int i = len-1; i >= 0; i--) {
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry.getValue()== max){
                count++;
            }
        }
        for ( Map.Entry<String, Integer> entry : list) {
            if (entry.getValue()== max){
                System.out.printf("%s %d",entry.getKey(),entry.getValue());
                break;
            }
        }

        if (count > 1){
            System.out.println(" "+count);
        }

    }
}
