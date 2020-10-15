package com.tom.map;

import java.util.*;

public class TreeCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.valueOf(scanner.nextLine());
        Map<String,Integer> map = new HashMap<>(num);
        int len = num;
        while (num -- > 0){
            String s1 = scanner.nextLine();
            if (!map.containsKey(s1)){
                map.put(s1,1);
            }else {
                map.put(s1,map.get(s1)+1);
            }
        }

        scanner.close();
        List<String> list = new ArrayList<>();
        for (Map.Entry<String,Integer> entry: map.entrySet()){
            list.add(entry.getKey());
        }
        list.sort(String::compareTo);

        for (String s: list){
            System.out.printf("%s %.4f%%\n" ,s,map.get(s) * 100 / (double)len);
        }

    }
}
