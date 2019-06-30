package com.anand.coding.dsalgo.array;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

@Test
public class ArrayTest {

    @Test
    public void testPivotElementIndex(){

        for(int k=1; k<=15; k++) {
            Array array = new Array(IntStream.rangeClosed(1, k).toArray());

            System.out.println("Running pivotElementIndex test for: " + array);
            for (int i = 1; i <= array.getNumberOfElements(); i++) {


                array.rotateByNElements(1);
                int pivotElementIndex = array.getRotatedArrayPivotElementIndex();
                System.out.println(array + " -> " + pivotElementIndex);
                Assert.assertEquals(pivotElementIndex, i);
            }

        }
    }

}
