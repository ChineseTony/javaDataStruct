package com.tom.oj;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author WangTao
 */
public class RemoveDuplicatesTest {


    @Test
    public void removeElements(){
        assertEquals(RemoveDuplicates.removeDuplicates("abbaca"),"ca");
    }
}
