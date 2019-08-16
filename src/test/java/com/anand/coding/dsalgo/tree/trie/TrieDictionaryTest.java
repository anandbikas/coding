package com.anand.coding.dsalgo.tree.trie;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TrieDictionaryTest {

    private TrieDictionary<String> trieDictionary;

    @BeforeMethod
    private void setUp(){
        trieDictionary = new TrieDictionary<>();

        trieDictionary.insert("elephant", "hathi");
        trieDictionary.insert("book", "kitab");
        trieDictionary.insert("boot","juta");
        trieDictionary.insert("hippopotamus","dariyayi ghoda");
    }

    @Test
    private void testTrieDictionary(){

        Assert.assertNotNull(trieDictionary.search("elephant"));
        Assert.assertEquals(trieDictionary.search("elephant"), "hathi");
        trieDictionary.delete("boot");

        Assert.assertNull(trieDictionary.search("boot"));
    }
}
