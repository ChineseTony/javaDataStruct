package com.tom.heap;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author WangTao
 */
public class MyMaxHeapTest {

    private MyMaxHeap<Integer> maxHeap;
    private MyMaxHeap<Integer> maxHeap2;

    private boolean flag = true;

    @Before
    public void initData(){
        maxHeap = new MyMaxHeap<>();
        int n = 1000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.insert(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int j = 0; j < n; j++) {
            arr[j] = maxHeap.removeMax();
            System.out.println(arr[j]);
        }
        for (int i = 1; i < n; i++) {
            if (arr[i-1] < arr[i]){
                flag = false;
            }
        }
    }


    @Test
    public void removeElements() {
        assertTrue(flag);
    }

    @Test
    public void heapify() {
        Integer[] a = new Integer[]{3,4,1,5,6};
        maxHeap2 = new MyMaxHeap<>(a);
        int[] arr = new int[a.length];
        for (int j = 0; j < a.length; j++) {
            arr[j] = maxHeap2.removeMax();
            System.out.println(arr[j]);
        }

    }


}
