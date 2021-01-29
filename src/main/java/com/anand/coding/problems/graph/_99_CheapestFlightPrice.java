package com.anand.coding.problems.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/**
 * n cities are connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Find cheapest price to reach from given city u to v with at most k stops. -1 if not found.
 * Input:
 *  n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]]
 *  u = 0, v = 2, k = 1
*  Output: 200
 */
public class _99_CheapestFlightPrice {

    int [][] adjArr;
    int size;
    int cheapestPrice;

    private static class DstCostPair{
        int u,cost;
        DstCostPair(int u, int cost){
            this.u=u; this.cost=cost;
        }
    }

    /**
     * BFS Solution
     */
    public int findCheapestPriceBfs(int n, int[][] flights, int u, int v, int stops) {

        size = n;
        adjArr = new int[size][size];
        cheapestPrice=Integer.MAX_VALUE;


        for(int i=0; i<flights.length; i++){
            adjArr[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        Queue<DstCostPair> queue = new ArrayDeque<>(size);
        queue.add(new DstCostPair(u,0));

        DstCostPair NULL = new DstCostPair(-1,-1);
        queue.add(NULL);

        stops++;
        while(queue.size()>1){

            DstCostPair pair = queue.remove();
            if(pair==NULL){
                stops--;
                queue.add(NULL);
                continue;
            }

            if(pair.cost>=cheapestPrice || stops<0) {
                continue;
            }

            if(pair.u==v){
                cheapestPrice=pair.cost;
                continue;
            }

            for (int childIndex = 0; childIndex < size; childIndex++) {
                if (adjArr[pair.u][childIndex] > 0) {
                    queue.add(new DstCostPair(childIndex, pair.cost + adjArr[pair.u][childIndex]));
                }
            }

        }
        return cheapestPrice==Integer.MAX_VALUE ? -1 : cheapestPrice;
    }


    private static class DstCostStopsCapsule{
        int u,cost,stops;
        DstCostStopsCapsule(int u, int cost, int stops){
            this.u=u; this.cost=cost; this.stops=stops;
        }
    }
    /**
     * DFS Solution iterative
     *
     */
    public int findCheapestPriceDfsIterative(int n, int[][] flights, int u, int v, int stops) {

        cheapestPrice=Integer.MAX_VALUE;
        size = n;
        adjArr = new int[size][size];

        for(int i=0; i<flights.length; i++){
            adjArr[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        Stack<DstCostStopsCapsule> stack = new Stack<>();
        stack.add(new DstCostStopsCapsule(u,0, -1));

        while(!stack.isEmpty()){
            DstCostStopsCapsule pair = stack.pop();

            if(pair.cost>=cheapestPrice || pair.stops>stops) {
                continue;
            }
            if(pair.u==v){
                cheapestPrice=pair.cost;
                continue;
            }

            for(int childIndex=0; childIndex<size; childIndex++){
                if(adjArr[pair.u][childIndex]>0){
                    stack.push(new DstCostStopsCapsule(childIndex, pair.cost+adjArr[pair.u][childIndex], pair.stops+1));
                }
            }
        }
        return cheapestPrice==Integer.MAX_VALUE ? -1 : cheapestPrice;
    }

    /**
     * DFS Solution recursive
     *
     */
    public int findCheapestPriceDfs(int n, int[][] flights, int u, int v, int stops) {

        cheapestPrice=Integer.MAX_VALUE;
        size = n;
        adjArr = new int[size][size];

        for(int i=0; i<flights.length; i++){
            adjArr[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        dfsRecursive(u,v,stops+1,0);
        return cheapestPrice==Integer.MAX_VALUE ? -1 : cheapestPrice;
    }

    public void dfsRecursive(int u, int v, int stops, int cost){

        if(cost>=cheapestPrice || stops<0) {
            return;
        }
        if(u==v){
            cheapestPrice=cost;
            return;
        }

        for(int childIndex=0; childIndex<size; childIndex++){
            if(adjArr[u][childIndex]>0){
                dfsRecursive(childIndex,v,stops-1, cost+adjArr[u][childIndex]);
            }
        }
    }

    /**
     * Bellman Shortest Path Tree.
     *
     * @param n
     * @param flights
     * @param U
     * @param V
     * @param stops
     * @return
     */
    public int findCheapestPriceBellman(int n, int[][] flights, int U, int V, int stops){

        int INF= Integer.MAX_VALUE;
        size=n;

        int [] dist = new int[size];

        for(int i=0; i<size; i++) {
            dist[i]=INF;
        }
        dist[U]=0;

        for(int i=0; i<size-1 && i<=stops; i++) {

            int [] tempDist = Arrays.copyOf(dist, size);

            for(int []flight: flights){
                int u = flight[0], v=flight[1], weight=flight[2];
                if(dist[u]==INF){
                    continue;
                }

                if (weight + dist[u] < tempDist[v]) {
                    tempDist[v] = weight + dist[u];
                }
            }
            dist = tempDist;
        }
        return dist[V]==INF ? -1 : dist[V];
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceBfs(3, flights,0,2,1));
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceDfs(3, flights,0,2,1));
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceBellman(3, flights,0,2,1));

        int [][] flights1 = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceBfs(4, flights1,0,3,1));
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceDfs(4, flights1,0,3,1));
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceBellman(4, flights1,0,3,1));
    }
}
