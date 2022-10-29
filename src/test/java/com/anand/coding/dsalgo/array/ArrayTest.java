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
            ArraySearch array = new ArraySearch(IntStream.rangeClosed(1, k).toArray());

            System.out.println("Running pivotElementIndex test for: " + array);
            for (int i = 1; i <= array.size(); i++) {


                array.rotate(1);
                int pivotElementIndex = array.getRotatedArrayPivotElementIndex();
                System.out.println(array + " -> " + pivotElementIndex);
                Assert.assertEquals(pivotElementIndex, i);
            }
        }
    }

    @Test
    public void testPivotElementIndexDuplicateEntries(){

        Assert.assertEquals(new ArraySearch(new int[]{3, 3, 3, 3, 4, 3}).getRotatedArrayPivotElementIndex(), 5);
        Assert.assertEquals(new ArraySearch(new int[]{3, 4, 3, 3, 3, 3}).getRotatedArrayPivotElementIndex(), 2);
        Assert.assertEquals(new ArraySearch(new int[]{3, 3, 3, 3, 3, 3}).getRotatedArrayPivotElementIndex(), 6);
        Assert.assertEquals(new ArraySearch(new int[]{5,3}).getRotatedArrayPivotElementIndex(), 1);
    }

}
