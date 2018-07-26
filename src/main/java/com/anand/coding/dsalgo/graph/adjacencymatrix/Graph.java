package com.anand.coding.dsalgo.graph.adjacencymatrix;

import com.anand.coding.dsalgo.exception.GraphFullException;

/**
 *
 * @param <T>
 */
public class Graph<T> {

    private static final int DEFAULT_SIZE = 100;

    private T vertices[];
    private int adjArr[][];

    int size=0;

    /**
     *
     */
    public Graph(){
        this(DEFAULT_SIZE);
    }

    /**
     *
     * @param size
     */
    @SuppressWarnings("unchecked")
    public Graph(int size){
        vertices = (T[])new Object[size];
        adjArr = new int[size][size];
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
        adjArr[v][u]= weight;

    }

    /**
     *
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
     *
     * @param args
     */
    public static void main(String [] args){
        Graph<String> graph = new Graph<>();

        for(int i=0; i<=5; i++){
            graph.insert("node" + i);
        }

        graph.addEdge(0,3);
        graph.addEdge(0,5);
        graph.addEdge(1,4, 5);
        graph.addEdge(2,5);
        graph.addEdge(3,2);
        graph.addEdge(5,4);

        graph.removeEdge(4,5);

        graph.display();
    }
}
