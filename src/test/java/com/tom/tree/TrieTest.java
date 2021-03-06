package com.tom.tree;

import com.tom.trie.Trie;
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
        assertTrue(trie.delete("cat"));
        assertTrue(trie.delete("can"));
        assertEquals(trie.getSize(),1);
        assertFalse(trie.search("cat"));
        assertFalse(trie.search("can"));
    }
}
