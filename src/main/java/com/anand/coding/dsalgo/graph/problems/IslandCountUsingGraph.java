package com.anand.coding.dsalgo.graph.problems;

import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.graph.adjacencylist.Graph;

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
 */
public class IslandCountUsingGraph {

    public static int islandCount(int [][]A, int n, int m){

        Graph<Integer> graph = new Graph<>(GraphType.UNDIRECTED);

        //Create a graph and add all the 1's as nodes.
        int nodeNumber=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(A[i][j]==1) {
                    A[i][j] = ++nodeNumber;
                    graph.insert(nodeNumber-1);
                }
            }
        }

        // Is there an edge i, i+1
        for(int i=0; i<n-1; i++){
            for(int j=0; j<m; j++){
                if(A[i][j]>0 && A[i+1][j]>0){
                    graph.addEdge(A[i][j]-1,A[i+1][j]-1);
                }
            }
        }

        // Is there an edge j, j+1
        for(int i=0; i<n; i++){
            for(int j=0; j<m-1; j++){
                if(A[i][j]>0 && A[i][j+1]>0){
                    graph.addEdge(A[i][j]-1,A[i][j+1]-1);
                }
            }
        }

        // Is there an edge i+1, i+1
        for(int i=0; i<n-1; i++){
            for(int j=0; j<m-1; j++){
                if(A[i][j]>0 && A[i+1][j+1]>0){
                    graph.addEdge(A[i][j]-1,A[i+1][j+1]-1);
                }
            }
        }

        // Is there an edge i+1, j-1
        for(int i=0; i<n-1; i++){
            for(int j=1; j<m; j++){
                if(A[i][j]>0 && A[i+1][j-1]>0){
                    graph.addEdge(A[i][j]-1,A[i+1][j-1]-1);
                }
            }
        }

        // Result
        graph.display();
        return graph.countDfsForests();

    }

    private static int getNodeIndex(int i, int j, int column){
        return column*i+j;
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
