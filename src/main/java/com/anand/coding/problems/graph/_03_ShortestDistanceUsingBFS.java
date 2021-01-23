package com.anand.coding.problems.graph;

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
 *
 */
public class _03_ShortestDistanceUsingBFS {

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
    public static int shortestDistance(int [][]A, int n, int m){

        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        //Find the start node
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(A[i][j]==2){
                    queue.add(new Node(i,j));
                    visited[i][j]=true;
                    break;
                }
            }
        }
        queue.add(null);

        int dist=0;
        while (queue.size()>1){
            Node node = queue.remove();
            if(node == null){
                dist++;
                queue.add(null);
                continue;
            }

            if (A[node.row][node.col] == 9){
                return dist;
            }

            // BFS all 4 neighbors if any
            for(int k=0; k<4; k++){
                int i = node.row + rows[k];
                int j = node.col + cols[k];

                if(i<0 || i>=n || j<0 || j>=m || A[i][j]==0 || visited[i][j]){
                    continue;
                }
                queue.add(new Node(i,j));
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

        System.out.println(shortestDistance(A, 5,5));
    }
}
