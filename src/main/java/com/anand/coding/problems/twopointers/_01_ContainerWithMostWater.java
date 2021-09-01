package com.anand.coding.problems.twopointers;

/**
 * Given the heights of lines in a row with unit distance.
 * Find the two lines which contains the maximum water.
 *
 * Ex:
 * H = [1,8,6,2,5,4,8,3,7]
 *
 * Max_water lies between 2nd and 9th lines
 * Total = 7 * (9-2) = 49 unit of water.
 *
 */
public class _01_ContainerWithMostWater {

    public static int containerWithMostWater(int[] A) {

        int max=0;
        for(int i=0,j=A.length-1; i<j; ) {
            int area = Math.min(A[i],A[j]) * (j-i);
            if(area>max){
                max = area;
            }
            if (A[i]>A[j]) j--; else i++;
        }
        return max;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(containerWithMostWater(new int[]{4,3,2,1,4}));
        System.out.println(containerWithMostWater(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
