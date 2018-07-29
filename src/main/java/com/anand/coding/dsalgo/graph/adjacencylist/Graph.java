package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @param <T>
 */
public class Graph<T> {

    private ArrayList<T> vertices;
    private ArrayList<LinkedList<Integer>> adjListArray;

    private GraphType type;
    private int size=0;

    /**
     *
     */
    public Graph(){
        this(GraphType.UNDIRECTED);
    }

    /**
     *
     * @param type
     */
    public Graph(GraphType type){
        vertices = new ArrayList<>();
        adjListArray = new ArrayList<>();
        this.type = type;
    }

    /**
     *
     * @param node
     */
    public void insert(T node){
        vertices.add(size, node);
        adjListArray.add(size, new LinkedList<>());
        size++;
    }

    /**
     *
     * @param u
     * @param v
     */
    public void removeEdge(Integer u, Integer v){
        if(u>=size || v >= size){
            throw new ArrayIndexOutOfBoundsException();
        }

        adjListArray.get(u).remove(v);
        if(type.equals(GraphType.UNDIRECTED)) {
            adjListArray.get(v).remove(u);
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

        adjListArray.get(u).addFirst(v);
        if (type.equals(GraphType.UNDIRECTED)) {
            adjListArray.get(v).addFirst(u);
        }
    }

    /**
     *
     */
    public void display(){
        System.out.println("Adjacency List Graph");

        for(int i=0; i<size; i++){
            System.out.print(vertices.get(i) + " -> ");
            for(Integer nodeIndex: adjListArray.get(i)){
                System.out.print(String.format("%s, ", vertices.get(nodeIndex)));
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
            System.out.print(vertices.get(nodeIndex) + "  ");

            for(int i: adjListArray.get(nodeIndex)){
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

            System.out.print(vertices.get(nodeIndex) + "  ");
            visited[nodeIndex] = true;

            for(int i: adjListArray.get(nodeIndex)){
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

        System.out.print(vertices.get(nodeIndex) + "  ");
        visited[nodeIndex] = true;

        for(int i: adjListArray.get(nodeIndex)){
            if(!visited[i]){
                dfsDisplayRec(i, visited);
            }
        }
    }

    /**
     *
     * @param u
     */
    public int outDegree(int u) {
        return adjListArray.get(u).size();
    }

}
