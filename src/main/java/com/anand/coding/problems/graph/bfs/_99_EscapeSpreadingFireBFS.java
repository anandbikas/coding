package com.anand.coding.problems.graph.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * You are given a 2D integer array which represents a field. Each cell has values: 0 grass, 1 fire, 2 wall
 * The fire is spreading in four directions every minute.
 *
 * A person need to escape from start (0,0) to safe-house(n-1,m-1) location.
 * Return the maximum number of minutes that you can stay in your initial position before moving while still safely reaching the safehouse.
 * If this is impossible, return -1. If you can always reach the safe-house regardless of the minutes stayed, return 1000000000.
 *
 * leetcode.com/problems/escape-the-spreading-fire
 */
public class _99_EscapeSpreadingFireBFS {

    // Possible directions of a node up, down, left, right.
    static int[] R = { -1, +1,  0,  0};
    static int[] C = {  0,  0, -1, +1};

    private static class Cell {
        int i, j;
        public Cell(int i, int j) { this.i = i; this.j = j;}
    }


    public static int maxWaitMinutes(int[][] A) {

        // Spread the fire and record the time when the fire reaches a cell.
        spreadFireBFS(A);

        // The person can wait for a time in the range 0 to A[0][0](time_to_reach_fire)-1
        // Do binarySearch to find the maxWaitTime in the range.
        int maxWaitTime=-1;

        for(int l=0,r=A[0][0]*-1; l<=r; ){
            int mid = (l+r)/2;
            int shortestDistance = shortestDistanceBFS(A, mid);

            if(shortestDistance==-1){
                r = mid-1;
                continue;
            }
            if(maxWaitTime<mid) maxWaitTime = mid;
            l = mid+1;
        }

        return maxWaitTime==A[0][0]*-1 ? 1000000000 : maxWaitTime;
    }

    public static int shortestDistanceBFS(int [][]A, int waitTime){
        int n=A.length, m = A[0].length;
        Queue<Cell> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        //Find the start node
        q.add(new Cell(0,0));
        visited[0][0]=true;

        for(int time=waitTime+1; !q.isEmpty(); time++) {

            for(int size=q.size(); size>0 ; size--) {
                Cell cell = q.remove();
                if(cell.i==n-1 && cell.j==m-1) {
                    return time;
                }

                // BFS all neighbors if any
                for (int k=0; k<R.length; k++) {
                    int i = cell.i + R[k];
                    int j = cell.j + C[k];

                    if(i < 0 || i >= n || j < 0 || j >= m || visited[i][j]) {
                        continue;
                    }

                    //If there is still time for the fire to reach this place.
                    if(A[i][j]==0 || A[i][j]*-1>time
                            || (A[i][j]*-1==time && i==n-1 && j==m-1)){ // Destination case where fire and the person reaches altogether.
                        q.add(new Cell(i,j));
                        visited[i][j] = true;
                    }
                }
            }
        }

        return -1;
    }

    public static void spreadFireBFS(int[][] A) {
        int n=A.length, m = A[0].length;
        Queue<Cell> q = new ArrayDeque<>();

        //Find the start cells (already in fire)
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(A[i][j]==1){
                    q.add(new Cell(i,j));
                }
            }
        }

        int time=-1;
        for(; !q.isEmpty(); time--) {

            for(int size=q.size(); size>0 ; size--) {
                Cell cell = q.remove();

                // BFS all neighbors if any
                for (int k=0; k<R.length; k++) {
                    int i = cell.i + R[k];
                    int j = cell.j + C[k];

                    if(i < 0 || i >= n || j < 0 || j >= m || A[i][j] != 0) {
                        continue;
                    }

                    A[i][j] = time; // Store negative time as other values will be +ve
                    q.add(new Cell(i,j));
                }
            }
        }
        System.out.println();
        Arrays.stream(A).map(Arrays::toString).forEach(System.out::println);
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] A = {
                {0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 2, 1, 0},
                {0, 2, 0, 0, 1, 2, 0},
                {0, 0, 2, 2, 2, 0, 2},
                {0, 0, 0, 0, 0, 0, 0}
        };
        System.out.println(maxWaitMinutes(A));

        int [][] B = {
                {0, 2, 0, 0, 1},
                {0, 2, 0, 2, 2},
                {0, 2, 0, 0, 0},
                {0, 0, 2, 2, 0},
                {0, 0, 0, 0, 0}
        };
        System.out.println(maxWaitMinutes(B));
    }
}
