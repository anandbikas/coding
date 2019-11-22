package com.anand.coding.problems.disjointset;

import com.anand.coding.dsalgo.disjointset.DisjointUnionSets;

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
 * This can be solved using DisjointSets as well, refer IslandCountUsingDfs.java
 *
 */
public class _02_IslandCountUsingDisjointSets {

    // Possible 8 directions of a node.
    static int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };

    /**
     *
     * @param A
     * @param n
     * @param m
     */
    public static int islandCount(int [][]A, int n, int m) {

        DisjointUnionSets dus = new DisjointUnionSets(n*m);

        int zerosCount = 0;

        //Process each node
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(A[i][j]==1) {

                    // Process all 8 neighbors if any
                    for(int k=0; k<8; k++){

                        int p =  i+row[k];
                        int q =  j+col[k];

                        if(p<0 || p>=n || q<0 || q>=m || A[p][q]==0){
                            continue;
                        }
                        dus.union(i*n+j, p*n+q);
                    }
                } else{
                    zerosCount++;
                }
            }
        }
        return dus.countSets()-zerosCount;
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
