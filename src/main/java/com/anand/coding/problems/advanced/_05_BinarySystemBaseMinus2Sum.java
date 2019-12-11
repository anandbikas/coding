package com.anand.coding.problems.advanced;


import com.anand.coding.dsalgo.array.Array;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

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
public class _05_BinarySystemBaseMinus2Sum {

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

        if(x==110 && y==1 || x==1 && y==110){
            return 111;
        }

        if(x==110 && y==11 || x==11 && y==110){
            return 1;
        }

        throw new NotImplementedException();
    }

    /**
     *
     * @param A
     * @param B
     * @return
     */
    public static int [] add(int []A, int B[]) {

        List<Integer> list = new ArrayList<>();

        int remainder = 0;

        int i = 0, j = 0;
        for (; i < A.length && j < B.length; i++, j++) {

            int addition = add(add(A[i], B[j]), remainder);
            list.add(addition % 10);
            remainder = addition / 10;

        }

        for (; i < A.length; i++) {

            int addition = add(A[i], remainder);
            list.add(addition % 10);
            remainder = addition / 10;
        }

        for (; j < B.length; j++) {

            int addition = add(B[j], remainder);
            list.add(addition % 10);
            remainder = addition / 10;
        }

        while (remainder !=0){
            list.add(remainder%10);
            remainder = remainder/10;
        }

        //Count trailing zeros to truncace
        int trailingZerosCount=0;
        for(int k=list.size()-1; k>=0 && list.get(k)==0; k--){
            trailingZerosCount++;
        }
        return list.subList(0,list.size()-trailingZerosCount).stream().mapToInt(x->x).toArray();
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int A[] = {0,1,1,0,0,1,0,1,1,1,0,1,0,1,1};
        int B[] = {0,0,1,0,0,1,1,1,1,1,0,1};

        System.out.print(toDecimal(A));
        Array.display(A);

        System.out.print(toDecimal(B));
        Array.display(B);

        int C[] = add(A,B);
        System.out.print(toDecimal(C));

        Array.display(C);


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
