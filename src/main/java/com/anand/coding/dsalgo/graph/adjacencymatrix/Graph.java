package com.anand.coding.dsalgo.graph.adjacencymatrix;

import com.anand.coding.dsalgo.exception.GraphFullException;
import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

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

        for(int i=0; i<size; i++){
            System.out.print(vertices[i] + " -> ");
            for(int j=0; j<size; j++){
                if(adjArr[i][j]>0){
                    System.out.print(String.format("%s(%s), ", vertices[j], adjArr[i][j]));
                }
            }
            System.out.println();
        }
    }

    /**
     * Breath First Search algorithm (similar to levelOrderTraversal of a tree)
     *
     * @param nodeIndex
     */
    public void bfsDisplay(int nodeIndex) {
        System.out.println("\nBFS Display from index: " + nodeIndex);
        if(nodeIndex>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Queue<Integer> queue = new ArrayCircularQueue<>(size);
        boolean []visited = new boolean[size];

        queue.insert(nodeIndex);
        visited[nodeIndex] = true;

        while(!queue.isEmpty()){

            nodeIndex = queue.delete();
            System.out.print(vertices[nodeIndex] + "  ");

            for(int childIndex=0; childIndex<size; childIndex++){
                if(adjArr[nodeIndex][childIndex]>0 && !visited[childIndex]){
                    queue.insert(childIndex);
                    visited[childIndex] = true;
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth First Search algorithm
     *
     * @param nodeIndex
     */
    public void dfsDisplay(int nodeIndex) {
        System.out.println("\nDFS Display from index: " + nodeIndex);
        if(nodeIndex>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Stack<Integer> stack = new ArrayStack<>(size);
        boolean []visited = new boolean[size];

        stack.push(nodeIndex);
        visited[nodeIndex] = true;

        while(!stack.isEmpty()){

            nodeIndex = stack.pop();
            System.out.print(vertices[nodeIndex] + "  ");

            for(int childIndex=size-1; childIndex>=0; childIndex--){
                if(adjArr[nodeIndex][childIndex]>0 && !visited[childIndex]){
                    stack.push(childIndex);
                    visited[childIndex] = true;
                }
            }
        }
        System.out.println();
    }

    /**
     *
     * @param nodeIndex
     */
    public void dfsDisplayRec(int nodeIndex){
        System.out.println("\nDFS Display Recursive from index: " + nodeIndex);
        if(nodeIndex>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        boolean []visited = new boolean[size];
        dfsDisplayRec(nodeIndex, visited);
        System.out.println();
    }

    /**
     *
     * @param nodeIndex
     * @param visited
     */
    private void dfsDisplayRec(int nodeIndex, boolean []visited){

        if(visited[nodeIndex]) {
            return;
        }

        System.out.print(vertices[nodeIndex] + "  ");
        visited[nodeIndex] = true;

        for(int childIndex=0; childIndex<size; childIndex++){
            if(adjArr[nodeIndex][childIndex]>0 && !visited[childIndex]){
                dfsDisplayRec(childIndex, visited);
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

}
