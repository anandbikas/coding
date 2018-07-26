package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.exception.GraphFullException;
import com.anand.coding.dsalgo.list.LinkedList;

/**
 *
 * @param <T>
 */
public class Graph<T extends Comparable<T>> {

    private static final int DEFAULT_SIZE = 100;

    private T vertices[];
    private LinkedList<Integer>[] adjListArray;

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
        vertices = (T[])new Comparable[size];
        adjListArray = new LinkedList[size];
        for(int i=0; i<size; i++){
            adjListArray[i] = new LinkedList<>();
        }
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

        adjListArray[u].deleteAtIndex(v+1);
        adjListArray[v].deleteAtIndex(u+1);
    }

    /**
     *
     * @param u
     * @param v
     */
    public void addEdge(int u, int v){
        if(u>=size || v >= size){
            throw new ArrayIndexOutOfBoundsException();
        }

        adjListArray[u].insertStart(v);
        adjListArray[v].insertStart(u);
    }

    /**
     *
     */
    public void display(){
        System.out.println("Adjacency List Graph");

        for(int i=0; i<size; i++){
            System.out.print(vertices[i] + " -> ");
            for(Integer pCrawl: adjListArray[i]){
                System.out.print(String.format("%s, ", vertices[pCrawl]));
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
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(3,2);
        graph.addEdge(5,4);

        graph.removeEdge(4,5);

        graph.display();
    }
}
