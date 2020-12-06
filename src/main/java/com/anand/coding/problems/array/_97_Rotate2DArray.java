package com.anand.coding.problems.array;

import java.util.*;

/**
 *
 */
public class _97_Rotate2DArray {


    /**
     * Rotate a matrix by 90 degree right using circular shift.
     *
     * @param M
     * @return
     */
    public static void rotateBy90Degree(int [][]M){

        Queue<Integer> queue = new ArrayDeque<>(); //new LinkedList<>();

        int left=0;
        int right=M[0].length-1;

        for(;left<right;left++,right--){

            for(int j=left+1; j<=right;j++){
                queue.add(M[left][j]);
            }

            for(int i=left; i<=right;i++){
                M[left][right+left-i] = M[i][left];
            }

            for(int j=left; j<=right; j++){
                M[j][left] = M[right][j];
            }

            for(int i=right; i>=left; i--){
                M[right][right+left-i] =  M[i][right];
            }

            for(int i=left+1; i<=right; i++){
                M[i][right] = queue.remove();
            }
        }
    }

    /**
     * Rotate a matrix by 90 degree right using metrics transpose and row reversed.
     *
     * @param M
     * @return
     */
    public static void rotateBy90DegreeV2(int [][]M){

        int size=M[0].length;

        //Transpose
        for(int i=0; i<size; i++){
            for(int j=i+1; j<size; j++){
                int temp = M[i][j];
                M[i][j] = M[j][i];
                M[j][i] = temp;
            }
        }

        //Row reverse
        for(int i=0; i<size; i++){
            for(int left=0,right=size-1;left<right; left++,right--){
                int temp = M[i][left];
                M[i][left] = M[i][right];
                M[i][right] = temp;
            }
        }
    }

    /**
     *
     */
    public static void display(int[][]M){

        for(int i=0; i<M[0].length; i++){
            for(int j=0; j<M[0].length; j++){
                System.out.print(String.format("%d  ", M[i][j]));
            }
            System.out.println();
        }
    }
    /**
     *
     * 5  13  2  5
     * 14  3  4  1
     * 12  6  8  9
     * 16  7  10  11
     * @param args
     */
    public static void main(String args[]){

        int [][]M = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};

        rotateBy90DegreeV2(M);
        display(M);
    }
}

