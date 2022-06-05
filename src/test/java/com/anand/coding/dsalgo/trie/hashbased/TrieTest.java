package com.anand.coding.dsalgo.trie.hashbased;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TrieTest {

    private Trie trie;

    @BeforeMethod
    private void setUp(){
        trie = new Trie();

        trie.insert("elephant", "hathi");
        trie.insert("book", "kitab");
        trie.insert("boot","juta");
        trie.insert("hippopotamus","dariyayi ghoda");

        trie.insert(";}#)*!~", "bikas_anand");
        trie.insert("®ÆǛ", "latin");
    }

    @Test
    private void testTrie(){

        Assert.assertNotNull(trie.search("elephant"));
        Assert.assertEquals(trie.search("elephant"), "hathi");
        Assert.assertEquals(trie.search("®ÆǛ"), "latin");

        trie.delete("boot");

        Assert.assertNull(trie.search("boot"));
    }
}
