package com.anand.coding.problems;

/**
 * Find cube root of a given integer.
 * if its not a perfect cube, return nearest number less than the cubeRoot.
 */
public class _03_CubeRoot {

    /**
     *
     * @param val
     * @return
     */
    public static Integer cubeRoot(int val){

        int left =0;
        int right =val;

        while(left<=right){
            int mid = left + (right-left)/2;

            int x = mid*mid*mid;
            if(x == val){
                return mid;
            }
            if(x<val){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return right;
    }

    /**
     *
     */
    public static void main(String []args){

        System.out.println(cubeRoot(7));

        System.out.println(cubeRoot(8));
        System.out.println(cubeRoot(9));

        System.out.println(cubeRoot(1000));
        System.out.println(cubeRoot(1330));

        System.out.println(cubeRoot(1331));
        System.out.println(cubeRoot(1332));
    }
}
