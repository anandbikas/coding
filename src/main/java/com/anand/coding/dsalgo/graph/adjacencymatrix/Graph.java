package com.anand.coding.dsalgo.graph.adjacencymatrix;

import com.anand.coding.dsalgo.exception.GraphFullException;
import com.anand.coding.dsalgo.graph.GraphType;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;

/**
 *
 * @param <T>
 */
public class Graph<T> {

    private static final int DEFAULT_SIZE = 100;

    private T []vertices;
    private int [][]adjArr;
    private GraphType type = GraphType.UNDIRECTED;

    private int size=0;

    /**
     *
     */
    public Graph(){
        this(DEFAULT_SIZE);
    }

    /**
     *
     */
    public Graph(GraphType type) {
        this(DEFAULT_SIZE, type);
    }

    /**
     *
     * @param size
     */
    public Graph(int size){
        this(size, GraphType.UNDIRECTED);
    }

    /**
     *
     * @param size
     */
    @SuppressWarnings("unchecked")
    public Graph(int size, GraphType type){
        vertices = (T[])new Object[size];
        adjArr = new int[size][size];
        this.type = type;
    }

    /**
     *
     * @param node
     */
    public void insert(T node){
        if(size >=vertices.length){
            throw new GraphFullException();
        }

        vertices[size++]=node;
    }

    /**
     *
     * @param u
     * @param v
     */
    public void addEdge(int u, int v){
        addEdge(u,v,1);
    }

    /**
     *
     * @param u
     * @param v
     */
    public void removeEdge(int u, int v){
        addEdge(u,v,0);
    }

    /**
     *
     * @param u
     * @param v
     * @param weight
     */
    public void addEdge(int u, int v, int weight){
        if(u>=size || v >= size){
            throw new ArrayIndexOutOfBoundsException();
        }

        adjArr[u][v]= weight;
        if(type.equals(GraphType.UNDIRECTED)){
            adjArr[v][u]= weight;
        }
    }

    /**
     * Display node along with all of its adjacent nodes.
     */
    public void display(){
        System.out.println("\nAdjacency Matrix Graph");

        for(int u=0; u<size; u++){
            System.out.print(vertices[u] + " -> ");
            for(int v=0; v<size; v++){
                if(adjArr[u][v]>0){
                    System.out.print(String.format("%s(%s), ", vertices[u], adjArr[u][v]));
                }
            }
            System.out.println();
        }
    }

    /**
     * Breath First Search algorithm (similar to levelOrderTraversal of a tree)
     *
     * @param u
     */
    public void bfsDisplay(int u) {
        System.out.println("\nBFS Display from index: " + u);
        if(u>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Queue<Integer> queue = new ArrayDeque<>(size);
        boolean []visited = new boolean[size];

        queue.add(u);
        visited[u] = true;

        while(!queue.isEmpty()){
            u = queue.remove();
            System.out.print(vertices[u] + "  ");

            for(int v=0; v<size; v++){
                if(adjArr[u][v]>0 && !visited[v]){
                    queue.add(v);
                    visited[v] = true;
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth First Search algorithm
     *
     * @param u
     */
    public void dfsDisplay(int u) {
        System.out.println("\nDFS Display from index: " + u);
        if(u>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Stack<Integer> stack = new Stack<>();
        boolean []visited = new boolean[size];

        stack.push(u);
        visited[u] = true;

        while(!stack.isEmpty()){
            u = stack.pop();
            System.out.print(vertices[u] + "  ");

            for(int v=size-1; v>=0; v--){
                if(adjArr[u][v]>0 && !visited[v]){
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }
        System.out.println();
    }

    /**
     *
     * @param u
     */
    public void dfsDisplayRec(int u){
        System.out.println("\nDFS Display Recursive from index: " + u);
        if(u>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        boolean []visited = new boolean[size];
        dfsDisplayRec(u, visited);
        System.out.println();
    }

    /**
     *
     * @param u
     * @param visited
     */
    private void dfsDisplayRec(int u, boolean []visited){

        System.out.print(vertices[u] + "  ");
        visited[u] = true;

        for(int v=0; v<size; v++){
            if(adjArr[u][v]>0 && !visited[v]){
                dfsDisplayRec(v, visited);
            }
        }
    }

    /**
     *
     * @param u
     */
    public int inDegree(int u) {
        int inDegree=0;

        for(int i=0;i<size;i++) {
            if (adjArr[i][u] > 0) {
                inDegree++;
            }
        }

        return inDegree;
    }

    /**
     *
     * @param u
     */
    public int outDegree(int u) {
        int outDegree=0;

        for(int i=0;i<size;i++) {
            if (adjArr[u][i] > 0) {
                outDegree++;
            }
        }

        return outDegree;
    }

    /**
     * 𝑖𝑗'th entry of 𝐴 pow 𝑘 gives the number of 𝑘-length paths/walks connecting the vertices 𝑖 and 𝑗.
     *
     * Path vs Walk:
     *    1. Path doesn't have repeated nodes.
     *    2. Walk can have repeated nodes.
     *      1.1 In directed graph this happens due to a loop.
     *      1.2 In unDirected graph, repeatition is natural.
     *
     * @return
     */
    public int [][] pathMatrix(int pathLength){
        return matrixOfPowK(adjArr, pathLength);
    }

    /**
     *
     * @param A
     * @param k
     * @return
     */
    private int [][] matrixOfPowK(int [][] A, int k){

        int [][] R = new int[A.length][A.length];
        int [][] T = new int[A.length][A.length];

        if(k<1){
            return R;
        }

        for (int p=0; p<A.length; p++) {
            for (int q=0; q<A.length; q++) {
                R[p][q] = A[p][q];
            }
        }

        while(k-->1) {
            // Reset result array to 0 and move it to temp.
            for (int p=0; p<A.length; p++) {
                for (int q=0; q<A.length; q++) {
                    T[p][q] = R[p][q];
                    R[p][q]=0;
                }
            }

            // Matrix multiplication.
            for (int p=0; p<A.length; p++) {
                for (int q=0; q<A.length; q++) {
                    for (int r=0; r<A.length; r++) {
                        R[p][q] += T[p][r] * A[r][q];
                    }
                }
            }
        }
        return R;
    }

    /**
     *
     * @return
     */
    public int [][] allPathMatrix(int uptoPathLength){
        return sumMatrixOfPowFromOneToK(adjArr, uptoPathLength);
    }

    /**
     *
     * @param A
     * @param k
     * @return
     */
    private int [][] sumMatrixOfPowFromOneToK(int [][] A, int k){

        int [][] R = new int[A.length][A.length];
        int [][] T = new int[A.length][A.length];

        int [][] S = new int[A.length][A.length];

        if(k<1){
            return S;
        }

        for (int p=0; p<A.length; p++) {
            for (int q=0; q<A.length; q++) {
                R[p][q] = A[p][q];
                S[p][q] += R[p][q];
            }
        }

        while(k-->1) {
            // Reset result array to 0 and move it to temp.
            for (int p=0; p<A.length; p++) {
                for (int q=0; q<A.length; q++) {
                    T[p][q] = R[p][q];
                    R[p][q]=0;
                }
            }

            // Matrix multiplication.
            for (int p=0; p<A.length; p++) {
                for (int q=0; q<A.length; q++) {
                    for (int r=0; r<A.length; r++) {
                        R[p][q] += T[p][r] * A[r][q];
                    }
                    S[p][q] += R[p][q];
                }
            }
        }
        return S;
    }

    /**
     * Dynamic Programming approach.
     * Floyd Warshall: All pairs shortest path matrix.
     *
     * R[u][v] represents minimum path value from u to v. The path can be of any length upto size.
     *
     * @return
     */
    public int [][] allPathMatrixWarshall(){

        int [][] R = new int[size][size];

        for (int p=0; p<adjArr.length; p++) {
            for (int q=0; q<adjArr.length; q++) {
                R[p][q] = adjArr[p][q];
            }
        }

        for(int k=0; k<size; k++){

            for(int u=0; u<size; u++){
                for(int v=0; v<size; v++){
                    // If k is an intermediate node, check if it comes in shortest path.
                    if(u==v || R[u][k]==0 || R[k][v]==0) {
                        continue;
                    }
                    // For weighted graph store actual path weight.
                    int pathValue = R[u][k] + R[k][v];
                    if(R[u][v]==0 || pathValue<R[u][v]){
                        R[u][v] = pathValue;
                    }

                    // For unweighted graph, store only 1 to indicate a path is present.
                    // R[u][v] = 1;
                }
            }
        }
        return R;
    }

}
