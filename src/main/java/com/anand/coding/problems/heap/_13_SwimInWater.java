package com.anand.coding.problems.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode.com/problems/swim-in-rising-water
 *
 */
public class _13_SwimInWater {

    public static int []R = {-1, 0, 0, +1}, C = {0, -1, +1, 0};

    /**
     * Priority Queue
     *
     * @param A
     * @return
     */
    public static int swimInWater1(int[][] A) {
        int n = A.length;
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.val));

        boolean [][]visited = new boolean[n][n];
        int t=A[0][0];
        visited[0][0]=true;
        pq.offer(new Cell(0,0,A[0][0]));

        while (!pq.isEmpty()) {
            Cell c = pq.remove();
            t = Math.max(t, c.val);
            if(c.i==n-1 && c.j==n-1) {
                break;
            }

            for(int x=0; x<4; x++) {
                int i = c.i+R[x], j = c.j+C[x];
                if(i<0 || i>=n || j<0 || j>=n || visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                pq.offer(new Cell(i,j,A[i][j]));
            }
        }
        return t;
    }

    /**
     * Binary Search
     *
     * @param A
     * @return
     */
    public static int swimInWater(int[][] A) {
        int n = A.length;
        int l = A[0][0], r = n*n-1;

        while(l < r) {
            int mid = l + ((r-l) >> 1);
            if(dfs(A, 0, 0, mid, new boolean[n][n])) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static boolean dfs(int[][] A, int i, int j, int time, boolean[][] visited) {
        int n = A.length;
        if(i<0 || i>=n || j<0 || j>=n || visited[i][j] || A[i][j]>time) {
            return false;
        }
        visited[i][j] = true;
        if(i==n-1 && j==n-1) {
            return true;
        }

        boolean res=false;
        for(int x=0; x<4 && !(res=dfs(A,i+R[x],j+C[x],time,visited)); x++);
        return res;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int[][] A = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        System.out.println(swimInWater(A));

        int[][] B = {{0,1,24,3,4},{2,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        System.out.println(swimInWater1(B));

        int[][] C = {{3,2},{0,1}};
        System.out.println(swimInWater(C));
    }

    public static class Cell {
        public int i,j,val;
        public Cell(int i, int j, int val){
            this.i=i; this.j=j; this.val=val;
        }
    }
}
