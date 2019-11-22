package com.anand.coding.problems.graph;

/**
 * Flood fill is used in MS paint to fill the pixel along with all surrounding same colored pixels with another color using DFS algorithm.
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
public class _01_FloodFill {

    // Possible 8 directions of a node.
    // 4 directions can also be used.
    static int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };

    /**
     *
     * @param A
     * @param n
     * @param m
     * @param i
     * @param j
     * @param newColor
     */
    public static void floodFill(int [][]A, int n, int m, int i, int j, int newColor) {
        floodFill(A, n, m, i, j, A[i][j], newColor);

    }

    /**
     *
     * @param A
     * @param n
     * @param m
     * @param i
     * @param j
     * @param oldColor
     * @param newColor
     */
    private static void floodFill(int [][]A, int n, int m, int i, int j, int oldColor, int newColor) {

        if(i<0 || i>=n || j<0 || j>=m || A[i][j]==0 || A[i][j]!=oldColor){
            return;
        }

        A[i][j] = newColor;

        // DFS all 8 neighbors if any
        for(int k=0; k<8; k++){
            floodFill(A, n, m, i+row[k], j+col[k], oldColor, newColor);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){
        int [][]A = {{1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1},
                    {1, 0, 0, 1, 1},
                    {1, 2, 2, 2, 2},
                    {1, 1, 1, 2, 2},
                    {1, 1, 1, 2, 1},
        };

        floodFill(A, 6,5,4,3, 5);

        display(A,6,5);
    }

    public static void display(int [][] A, int n, int m)
    {
        System.out.println();
        for(int i=0; i<n; i++){

            for(int w =0; w<m; w++){
                System.out.print(String.format("%4d", A[i][w]));
            }
            System.out.println();
        }
    }
}
