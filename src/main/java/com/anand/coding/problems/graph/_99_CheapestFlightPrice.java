package com.anand.coding.problems.graph;

import com.anand.coding.dsalgo.graph.adjacencylist.Pair;

import java.util.*;

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
     * @param size
     * @param flights
     * @param U
     * @param V
     * @param stops
     * @return
     */
    public int findCheapestPriceBellman(int size, int[][] flights, int U, int V, int stops){

        int INF= Integer.MAX_VALUE;

        int [] dist = new int[size];
        Arrays.fill(dist, INF);
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

    public int findCheapestPriceDijkstra(int size, int[][] flights, int U, int V, int stops){

        HashMap<Integer, Set<Pair<Integer,Integer>>> vertices = new HashMap<>();

        //Populate graph
        for(int []flight: flights) {
            int u = flight[0], v = flight[1], weight = flight[2];
            if (!vertices.containsKey(u))
                vertices.put(u, new HashSet<>());
            if (!vertices.containsKey(v))
                vertices.put(v, new HashSet<>());
            vertices.get(u).add(new Pair<>(v,weight));
        }

        PriorityQueue<Pair<Integer,int[]>> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair<>(0, new int[]{U,-1}));

        while(!priorityQueue.isEmpty()) {
            Pair<Integer,int []> pair = priorityQueue.remove();
            int minDist=pair.key, minDistNode = pair.value[0], stop = pair.value[1];

            if(minDistNode==V){
                return minDist;
            }

            if(stop>=stops){
                continue;
            }

            for(Pair<Integer, Integer> v : vertices.get(minDistNode)){
                priorityQueue.add(new Pair<>( v.value+minDist, new int[]{v.key, stop+1}));
            }
        }
        return -1;
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
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceDijkstra(4, flights1,0,3,1));

        int [][]flights2 = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceBellman(5, flights2,0,2,2));
        System.out.println(new _99_CheapestFlightPrice().findCheapestPriceDijkstra(5, flights2,0,2,2));
    }
}
