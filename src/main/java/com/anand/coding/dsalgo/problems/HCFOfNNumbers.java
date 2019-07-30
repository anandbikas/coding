package com.anand.coding.dsalgo.problems;
import java.io.IOException;

/**
 *
 */
public class HCFOfNNumbers {

    public static int hcfOfNNumbers(int[] arr)
    {
        // Lets say HCF is x with initial value 0;
        int x = 0;

        //Now calculate HCF with other numbers one by one.
        for(int y: arr){
            //This calculates HCF of two numbers x and y and stores it in x.
            while(y>0){
                int temp = y;
                y = x%y;
                x = temp;
            }
        }
        return x;
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) {

        int [] arr= {0,5,15,10,20};

        System.out.println(hcfOfNNumbers(arr));
    }
}
