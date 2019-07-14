package com.tom.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */
public class MyQueueStackTest {
    private MyStack<Integer> stack;

    @Before
    public void initData(){
        stack = new MyQueueStack<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
    }

    @Test
    public void testMin(){
        assertEquals(stack.peek().intValue(),1);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        assertEquals(sb.toString(),"1234");
    }
}
