package com.anand.coding.dsalgo.graph;

/**
 *
 */
public class Edge<T> implements Comparable<Edge<T>> {
    public T u, v;
    public int weight;

    public Edge(T u, T v, int weight){
        this.u = u; this.v = v; this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return Integer.compare(weight, edge.weight);
    }
}
