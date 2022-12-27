package com.anand.coding.problems.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * A 2D array contains 0,1,2. 0(empty), 1(fresh orange), 2(rotten orange).
 * One rotten orange can rot its four neighbours in one time frame.
 *
 * Find total time to rot all the oranges.
 * Return -1 if not all are rotten.
 *
 */
public class _03_MiniTimeRotAllFruitsBFS {

    // Possible directions of a node up, down, left, right.
    static int[] R = { -1, +1,  0,  0};
    static int[] C = {  0,  0, -1, +1};

    private static class Cell {
        int i, j;
        public Cell(int i, int j) { this.i = i; this.j = j;}
    }

    public static int minimumTimeToRotAll(int [][]A){
        int n=A.length, m = A[0].length;
        Queue<Cell> q = new ArrayDeque<>();

        //Find the start cells (already rotten)
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(A[i][j]==2){
                    q.add(new Cell(i,j));
                }
            }
        }

        int time=0;
        for(; !q.isEmpty(); time++) {

            for(int size=q.size(); size>0 ; size--) {
                Cell cell = q.remove();

                // BFS all neighbors if any
                for (int k=0; k<R.length; k++) {
                    int i = cell.i + R[k];
                    int j = cell.j + C[k];

                    if(i < 0 || i >= n || j < 0 || j >= m || A[i][j] != 1) {
                        continue;
                    }

                    A[i][j] = 2;
                    q.add(new Cell(i,j));
                }
            }
        }

        //Check if not all are rotten
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(A[i][j]==1) {
                    return -1;
                }
            }
        }

        return Math.max(0,time-1); //Remove count for the last round
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] A = {
                {2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}
        };
        System.out.println(minimumTimeToRotAll(A));


        int [][] B = {
                {2, 1, 0, 2, 1},
                {0, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}
        };
        System.out.println(minimumTimeToRotAll(B));
    }
}
