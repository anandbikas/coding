package com.anand.coding.dsalgo.graph;

/**
 *
 */
public class Edge<T> implements Comparable<Edge<T>> {
    T u;
    T v;
    Integer weight;

    public Edge(T u, T v, int weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public T getU() {
        return u;
    }

    public void setU(T u) {
        this.u = u;
    }

    public T getV() {
        return v;
    }

    public void setV(T v) {
        this.v = v;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return weight.compareTo(edge.weight);
    }
}
