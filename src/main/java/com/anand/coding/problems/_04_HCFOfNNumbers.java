package com.anand.coding.problems;
import java.io.IOException;

/**
 *
 */
public class _04_HCFOfNNumbers {

    public static int hcfOfNNumbers(int[] arr) {

        int x = arr[0];

        //Calculate HCF with each number.
        for(int i=1; i<arr.length; i++){
            int y = arr[i];

            //HCF of two numbers x and y, stored in x.
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
