package com.tom.string;




import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tom
 */
public class FrequencySort {

    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>(16);
        for(char c:s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Freq> freqList = new ArrayList<>();
        for (Map.Entry<Character,Integer> entry:map.entrySet()){
            freqList.add(new Freq(entry.getKey(),entry.getValue()));
        }
        Collections.sort(freqList, (o1, o2) -> Integer.compare(o2.count,o1.count));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < freqList.size(); i++) {
            Freq freq = freqList.get(i);
            while (freq.count-- > 0){
                sb.append(freq.c);
            }
        }
        return sb.toString();

    }

    class Freq  {
        public Freq(char c, int count) {
            this.c = c;
            this.count = count;
        }

        char c;
        int count;

    }


    public static void main(String[] args) {
        System.out.println(new FrequencySort().frequencySort("tree"));

    }
}
