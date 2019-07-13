package com.tom.stack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */
public class MinStackTest {

    @Test
    public void testMin(){
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(-1);
        minStack.push(6);
        assertEquals(minStack.getMin(),-1);
        minStack.pop();
        minStack.pop();
        assertEquals(minStack.getMin(),3);
    }
}
