package com.anand.coding.dsalgo.array;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

@Test
public class ArrayTest {

    @Test
    public void testPivotElementIndex(){

        for(int k=1; k<=6; k++) {
            Array array = new Array(IntStream.rangeClosed(1, k).toArray());

            System.out.println("Running pivotElementIndex test for: " + array);
            for (int i = 1; i <= array.getSize(); i++) {


                array.rotateByNElements(1);
                int pivotElementIndex = array.getRotatedArrayPivotElementIndex();
                System.out.println(array + " -> " + pivotElementIndex);
                Assert.assertEquals(pivotElementIndex, i);
            }

        }
    }

    @Test
    public void testPivotElementIndexDuplicateEntries(){

        Array array = new Array(new int[]{3, 3, 3, 3, 4, 3});
        int pivotElementIndex = array.getRotatedArrayPivotElementIndex();
        Assert.assertEquals(pivotElementIndex, 5);

        array = new Array(new int[]{3, 4, 3, 3, 3, 3});
        pivotElementIndex = array.getRotatedArrayPivotElementIndex();
        Assert.assertEquals(pivotElementIndex, 2);

        array = new Array(new int[]{3, 3, 3, 3, 3, 3});
        pivotElementIndex = array.getRotatedArrayPivotElementIndex();
        Assert.assertEquals(pivotElementIndex, 6);

        array = new Array(new int[]{5,3});
        pivotElementIndex = array.getRotatedArrayPivotElementIndex();
        Assert.assertEquals(pivotElementIndex, 1);
    }

}
