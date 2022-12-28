package com.anand.coding.problems.graph.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * You are given a 2D integer array which represents a land. Each cell has values: 0 land, 1 water
 * The flood is spreading. Each day a cell becomes flooded as given in the data int[][] cells
 *
 * A person need to escape from the top row to the last row
 * Find the last day that it is possible to walk from the top to the bottom by only walking on land cells.
 *
 * leetcode.com/problems/last-day-where-you-can-still-cross
 */
public class _99a_LastDayToEscapeFloodBFS {

    // Possible directions of a node up, down, left, right.
    static int[] R = { -1, +1,  0,  0};
    static int[] C = {  0,  0, -1, +1};

    private static class Cell {
        int i, j;
        public Cell(int i, int j) { this.i = i; this.j = j;}
    }


    public static int lastDayToCross(int n, int m, int[][] cells) {

        int [][]A = new int[n][m];
        // Flood the water and record the day when the flood reaches a cell.
        floodWater(A, cells);

        // The last day to cross can be in the range 0 to Max(A[0][0] ... A[0][n-1](day_to_reach_flood)-1
        // Do binarySearch to find the lastDayToCross in the range.
        int lastDay=1;

        for(int l=0,r=Arrays.stream(A[0]).max().orElse(0)-1; l<=r; ){
            int mid = (l+r)/2;
            int shortestDistance = shortestDistanceBFS(A, mid);
            if(shortestDistance==-1){
                r = mid-1;
                continue;
            }
            if(lastDay<mid) lastDay = mid;
            l = mid+1;
        }

        return lastDay;
    }

    public static int shortestDistanceBFS(int [][]A, int lastDay){
        int n=A.length, m=A[0].length;
        Queue<Cell> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        //Find the start node
        for(int j=0; j<m; j++) {
            if(A[0][j]==0 || A[0][j]>lastDay) {
                q.add(new Cell(0, j));
                visited[0][j] = true;
            }
        }

        while(!q.isEmpty()) {

            //for(int size=q.size(); size>0 ; size--) { Not required as there is no logic on per level-wise.
                Cell cell = q.remove();
                if(cell.i==n-1) {
                    return lastDay;
                }

                // BFS all neighbors if any
                for (int k=0; k<R.length; k++) {
                    int i = cell.i + R[k];
                    int j = cell.j + C[k];

                    if(i < 0 || i >= n || j < 0 || j >= m || visited[i][j]) {
                        continue;
                    }

                    //If there is still time for the flood to reach this place.
                    if(A[i][j]==0 || A[i][j]>lastDay){
                        q.add(new Cell(i,j));
                        visited[i][j] = true;
                    }
                }
            //}
        }

        return -1;
    }

    public static void floodWater(int[][] A, int[][] cells) {

        for(int day=1; day<=cells.length; day++){
            int i=cells[day-1][0], j=cells[day-1][1];
            A[i-1][j-1] = day;
        }

        System.out.println();
        Arrays.stream(A).map(Arrays::toString).forEach(System.out::println);
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] A = {{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}};
        System.out.println(lastDayToCross(3,3, A));

        int [][] B = {{4,2},{6,2},{2,1},{4,1},{6,1},{3,1},{2,2},{3,2},{1,1},{5,1},{5,2},{1,2}};
        System.out.println(lastDayToCross(6,2, B));

        int [][] C = {{3,2},{2,1},{1,3},{1,2},{3,3},{2,2},{4,3},{1,1},{2,3},{4,1},{3,1},{4,2}};
        System.out.println(lastDayToCross(4,3, C));
    }
}
