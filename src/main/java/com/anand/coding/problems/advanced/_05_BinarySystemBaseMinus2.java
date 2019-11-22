package com.anand.coding.problems.advanced;


import com.anand.coding.dsalgo.array.Array;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Given an array of bits representing an integer in base -2.
 * Divide the number with 2 (take ceil) and return the new array of bits.
 *
 * Positive Integers:
 *      1   -2  4   -8  16  -32
 *      ------------------------
 * 1    1
 * 2    0   1   1
 * 3    1   1   1
 * 4    0   0   1
 * 5    1   0   1
 * 6    0   1   0   1   1
 *
 *
 * Negative Integers:
 *      1   -2  4   -8  16  -32
 *      ------------------------
 * -1   1   1
 * -2   0   1
 * -3   1   0   1   1
 * -4   0   0   1   1
 * -5   1   1   1   1
 * -6   0   1   1   1
 *
 * Solution:
 * 1. Multiply by -1 (binary multiplication by 11(base-2))
 * 2. Divide by -2 (left shift)
 */
public class _05_BinarySystemBaseMinus2 {

    /**
     * Binary addition base -2
     *
     * @param x
     * @param y
     * @return
     */
    public static int add(int x, int y){

        if(x==0){
            return y;
        }
        if(y==0){
            return x;
        }
        if(x==1 && y==1){
            return 110;
        }

        if(x==1 && y==11 || x==11 && y==1){
            return 0;
        }

        if(x==110 && y==11 || x==11 && y==110){
            return 1;
        }

        throw new NotImplementedException();
    }

    /**
     *
     * @param A
     * @return
     */
    public static int [] divideBy2(int []A){

        int B[] = new int[A.length];
        int remainder = 0;
        int i=1;
        for(; i<A.length; i++){

            int addition = add(add(A[i],A[i-1]), remainder);
            B[i-1] = addition%10;
            remainder = addition/10;
        }
        B[i-1] = add(A[i-1],remainder);
        return B;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int A[] = {0,0,0,1,1,1,1,1,1};
        int B[] = divideBy2(A);

        System.out.print(toDecimal(A));
        Array.display(A);

        System.out.print(toDecimal(B));
        Array.display(B);

    }

    public static long toDecimal(int A[]){
        long res=0;

        for(int i=0; i<A.length; i++){
            if(A[i]==1){
                res += Math.pow(-2, i);
            }
        }
        return res;
    }
}
