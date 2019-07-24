package com.tom.map;


import	java.util.TreeMap;


/**
 * @author WangTao
 */
public class MyHashMap<K,V> {

    //数组 + 红黑树
    private TreeMap<K, V>[] map;

    //
    private int M;

    private int size;

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    //hash冲突 超过上界10进行扩容
    private static final int upperTol = 10;

    private static final int lowTol = 2;


    public MyHashMap(int M){
        this.M = M;
        size = 0;
        map = new TreeMap [M];
        for (int i = 0; i < M; i++) {
            map [i] = new TreeMap<>();
        }
    }

    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY);
    }


    public int getSize() {
        return size;
    }

    public void add(K key, V value){
        //hash冲突
        TreeMap<K, V> m = map [hash(key)];
        if(m.containsKey(key)){
            m.put(key,value);
        }else {
            m.put(key,value);
            size++;
            if (size >= upperTol * M){
                resize(M * 2);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> m = map [hash(key)];
        V ret = null;
        if(m.containsKey(key)){
            ret = m.remove(key);
            size--;
            if (size < lowTol * M && M / 2 >= DEFAULT_INITIAL_CAPACITY){
                resize(M / 2);
            }
        }
        return ret;
    }

    public void set(K key, V value){
        //hash冲突
        TreeMap<K, V> m = map [hash(key)];
        if(!m.containsKey(key)){
            throw new RuntimeException(key +" doesn't exist");
        }
        m.put(key,value);

    }

    public boolean containsKey(K key) {
        return map [hash(key)].containsKey(key);
    }

    public V get(K key){
        return map [hash(key)].get(key);
    }

    private int hash(K k){
        return  (k.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int newCapacity){
        TreeMap<K, V>[] newMap = new TreeMap [newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newMap [i] = new TreeMap<>();
        }
        //重新散列
        int oldM = M;
        this.M = newCapacity;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> temp = map[i];
            for (K key : temp.keySet()){
                newMap [hash(key)].put(key, temp.get(key));
            }
        }
        this.map = newMap;
    }
}
