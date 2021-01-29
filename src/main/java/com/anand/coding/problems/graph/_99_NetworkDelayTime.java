package com.anand.coding.problems.graph;

import java.util.Arrays;

/**
 * There are n network nodes with given signal travelling time from one node to another.
 *
 * Calculate minimum time for a signal originating at src to reach all the other nodes.
 * -1 if not all the nodes can get the signal.
 *
 */
public class _99_NetworkDelayTime {

    public int networkDelayTieBellman(int[][] times, int n, int src){

        int INF= Integer.MAX_VALUE;
        int size=n;

        int [] dist = new int[size+1];

        for(int i=1; i<=size; i++) {
            dist[i]=INF;
        }
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

        int result = -1;
        for(int d: dist){
            if(d==INF){
                return -1;
            }
            if(d>result){
                result=d;
            }
        }
        return result;
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] times = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(new _99_NetworkDelayTime().networkDelayTieBellman(times, 4, 2));
    }
}
