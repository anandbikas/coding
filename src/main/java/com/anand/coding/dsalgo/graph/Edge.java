package com.anand.coding.dsalgo.graph;

/**
 *
 */
public class Edge implements Comparable<Edge> {
    int u;
    int v;
    Integer weight;

    public Edge(int u, int v, int weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
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
