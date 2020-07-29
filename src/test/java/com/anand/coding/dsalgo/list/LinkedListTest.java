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
    private void testInsertionDeletion(){

        list.insertAtIndex(6, 0);
        Assert.assertEquals(list.findIndex(0), 6);

        Assert.assertEquals(list.length(),count+1);

        list.delete(0);
        Assert.assertNull(list.search(0));

        list.delete(5);
        list.insertSorted(5);
        Assert.assertEquals(list.findIndex(5), 5);

        Assert.assertEquals(list.deleteEnd().data, (Integer)10);
        Assert.assertEquals(list.deleteStart().data, (Integer)1);

        list.display();
    }

    @Test
    private void testSorting(){

        list.swapAdjacentNodes(1);
        Assert.assertEquals(list.findIndex(1), 2);

        list.insertAtIndex(6, 0);
        Assert.assertEquals(list.findIndex(0), 6);

        list.bubbleSort();
        Assert.assertEquals(list.findIndex(0), 1);
    }

    @Test
    private void testMergeSort(){

        list.swapAdjacentNodes(1);
        Assert.assertEquals(list.findIndex(1), 2);

        list.insertAtIndex(6, 0);
        Assert.assertEquals(list.findIndex(0), 6);

        list.mergeSort();
        Assert.assertEquals(list.findIndex(0), 1);
    }

    @Test
    private void testReverse(){
        list.reverseRec();

        int data=10;
        for(int index=1; index<=10; index++){
            Assert.assertEquals(list.findIndex(data--), index);
        }

        list.reverse();
        data=1;
        for(int index=1; index<=10; index++){
            Assert.assertEquals(list.findIndex(data++), index);
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

        Assert.assertEquals(list.minValueNode().data, (Integer)1);
        Assert.assertEquals(list.maxValueNode().data, (Integer)10);

        Assert.assertEquals(list.kthNodeFromEnd(7), list.search(4));

        Assert.assertEquals(list.findIndex(list.getMiddleNode().data), 6);

        list.insertEnd(11);
        Assert.assertEquals(list.findIndex(list.getMiddleNode().data), 6);
    }

    @Test
    private void isPalindrome(){
        Assert.assertFalse(list.isPalindromeUsingStack());
        Assert.assertFalse(list.isPalindrome());
        Assert.assertFalse(list.isPalindromeRec());

        LinkedList<Integer> list1 = new LinkedList<>();

        for(int i=5; i>0; i--){
            list1.insertStart(i);
        }

        for(int i=1; i<=5; i++){
            list1.insertStart(i);
        }
        Assert.assertTrue(list1.isPalindromeUsingStack());
        Assert.assertTrue(list1.isPalindrome());
        Assert.assertTrue(list1.isPalindromeRec());

        Assert.assertEquals(list.length(),count);
    }
}
