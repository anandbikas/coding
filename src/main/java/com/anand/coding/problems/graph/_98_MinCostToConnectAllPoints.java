package com.anand.coding.problems.graph;

import com.anand.coding.dsalgo.disjointset.DisjointUnionSets;
import com.anand.coding.dsalgo.graph.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * points[][] represents integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|.
 * Calculate minimum cost to make all points connected.
 *
 * All points are connected if there is exactly one simple path between any two points.
 *
 * Hint: Kruskal's MST.
 *  1. Sort all edges in increasing order of their weight
 *  2. Insert smallest edge into spanning tree.
 *  3. If the newly inserted edge forms a cycle, discard it.
 *
 *  Use union-find to detect cycle in an undirected graph.
 */
public class _98_MinCostToConnectAllPoints {

    public static int kruskalsMinimumSpanningTree(int[][] points){

        int size=points.length;

        DisjointUnionSets dus = new DisjointUnionSets(size);
        List<Edge<Integer>> edgeList = new ArrayList<>();
        List<Edge<Integer>> mstEdgeList = new ArrayList<>();

        boolean []visited = new boolean[size];
        for(int u=0; u<size; u++){
            visited[u]=true;
            int x1=points[u][0];
            int y1=points[u][1];

            for(int v=0; v<size; v++) {
                if(u==v || visited[v]){
                    continue;
                }
                int x2=points[v][0];
                int y2=points[v][1];

                int manhattan_dist= Math.abs(y2<y1 ? y1-y2 : y2-y1)
                                            + Math.abs(x2<x1 ? x1-x2 : x2-x1);

                edgeList.add(new Edge<>(u, v, manhattan_dist));
            }
        }
        Collections.sort(edgeList);

        int edgeCount=0;
        for(Edge<Integer> edge: edgeList){
            if(edgeCount >= size-1){
                break;
            }

            int leftEnd = dus.find(edge.u);
            int rightEnd = dus.find(edge.v);

            // If u,v does not create loop add it to mstGraph.
            if(leftEnd!=rightEnd){
                edgeCount++;
                mstEdgeList.add(edge);
                dus.union(leftEnd, rightEnd);
            }
        }
        return mstEdgeList.stream().mapToInt(edge -> edge.weight).reduce(Integer::sum).orElse(0);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [][]points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(kruskalsMinimumSpanningTree(points));

    }
}
