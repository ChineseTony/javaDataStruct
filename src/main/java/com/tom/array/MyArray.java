package com.tom.array;



/**
 * @author WangTao
 */
public class MyArray<T> {

    private T[] data;

    private int size;

    private static  final  int DEFAULT_CAPACITY = 10;


    /**
     *
     * @param capacity  数组的容量
     */
    public MyArray(int capacity) {
        data =(T[]) new Object[capacity];
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

    public void addLast(T t){
        add(size, t);
    }

    public void addFirst(T t){
        add(0, t);
    }

    public void add(int index, T o){
        if (index < 0 || index > size ){
            throw new IllegalArgumentException("add index");
        }

        if (size == data.length){
            resize(data.length*2);
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

    public void set(int index,T t){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = t;
    }


    public boolean contains(T t){
        for (int i = 0; i < size; i++) {
            if (t.equals(data[i])){
                return true;
            }
        }
        return false;
    }

    public int find(T t){
        for (int i = 0; i < size; i++) {
            if (t.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    public T remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is illegal");
        }
        T temp = data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 2){
            resize(data.length / 2);
        }
        return temp;
    }

    public T removeFirst(){
        return remove(0);
    }

    public T removeLast(){
        return remove(size - 1);
    }

    public void removeElement(T t){
        int index = find(t);
        if (index != -1){
            remove(index);
        }
    }



    private void resize(int length){
        T[] objects = (T[]) new Object[length];
        for (int i = 0; i < size; i++) {
            objects[i] = data[i];
        }
        data = objects;
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
