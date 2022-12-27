package com.anand.coding.problems.graph.disjointset;

import java.util.stream.IntStream;

/**
 * Given a 2D matrix (0/1), find the number of islands.
 * A group of connected 1's forms an island
 *
 * Input : mat[][] = {{1, 1, 0, 0, 0},
 *                    {0, 1, 0, 0, 1},
 *                    {1, 0, 0, 1, 1},
 *                    {0, 0, 0, 0, 0},
 *                    {1, 0, 1, 0, 1}
 * Output : 5
 *
 * DisjointSets solution: Refer IslandCountDFS.java
 *
 */
public class _01_IslandCountDisjointSets {

    // Possible directions of a node up, down, left, right.
    static int[] R = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] C = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static int islandCount(int [][]A, int n, int m) {

        int [] Parent = new int[n*m];
        for(int i=0; i<Parent.length; Parent[i]=i++);

        int zerosCount = 0;

        //Process each node
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(A[i][j]==0) {
                    zerosCount++;
                    continue;
                }

                // BFS all neighbors if any
                for(int k=0; k<R.length; k++){
                    int p =  i+R[k];
                    int q =  j+C[k];

                    if(p<0 || p>=n || q<0 || q>=m || A[p][q]==0){
                        continue;
                    }
                    union((i*m)+j, (p*m)+q, Parent); //Union
                }
            }
        }

        return (int)IntStream.range(0, Parent.length)
                .filter(i -> Parent[i]==i).count() - zerosCount;
    }

    public static void union(int i, int j, int []Parent) {

        int iRep, jRep;
        for(iRep=i; iRep!=Parent[iRep]; iRep=Parent[iRep]); //Find
        for(jRep=j; jRep!=Parent[jRep]; jRep=Parent[jRep]); //Find

        Parent[iRep]=jRep;
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
