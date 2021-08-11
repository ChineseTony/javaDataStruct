package com.tom.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangtao
 * @date 2021/8/6
 */
public class LRU<K,V> {

    private int capacity;

    private static final float hashTableLoadFactor = 0.75f;

    private LinkedHashMap<K,V> map;

    public LRU(int capacity) {
        this.capacity = capacity;
        int hashTableCapacity = (int) Math.ceil(capacity / hashTableLoadFactor) + 1;
        map = new LinkedHashMap<K,V>(hashTableCapacity,hashTableLoadFactor,
                true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LRU.this.capacity;
//                return false;
            }
        };
    }

    public V get(K key){
        return map.get(key);
    }

    public  void put(K key, V value) {
        map.put(key, value);
    }


    public  void clear() {
        map.clear();
    }

    public  int usedEntries() {
        return map.size();
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String,String>(
            4,hashTableLoadFactor,true
        );
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.get("1");
        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }

        LRU<String,String> lru = new LRU<>(3);
        lru.put("1","1");
        lru.put("2","2");
        lru.put("3","3");
        lru.put("4","4");
        System.out.println(lru.usedEntries());

    }
}
