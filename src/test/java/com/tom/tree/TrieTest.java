package com.tom.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author WangTao
 */
public class TrieTest {
    private Trie trie;

    @Before
    public void initData(){
        trie = new Trie();
        trie.addDiGui("cat");
        trie.add("can");
        trie.addDiGui("can");
        trie.addDiGui("pan");

    }

    @Test
    public void test(){
        assertEquals(trie.getSize(),3);
        assertTrue(trie.contains("can"));
        assertTrue(trie.search("cat"));
        assertTrue(trie.isPrefix("ca"));
        assertTrue(trie.isPrefixDiGui("pa"));
        assertTrue(trie.searchRegx("c.t"));
        trie.delete("cat");
        assertEquals(trie.getSize(),2);
        assertFalse(trie.search("cat"));
        assertTrue(trie.search("can"));
    }
}
