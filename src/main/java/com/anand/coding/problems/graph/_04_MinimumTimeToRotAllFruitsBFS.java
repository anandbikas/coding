package com.anand.coding.problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A 2D array contains 0,1,2. 0(empty), 1(fresh orange), 2(rotten orange).
 * One rotten orange can rot its four neighbours in one time frame.
 *
 * Find total time rot all oranges.
 * Return -1 if not all are rotten.
 *
 */
public class _04_MinimumTimeToRotAllFruitsBFS {

    // Possible 4 directions of a node up, down, left, right.
    static int[] rows = { -1, +1,  0,  0};
    static int[] cols = {  0,  0, -1, +1};

    private static class Node{
        int row, col;

        public Node(int row, int col) {
            this.row = row; this.col = col;
        }
    }

    /**
     *
     * @param A
     * @param n
     * @param m
     * @return
     */
    public static int minimumTimeToRotAll(int [][]A, int n, int m){

        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        //Find already rotten nodes.
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(A[i][j]==2){
                    queue.add(new Node(i,j));
                    visited[i][j]=true;
                }
            }
        }
        queue.add(null);

        int time=0;
        while (queue.size()>1){
            Node node = queue.remove();
            if(node == null){
                time++;
                queue.add(null);
                continue;
            }

            //Rot neighbour fruits
            for(int k=0; k<4; k++){
                int i = node.row + rows[k];
                int j = node.col + cols[k];

                if(i<0 || i>=n || j<0 || j>=m || A[i][j]==0 || visited[i][j]){
                    continue;
                }

                if(A[i][j]==1){
                    A[i][j]=2;
                    queue.add(new Node(i,j));
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
        System.out.println(minimumTimeToRotAll(A,3, 5));


        int [][] B = {
                {2, 1, 0, 2, 1},
                {0, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}
        };
        System.out.println(minimumTimeToRotAll(B,3, 5));
    }
}
