package com.tom.array;



/**
 * @author WangTao
 */
public class MyArray {

    private Object[] data;

    private int size;

    private static  final  int DEFAULT_CAPACITY = 10;


    /**
     *
     * @param capacity  数组的容量
     */
    public MyArray(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public MyArray() {
        this(DEFAULT_CAPACITY);
    }

    public int getSize() {
        return size;
    }

    public  int getCapacity() {
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addLast(Object o){
        add(size, o);
    }

    public void addFirst(Object o){
        add(0, o);
    }

    public void add(int index, Object o){
        if (size == data.length){
            throw new IllegalArgumentException("Array is full");
        }

        if (index < 0 || index > size ){
            throw new IllegalArgumentException("add index");
        }

        for (int i = size - 1 ; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = o;
        size++;
    }

    public Object get(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    public void set(int index,Object e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = e;
    }


    public boolean contains(Object e){
        for (int i = 0; i < size; i++) {
            if (e == data[i]){
                return true;
            }
        }
        return false;
    }

    public int find(Object e){
        for (int i = 0; i < size; i++) {
            if (e == data[i]){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array size : %d,capacity : %d\n",size,data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size -1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
