package com.anand.coding.problems.graph.dfs;

/**
 * Flood fill is used in MS Paint to fill the pixel along with all surrounding same colored pixels with another color using DFS algorithm.
 *
 * Input:
 *        screen[M][N] ={{1, 1, 1, 1, 1},
 *                       {1, 1, 1, 1, 1},
 *                       {1, 0, 0, 1, 1},
 *                       {1, 2, 2, 2, 2},
 *                       {1, 1, 1, 2, 2},
 *                       {1, 1, 1, 2, 1},
 *                       };
 *    coordinates (x = 4, y = 3), newColor = 5
 *
 * Output:
 * Screen should be changed to following.
 *         screen[M][N] ={{1, 1, 1, 1, 1},
 *                        {1, 1, 1, 1, 1},
 *                        {1, 0, 0, 1, 1},
 *                        {1, 5, 5, 5, 5},
 *                        {1, 1, 1, 5, 5},
 *                        {1, 1, 1, 5, 1},
 *                        };
 */
public class _01_FloodFillDFS {

    // Possible 8 directions of a node.
    // 4 directions can also be used.
    static int[] R = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] C = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] R4 = { -1, 0, 0, 1}, C4 = { 0, -1, 1, 0};

    public static void floodFill(int [][]A, int i, int j, int newColor) {
        int n=A.length, m=A[0].length;
        floodFillDfs(A, n, m, i, j, A[i][j], newColor);
    }

    private static void floodFillDfs(int [][]A, int n, int m, int i, int j, int oldColor, int newColor) {

        if(i<0 || i>=n || j<0 || j>=m || A[i][j]==0 || A[i][j]!=oldColor){
            return;
        }

        A[i][j] = newColor;

        // DFS all 8 neighbors if any
        for(int k=0; k<8; k++) {
            floodFillDfs(A, n, m, i+R[k], j+C[k], oldColor, newColor);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String []args) {
        int [][]A = {{1, 1, 1, 1, 1},
                     {1, 1, 1, 1, 1},
                     {1, 0, 0, 1, 1},
                     {1, 2, 2, 2, 2},
                     {1, 1, 1, 2, 2},
                     {1, 1, 1, 2, 1}};

        floodFill(A,4,3, 5);
        display(A);
    }

    public static void display(int [][] A) {
        System.out.println();
        for(int i=0; i<A.length; i++){
            for(int j =0; j<A[0].length; j++){
                System.out.printf("%4d", A[i][j]);
            }
            System.out.println();
        }
    }
}
