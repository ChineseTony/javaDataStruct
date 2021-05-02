package com.tom.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tom
 */
public class GroupThePeople {


    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        if (groupSizes == null || groupSizes.length <=0){
            return result;
        }
        int len = groupSizes.length;
        Map<Integer,List<Integer>> map = new HashMap<>(len);
        for (int i = 0; i < len ; i++) {
            List<Integer> tmp;
            if (!map.containsKey(groupSizes[i])){
               tmp = new ArrayList<>();
            }else {
                tmp = map.get(groupSizes[i]);
            }
            tmp.add(i);
            map.put(groupSizes[i],tmp);
        }
        for (Map.Entry<Integer,List<Integer>> entry:map.entrySet()){
            List<Integer> value = entry.getValue();
            int times = entry.getKey();
            int tmp = value.size() / times;
            int index = 0;
            while (index < tmp){
                result.add(new ArrayList<>(value.subList(index*times,
                        (index+1) * times)));
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] tmp = new int[]{3,3,3,3,3,1,3};
        List<List<Integer>> lists = groupThePeople(tmp);
        for (List<Integer> tmpList:lists){
            System.out.println(Arrays.toString(tmpList.toArray(new Integer[0])));
        }
    }
}
