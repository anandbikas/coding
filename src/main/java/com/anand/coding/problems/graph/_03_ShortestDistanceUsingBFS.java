package com.anand.coding.problems.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D matrix (0/1), one of them is 2 (start) and another is 9 (destination)
 * Find the shortest distance from source to destination
 *
 *  1. Two adjacent node having value 1 means there is a way.
 *  2. 0 means there is no way.
 *  3. Moving is allowed only in up,down,left,right directions.
 *
 * Input : mat[][] = {{1, 2, 1, 0, 0},
 *                    {0, 1, 1, 0, 1},
 *                    {1, 0, 1, 1, 1},
 *                    {1, 9, 1, 0, 0},
 *                    {1, 0, 1, 0, 1}
 * Output : 5
 * problems/shortest-path-in-binary-matrix
 */
public class _03_ShortestDistanceUsingBFS {

    // Possible directions of a node up, down, left, right.
    static int[] R = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] C = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] R4 = { -1, 0, 0, 1}, C4 = { 0, -1, 1, 0};

    private static class Cell {
        int i, j;
        public Cell(int i, int j) { this.i = i; this.j = j;}
    }

    public static int shortestDistance(int [][]A){
        int n=A.length, m = A[0].length;
        Cell NULL = new Cell(-1,-1);
        Queue<Cell> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        //Find the start node
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(A[i][j]==2){
                    queue.add(new Cell(i,j));
                    visited[i][j]=true;
                    break;
                }
            }
        }
        queue.add(NULL);

        int dist=0;
        while (queue.size()>1) {
            Cell cell = queue.remove();
            if(cell == NULL) {
                dist++;
                queue.add(NULL);
                continue;
            }

            if (A[cell.i][cell.j] == 9){
                return dist;
            }

            // BFS all neighbors if any
            for(int k=0; k<R.length; k++){
                int i = cell.i + R[k];
                int j = cell.j + C[k];

                if(i<0 || i>=n || j<0 || j>=m || A[i][j]==0 || visited[i][j]){
                    continue;
                }
                queue.add(new Cell(i,j));
                visited[i][j]=true;
            }
        }

        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){
        int [][]A = {{1, 2, 1, 0, 0},
                     {0, 1, 1, 0, 1},
                     {1, 0, 1, 1, 1},
                     {1, 9, 1, 0, 0},
                     {1, 0, 1, 0, 1}
                    };

        System.out.println(shortestDistance(A));
    }
}
