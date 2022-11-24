package com.anand.coding.problems.graph;

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
public class _04_MinimumTimeToRotAllFruitsBFS {

    // Possible directions of a node up, down, left, right.
    static int[] R = { -1, +1,  0,  0};
    static int[] C = {  0,  0, -1, +1};

    private static class Cell {
        int i, j;
        public Cell(int i, int j) { this.i = i; this.j = j;}
    }

    public static int minimumTimeToRotAll(int [][]A){
        int n=A.length, m = A[0].length;
        Cell NULL = new Cell(-1,-1);
        Queue<Cell> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        //Find the start cells already rotten
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(A[i][j]==2){
                    queue.add(new Cell(i,j));
                    visited[i][j]=true;
                }
            }
        }
        queue.add(NULL);

        int time=0;
        while (queue.size()>1) {
            Cell cell = queue.remove();
            if(cell == NULL) {
                time++;
                queue.add(NULL);
                continue;
            }

            //Rot neighbours
            for(int k=0; k<R.length; k++){
                int i = cell.i + R[k];
                int j = cell.j + C[k];

                if(i<0 || i>=n || j<0 || j>=m || A[i][j]==0 || visited[i][j]){
                    continue;
                }

                if(A[i][j]==1){
                    A[i][j]=2;
                    queue.add(new Cell(i,j));
                }
                visited[i][j]=true;
            }
        }

        //Check if not all are rotten
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(A[i][j]==1){
                    return -1;
                }
            }
        }

        return time;
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
