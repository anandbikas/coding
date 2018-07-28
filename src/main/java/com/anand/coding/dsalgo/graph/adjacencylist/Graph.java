package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.exception.GraphFullException;
import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.list.LinkedList;
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
    private LinkedList<Integer>[] adjListArray;
    private GraphType type;
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
        vertices = (T[])new Comparable[size];
        adjListArray = new LinkedList[size];
        for(int i=0; i<size; i++){
            adjListArray[i] = new LinkedList<>();
        }
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
    public void removeEdge(int u, int v){
        if(u>=size || v >= size){
            throw new ArrayIndexOutOfBoundsException();
        }

        adjListArray[u].delete(v);
        if(type.equals(GraphType.UNDIRECTED)) {
            adjListArray[v].delete(u);
        }
    }

    /**
     *
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        if (u >= size || v >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        adjListArray[u].insertStart(v);
        if (type.equals(GraphType.UNDIRECTED)) {
            adjListArray[v].insertStart(u);
        }
    }

    /**
     *
     */
    public void display(){
        System.out.println("Adjacency List Graph");

        for(int i=0; i<size; i++){
            System.out.print(vertices[i] + " -> ");
            for(Integer nodeIndex: adjListArray[i]){
                System.out.print(String.format("%s, ", vertices[nodeIndex]));
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

            for(int i: adjListArray[nodeIndex]){
                if(!visited[i]){
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

            for(int i: adjListArray[nodeIndex]){
                if(!visited[i]){
                    stack.push(i);
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

        for(int i: adjListArray[nodeIndex]){
            if(!visited[i]){
                dfsDisplayRec(i, visited);
            }
        }
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){
        // UNDIRECTED Graph
        System.out.println(("\nUnDirected Graph"));
        Graph<String> undirectedGraph = new Graph<>();

        for(int i=0; i<=5; i++){
            undirectedGraph.insert("node" + i);
        }

        undirectedGraph.addEdge(0,3);
        undirectedGraph.addEdge(0,5);
        undirectedGraph.addEdge(1,4);
        undirectedGraph.addEdge(2,5);
        undirectedGraph.addEdge(3,2);
        undirectedGraph.addEdge(5,4);

        undirectedGraph.display();
        undirectedGraph.bfsDisplay(5);
        undirectedGraph.dfsDisplay(5);
        undirectedGraph.dfsDisplayRec(5);

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
        directedGraph.addEdge(1,4);
        directedGraph.addEdge(2,5);
        directedGraph.addEdge(3,2);
        directedGraph.addEdge(5,4);

        directedGraph.display();
        directedGraph.bfsDisplay(0);

        directedGraph.removeEdge(0,5);
        directedGraph.bfsDisplay(0);
    }
}
