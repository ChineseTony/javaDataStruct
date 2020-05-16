package com.tom.oj;

/**
 * @author WangTao
 * 三合一。描述如何只用一个数组来实现三个栈。
 */
public class TripleInOne {

    private int[] arr;
    private int stackSize;

    private int[] indexs;

    public TripleInOne(int stackSize) {
        arr = new int[stackSize * 3];
        indexs = new int[]{0,1,2};
        this.stackSize = stackSize;

    }

    public void push(int stackNum, int value) {
        if(indexs[stackNum] >= stackSize * 3){
            return;
        }
        arr[indexs[stackNum]] = value;
        indexs[stackNum] += 3;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)){
            return -1;
        }
        indexs[stackNum] -= 3;
        return arr[indexs[stackNum]];
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)){
            return -1;
        }
        return arr[indexs[stackNum] - 3];
    }

    public boolean isEmpty(int stackNum) {
        return indexs[stackNum] < 3;
    }
}
