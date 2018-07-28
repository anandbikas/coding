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

    private T vertices[];
    private int adjArr[][];
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
        System.out.println("Adjacency Matrix Graph");

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
        System.out.println("BFS Display from index: " + nodeIndex);
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

            for(int i=0; i<size; i++){
                if(!visited[i] && adjArr[nodeIndex][i]>0){
                    queue.insert(i);
                    visited[i] = true;
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
        System.out.println("DFS Display from index: " + nodeIndex);
        if(nodeIndex>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Stack<Integer> stack = new ArrayStack<>(size);
        boolean []visited = new boolean[size];

        stack.push(nodeIndex);

        while(!stack.isEmpty()){

            nodeIndex = stack.pop();
            if(visited[nodeIndex]) {
                continue;
            }

            System.out.print(vertices[nodeIndex] + "  ");
            visited[nodeIndex] = true;

            for(int i=0; i<size; i++){
                if(!visited[i] && adjArr[nodeIndex][i]>0){
                    stack.push(i);
                }
            }
        }
        System.out.println();
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        // UNDIRECTED Graph
        System.out.println(("\nUnDirected Graph"));
        Graph<String> undirectedGraph = new Graph<>();

        for(int i=0; i<=5; i++){
            undirectedGraph.insert("node" + i);
        }

        undirectedGraph.addEdge(0,3);
        undirectedGraph.addEdge(0,5);
        undirectedGraph.addEdge(1,4, 5);
        undirectedGraph.addEdge(2,5);
        undirectedGraph.addEdge(3,2);
        undirectedGraph.addEdge(5,4);

        undirectedGraph.display();
        undirectedGraph.bfsDisplay(5);
        undirectedGraph.dfsDisplay(5);

        undirectedGraph.removeEdge(4,5);
        undirectedGraph.bfsDisplay(5);

        // DIRECTED Graph
        System.out.println(("\nDirected Graph"));
        Graph<String> directedGraph = new Graph<>(GraphType.DIRECTED);

        for(int i=0; i<=5; i++){
            directedGraph.insert("node" + i);
        }

        directedGraph.addEdge(0,3);
        directedGraph.addEdge(0,5);
        directedGraph.addEdge(1,4, 5);
        directedGraph.addEdge(2,5);
        directedGraph.addEdge(3,2);
        directedGraph.addEdge(5,4);

        directedGraph.display();
        directedGraph.bfsDisplay(0);

        directedGraph.removeEdge(0,5);
        directedGraph.bfsDisplay(0);
    }
}
