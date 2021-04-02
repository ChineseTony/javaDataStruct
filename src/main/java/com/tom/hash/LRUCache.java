package com.tom.hash;

import java.util.LinkedHashMap;


/**
 * LRU  淘汰缓存算法
 * @param <K>
 * @param <V>
 */
public class LRUCache<K,V> {

    private int cap;
    private LinkedHashMap<K,V> cache = new LinkedHashMap<>();

    public LRUCache(int capacity){
        this.cap = capacity;
    }

    public V get(K key){
        if (!cache.containsKey(key)){
            return null;
        }
        //添加到队尾
        makeRecently(key);
        return cache.get(key);
    }

    public void  put(K key,V value){
        if (cache.containsKey(key)) {
            cache.put(key,value);
            makeRecently(key);
        }
        if (cache.size() >= this.cap){
            K oldKey = cache.keySet().iterator().next();
            cache.remove(oldKey);
        }
        cache.put(key,value);
    }

    public V  delete(K key){
        if (cache.containsKey(key)) {
            return cache.remove(key);
        }else {
            return null;
        }
    }


    private void makeRecently(K key){
        V value = cache.get(key);
        cache.remove(key);
        cache.put(key,value);
    }

}
