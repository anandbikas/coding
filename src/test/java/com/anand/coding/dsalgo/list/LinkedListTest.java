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
            list.add(i);
        }

        for(int i=5; i>0; i--){
            list.addStart(i);
        }
    }

    @Test
    private void testInsertionDeletion(){

        list.add(6, 0);
        Assert.assertEquals(list.indexOf(0), 6);

        Assert.assertEquals(list.size(),count+1);

        list.remove(0);
        Assert.assertNull(list.search(0));

        list.remove(5);
        list.addSorted(5);
        Assert.assertEquals(list.indexOf(5), 5);

        Assert.assertEquals(list.removeLast().data, (Integer)10);
        Assert.assertEquals(list.removeFirst().data, (Integer)1);

        list.display();
    }

    @Test
    private void testSorting(){

        list.swapAdjacent(1);
        Assert.assertEquals(list.indexOf(1), 2);

        list.add(6, 0);
        Assert.assertEquals(list.indexOf(0), 6);

        list.bubbleSort();
        Assert.assertEquals(list.indexOf(0), 1);
    }

    @Test
    private void testMergeSort(){

        list.swapAdjacent(1);
        Assert.assertEquals(list.indexOf(1), 2);

        list.add(6, 0);
        Assert.assertEquals(list.indexOf(0), 6);

        list.mergeSort();
        Assert.assertEquals(list.indexOf(0), 1);
    }

    @Test
    private void testReverse(){
        list.reverseRec();

        int data=10;
        for(int index=1; index<=10; index++){
            Assert.assertEquals(list.indexOf(data--), index);
        }

        list.reverse();
        data=1;
        for(int index=1; index<=10; index++){
            Assert.assertEquals(list.indexOf(data++), index);
        }

    }

    @Test
    private void testLoop(){

        list.search(10).next = list.search(3);

        Assert.assertTrue(list.hasLoop());
        Assert.assertEquals(list.getLoopLength(), 8);
        Assert.assertEquals(list.getStartNodeOfLoop(), list.search(3));

        list.fixLoop();
        Assert.assertFalse(list.hasLoop());
    }

    @Test
    private void testNodeIndexes(){

        Assert.assertEquals(list.min().data, (Integer)1);
        Assert.assertEquals(list.max().data, (Integer)10);

        Assert.assertEquals(list.kthNodeFromEnd(7), list.search(4));

        Assert.assertEquals(list.indexOf(list.getMiddleNode().data), 6);

        list.add(11);
        Assert.assertEquals(list.indexOf(list.getMiddleNode().data), 6);
    }

    @Test
    private void isPalindrome(){
        Assert.assertFalse(list.isPalindromeUsingStack());
        Assert.assertFalse(list.isPalindrome());
        Assert.assertFalse(list.isPalindromeRec());

        LinkedList<Integer> list1 = new LinkedList<>();

        for(int i=5; i>0; i--){
            list1.addStart(i);
        }

        for(int i=1; i<=5; i++){
            list1.addStart(i);
        }
        Assert.assertTrue(list1.isPalindromeUsingStack());
        Assert.assertTrue(list1.isPalindrome());
        Assert.assertTrue(list1.isPalindromeRec());

        Assert.assertEquals(list.size(),count);
    }
}
