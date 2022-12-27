package com.anand.coding.problems.graph.dfs;

/**
 * Given a 2D matrix (0/1), find the number of islands.
 * A group of connected 1s forms an island
 *
 * Input : mat[][] = {{1, 1, 0, 0, 0},
 *                    {0, 1, 0, 0, 1},
 *                    {1, 0, 0, 1, 1},
 *                    {0, 0, 0, 0, 0},
 *                    {1, 0, 1, 0, 1}
 * Output : 5
 *
 * DisjointSets solution: Refer IslandCountUsingDisjointSets.java
 */
public class _02_IslandCountDFS {

    // Possible 8 directions of a node.
    static int[] R = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] C = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static int islandCount(int [][]A, int n, int m){

        boolean[][] visited = new boolean[n][m];

        int count = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(A[i][j]==1 && !visited[i][j] ) {
                    dfs(A, n, m, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(int [][]A, int n, int m, boolean[][] visited, int i, int j) {

        if(i<0 || i>=n || j<0 || j>=m || A[i][j]==0 || visited[i][j]){
            return;
        }

        visited[i][j] = true;

        // DFS all 8 neighbors if any
        for(int k=0; k<8; k++){
            dfs(A, n, m, visited, i+ R[k], j+ C[k]);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){
        int [][]A = {
                {1,1,0,0,0},
                {0,1,0,0,1},
                {1,0,0,1,1},
                {0,0,0,0,0},
                {1,0,1,0,1}
            };

        System.out.println(islandCount(A, 5,5));
    }
}
