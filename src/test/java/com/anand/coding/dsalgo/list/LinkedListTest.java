package com.anand.coding.dsalgo.list;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class LinkedListTest {

    private LinkedList<Integer> list;
    private static final int count=10;

    @BeforeMethod
    private void setUp(){
        list = new LinkedList<>();

        for(int i=6; i<=count; i++){
            list.insertEnd(i);
        }

        for(int i=5; i>0; i--){
            list.insertStart(i);
        }
    }

    @Test
    private void testBasics(){
        Assert.assertEquals(list.kthNodeFromEnd(7),list.search(4));

        list.bubbleSort();
        list.delete(5);
        list.insertSorted(5);
        Assert.assertEquals(list.findIndex(5), 5);
    }

    @Test
    private void testReverseRecursive(){
        list.reverseRec();

        int data=10;
        for(int index=1; index<=10; index++){
            Assert.assertEquals(list.findIndex(data--), index);
        }
    }

    @Test
    private void testDeletion(){
        list.delete(5);

        Assert.assertNull(list.search(5));
        Assert.assertEquals(list.length(),count-1);
    }

    @Test
    private void testLoop(){

        list.search(10).setNext(list.search(3));

        Assert.assertTrue(list.hasLoop());
        Assert.assertEquals(list.getLoopLength(), 8);
        Assert.assertEquals(list.getStartNodeOfLoop(), list.search(3));

        list.fixLoop();
        Assert.assertFalse(list.hasLoop());
    }
}
