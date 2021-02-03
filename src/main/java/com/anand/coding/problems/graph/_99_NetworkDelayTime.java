package com.anand.coding.problems.graph;

import com.anand.coding.dsalgo.graph.adjacencylist.Pair;
import java.util.*;

/**
 * There are n network nodes with given signal travelling time from one node to another.
 *
 * Calculate minimum time for a signal originating at src to reach all the other nodes.
 * -1 if not all the nodes can get the signal.
 *
 */
public class _99_NetworkDelayTime {

    public int networkDelayTimeBellman(int[][] times, int size, int src){

        int INF= Integer.MAX_VALUE;

        int [] dist = new int[size+1];
        Arrays.fill(dist, 1, size+1, INF);
        dist[src]=0;

        for(int i=0; i<size-1; i++) {
            int [] tempDist = Arrays.copyOf(dist, size+1);

            for(int []time: times){
                int u = time[0], v=time[1], weight=time[2];
                if(dist[u]==INF){
                    continue;
                }

                if (weight + dist[u] < tempDist[v]) {
                    tempDist[v] = weight + dist[u];
                }
            }
            dist = tempDist;
        }

        int result =  Arrays.stream(dist).max().orElse(-1);
        return result==INF ? -1 : result;
    }

    public int networkDelayTimeDijkstra(int[][] times, int size, int source){

        int INF= Integer.MAX_VALUE;
        HashMap<Integer, Set<Pair<Integer,Integer>>> vertices = new HashMap<>();

        //Populate graph
        for(int []time: times) {
            int u = time[0], v = time[1], weight = time[2];
            if (!vertices.containsKey(u))
                vertices.put(u, new HashSet<>());
            if (!vertices.containsKey(v))
                vertices.put(v, new HashSet<>());
            vertices.get(u).add(new Pair<>(v,weight));
        }

        int [] dist = new int[size+1];
        boolean [] selected = new boolean[size+1];
        Arrays.fill(dist, 1, size+1, INF);
        dist[source]=0;

        PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair<>(0, source));

        while(!priorityQueue.isEmpty()) {
            int minDistNode = priorityQueue.remove().value;
            if(selected[minDistNode]){
                continue;
            }

            selected[minDistNode]=true;

            for(Pair<Integer, Integer> v : vertices.get(minDistNode)){
                if(!selected[v.key] &&
                        v.value+dist[minDistNode] < dist[v.key]){
                    dist[v.key] = v.value+dist[minDistNode];
                    priorityQueue.add(new Pair<>(dist[v.key], v.key));
                }
            }
        }

        int result =  Arrays.stream(dist).max().orElse(-1);
        return result==INF ? -1 : result;
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] times = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(new _99_NetworkDelayTime().networkDelayTimeBellman(times, 4, 2));
        System.out.println(new _99_NetworkDelayTime().networkDelayTimeDijkstra(times, 4, 2));
    }
}
