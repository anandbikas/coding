package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.disjointset.DisjointUnionSets;
import com.anand.coding.dsalgo.graph.Edge;
import com.anand.coding.dsalgo.graph.GraphType;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * 1. A non-linear data structure consisting of nodes(vertices) and edges.
 *    It has set of vertices V = {0,1,2,3,4,5} and the set of edges E = {23,30,41,50,52,54}.
 *
 * 2. In Connected graph there can be one such node from which all other nodes can be traversed.
 *
 * 3. Disconnected graph is like collection of different sub graphs.
 *
 * @param <T>
 */
public class Graph<T> {

    private ArrayList<T> vertices;

    // Pair<Integer,Integer> represents childNode and weight pair
    private ArrayList<LinkedList<Pair<Integer,Integer>>> adjListArray;

    private GraphType type;
    private int size=0;

    /**
     *
     */
    public Graph(){
        this(GraphType.UNDIRECTED);
    }

    /**
     *
     * @param type
     */
    public Graph(GraphType type){
        vertices = new ArrayList<>();
        adjListArray = new ArrayList<>();
        this.type = type;
    }

    /**
     *
     * @param node
     */
    public void insert(T node){
        vertices.add(size, node);
        adjListArray.add(size, new LinkedList<>());
        size++;
    }

    /**
     *
     * @param u
     * @param v
     */
    public void removeEdge(Integer u, Integer v){
        if(u>=size || v >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        adjListArray.get(u).remove(new Pair<>(v, 1));
        if(type.equals(GraphType.UNDIRECTED)) {
            adjListArray.get(v).remove(new Pair<>(u, 1));
        }
    }

    /**
     *
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        addEdge(u, v, 1);
    }

    /**
     *
     * @param u
     * @param v
     * @param wt
     */
    public void addEdge(int u, int v, int wt) {
        if (u >= size || v >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        adjListArray.get(u).addLast(new Pair<>(v, wt));
        if (type.equals(GraphType.UNDIRECTED)) {
            adjListArray.get(v).addLast(new Pair<>(u, wt));
        }
    }

    /**
     *
     */
    public void display(){
        System.out.println("Adjacency List Graph");

        for(int u=0; u<size; u++){
            System.out.print(vertices.get(u) + " -> ");
            for(Pair<Integer, Integer> v: adjListArray.get(u)){
                System.out.print(String.format("%s, ", vertices.get(v.key)));
            }
            System.out.println();
        }
    }

    /**
     *
     */
    public void displayWeighted(){
        System.out.println("Adjacency List Graph");

        for(int u=0; u<size; u++){
            System.out.print(vertices.get(u) + " -> ");
            for(Pair<Integer, Integer> v: adjListArray.get(u)){
                System.out.print(String.format("%s(%s), ", vertices.get(v.key), v.value));
            }
            System.out.println();
        }
    }

    /**
     * Breath First Search algorithm (similar to levelOrderTraversal of a tree)
     *
     * @param u
     */
    public void bfsDisplay(int u) {
        System.out.println("BFS Display from index: " + u);
        if(u>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Queue<Integer> queue = new ArrayDeque<>(size);
        boolean []visited = new boolean[size];

        queue.add(u);
        visited[u]=true;

        while(!queue.isEmpty()){
            u = queue.remove();
            System.out.print(vertices.get(u) + "  ");

            for(Pair<Integer, Integer> v: adjListArray.get(u)){
                if(!visited[v.key]){
                    queue.add(v.key);
                    visited[v.key] = true;
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth First Search algorithm
     *
     * @param u
     */
    public void dfsDisplayPreOrder(int u) {
        System.out.println("DFS Display PreOrder from index: " + u);
        if(u>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Stack<Integer> stack = new Stack<>();
        boolean []visited = new boolean[size];

        stack.push(u);
        visited[u]=true;

        while(!stack.isEmpty()){
            u = stack.pop();

            //For PostOrder printing will be done after processing adjList.
            System.out.print(vertices.get(u) + "  ");

            Iterator<Pair<Integer, Integer>> iterator = adjListArray.get(u).descendingIterator();
            while (iterator.hasNext()){
                Pair<Integer, Integer> v = iterator.next();
                if(!visited[v.key]){
                    stack.push(v.key);
                    visited[v.key] = true;
                }
            }
        }
        System.out.println();
    }

    /**
     *
     * @param u
     */
    public void dfsDisplayPreOrderRec(int u){
        System.out.println("\nDFS Display PreOrder Recursive from index: " + u);
        if(u>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        boolean []visited = new boolean[size];
        dfsDisplayPreOrderRec(u, visited);
        System.out.println();
    }

    /**
     * @param u
     * @param visited
     */
    private void dfsDisplayPreOrderRec(int u, boolean []visited){

        //For PostOrder printing will be done after processing adjList.
        System.out.print(vertices.get(u) + "  ");
        visited[u] = true;

        for(Pair<Integer, Integer> v: adjListArray.get(u)){
            if(!visited[v.key]){
                dfsDisplayPreOrderRec(v.key, visited);
            }
        }
    }

    /**
     * Use DFS to count number of disconnected sub graphs.
     *
     * @return
     */
    public int countDfsForests(){

        boolean []visited = new boolean[size];

        int dfsForests = 0;
        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(int u=0; u<size; u++) {
            if(!visited[u]){
                countDfsForests(u, visited);
                dfsForests++;
            }
        }
        return dfsForests;
    }

    /**
     *
     * @param u
     * @param visited
     * @return
     */
    private void countDfsForests(int u, boolean []visited){

        visited[u] = true;

        for(Pair<Integer, Integer> v: adjListArray.get(u)){
            if(!visited[v.key]){
                countDfsForests(v.key, visited);
            }
        }
    }


    /**
     *
     * @param node
     */
    public int outDegree(int node) {
        return adjListArray.get(node).size();
    }

    /**
     *
     * @param node
     */
    public int inDegree(int node) {

        //To calculate inDegrees for all nodes, take an array instead.
        int inDegree=0;

        for(int u=0; u<size; u++){
            for(Pair<Integer, Integer> v: adjListArray.get(u)) {
                if(v.key==node) {
                   inDegree++;
                }
            }
        }
        return inDegree;
    }


    /**
     * Use DFS to find a cycle in a directed graph
     *
     * @return
     */
    public boolean isCyclicDfsRec(){

        if(type.equals(GraphType.UNDIRECTED)){
            throw new NotImplementedException();
        }

        boolean []visited = new boolean[size];
        boolean []visiting = new boolean[size];

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(int u=0; u<size; u++) {
            if(!visited[u] && isCyclicDfsRec(u, visited, visiting)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param u
     * @param visited
     * @param visiting
     * @return
     */
    private boolean isCyclicDfsRec(int u, boolean []visited, boolean []visiting){

        visiting[u] = true;
        visited[u] = true;

        for(Pair<Integer, Integer> v: adjListArray.get(u)){
            if(visiting[v.key]){
                return true;
            }
            if(!visited[v.key]) {
                if (isCyclicDfsRec(v.key, visited, visiting)) {
                    return true;
                }
            }
        }
        visiting[u] = false;
        return false;
    }

    /**
     * Use DisjointSets Union-Find to find a cycle in an undirected graph
     * Consider all the edges(u-v) in the graph,
     * if they are already in the same disjoint set, there is a loop
     * Else perform union on u and v.
     *
     * @return
     */
    public boolean isCyclicUnionFind() {

        if(type.equals(GraphType.DIRECTED)){
            throw new NotImplementedException();
        }

        DisjointUnionSets dus = new DisjointUnionSets(size);
        boolean []visited = new boolean[size];

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(int u=0; u<size; u++) {
            visited[u] = true;

            for(Pair<Integer, Integer> v: adjListArray.get(u)){
                if(visited[v.key]){
                    continue;
                }

                int leftEnd = dus.find(u);
                int rightEnd = dus.find(v.key);

                if(leftEnd==rightEnd){
                    return true;
                } else{
                    dus.union(leftEnd, rightEnd);
                }
            }
        }
        return false;
    }

    /**
     * BFS algorithm for topological sorting
     * Applicable for Directed Acyclic Graph (DAG)
     *
     * Algorithm:
     *      Apply BFS algorithm to process as following
     *          1. Calculate and store indegree of all the vertices
     *          2. Put all the vertices with indegree=0 in a queue
     *          3. For each vertices in the queue
     *              3.1 Consider the vertex in the topological sorting list.
     *              3.2 Reduce indegree of all its children and put the one's with indegree=0 in the queue
     *
     *  NOTE: If the result list size is less than vertices count, then there is a cycle in the graph
     * @return
     */
    public List<T> topologicalSortingBfs(){

        if(type.equals(GraphType.UNDIRECTED) || isCyclicDfsRec()){
            return null;
        }

        Queue<Integer> queue = new ArrayDeque<>(size);
        List<T> topologicallySortedVertices = new ArrayList<>();

        int[] inDegrees = new int[size];

        for(int u=0; u<size; u++){
            for(Pair<Integer, Integer> v: adjListArray.get(u)) {
                inDegrees[v.key]++;
            }
        }

        for(int u=0; u<size; u++){
            if(inDegrees[u]==0){
                queue.add(u);
            }
        }

        while(!queue.isEmpty()){
            int u = queue.remove();
            topologicallySortedVertices.add(vertices.get(u));

            for(Pair<Integer, Integer> v: adjListArray.get(u)){
                //Reduce indegree once its parent is processed.
                if(--inDegrees[v.key] == 0){
                    queue.add(v.key);
                }
            }
        }

        return topologicallySortedVertices;
    }

    /**
     *
     * It is a linear ordering of vertices where for each edge uv, vertex u comes before v in the ordering.
     *
     * DFS (PostOrder) algorithm for topological sorting
     * Applicable for Directed Acyclic Graph (DAG)
     *
     * Algorithm:
     *      Apply DFS (PostOrder) algorithm to process as following
     *      1. traverse all the DFS forests
     *      2. Once all the children are processed, put the vertex in a stack
     *      3. Empty the stack and it gives topological sorting.
     *
     * @return
     */
    public List<T> topologicalSortingDfsRec(){

        if(type.equals(GraphType.UNDIRECTED) || isCyclicDfsRec()){
            return null;
        }

        boolean []visited = new boolean[size];
        Stack<T> topologicalVertexStack = new Stack<>();

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(int u=0; u<size; u++) {
            if(!visited[u]) {
                topologicalSortingDfsRec(u, visited, topologicalVertexStack);
            }
        }

        List<T> topologicallySortedVertices = new ArrayList<>();
        while(!topologicalVertexStack.isEmpty()){
            topologicallySortedVertices.add(topologicalVertexStack.pop());
        }

        return topologicallySortedVertices;
    }

    /**
     *
     * @param u
     * @param visited
     * @param topologicalVertexStack
     */
    private void topologicalSortingDfsRec(int u, boolean []visited, Stack<T> topologicalVertexStack){

        visited[u] = true;

        for(Pair<Integer, Integer> v: adjListArray.get(u)){
            if(!visited[v.key]){
                topologicalSortingDfsRec(v.key, visited, topologicalVertexStack);
            }
        }
        topologicalVertexStack.push(vertices.get(u));
    }

    /**
     * use DFS(PreOrder) to find all paths from u to v
     *
     * @param u
     * @param v
     * @param
     */
    public List<List<T>> findAllPathsDFSRec(int u, int v){
        if(u>=size || v >=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        boolean []visited = new boolean[size];
        LinkedList<T> pathStack = new LinkedList<>();

        List<List<T>> pathList= new ArrayList<>();

        findAllPathsDFSRec(u, v, visited, pathStack, pathList);
        return pathList;
    }

    /**
     *
     * @param u
     * @param v
     * @param visited
     */
    private void findAllPathsDFSRec(int u, int v, boolean []visited, LinkedList<T> pathStack, List<List<T>> pathList){

        pathStack.addLast(vertices.get(u));

        if(u == v){
            pathList.add((List<T>)pathStack.clone());

            pathStack.removeLast();
            return;
        }

        visited[u] = true;

        for(Pair<Integer, Integer> vNode: adjListArray.get(u)){
            if(!visited[vNode.key]){
                findAllPathsDFSRec(vNode.key, v, visited, pathStack, pathList);
            }
        }

        pathStack.removeLast();
    }

    /**
     * use DFS(PostOrder) to find all paths from u to v
     *
     * @param u
     * @param v
     * @param
     */
    public List<List<T>> findAllPathsDFS(int u, int v) {
        if (u >= size || v >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[size];

        LinkedList<T> pathStack = new LinkedList<>();
        List<List<T>> pathList = new ArrayList<>();

        stack.push(u);

        while (!stack.isEmpty()) {

            u = stack.pop();
            if (u == -1) {
                //Means all childs processed, now remove parent from pathStack
                pathStack.removeLast();
                continue;
            }

            pathStack.addLast(vertices.get(u));

            if (u == v) {
                pathList.add((List<T>)pathStack.clone());
                pathStack.removeLast();
                continue;
            }

            visited[u] = true;

            //Put a marker in stack for parent place holder.
            stack.push(-1);

            Iterator<Pair<Integer, Integer>> iterator = adjListArray.get(u).descendingIterator();
            while (iterator.hasNext()) {
                Pair<Integer, Integer> vNode = iterator.next();
                if (!visited[vNode.key]) {
                    stack.push(vNode.key);
                }
            }
        }
        return pathList;
    }



    /**
     * Kruskal MST: Greedy Algorithm
     * -> In a connected and undirected graph there can be many sub graphs (spanning tree) which connects all
     *    the vertices together with exactly V-1 edges.
     *
     * -> In a weighted, connected and undirected graph, Minimum (Weight) Spanning Tree (MST) is one of the spanning trees
     *    having minimum total weight.
     *
     *  1. Sort all edges in increasing order of their weight
     *  2. Insert smallest edge into spanning tree.
     *  3. If the newly inserted edge forms a cycle, discard it.
     *
     *  Use union-find to detect cycle in an undirected graph.
     *
     * @return
     */
    public Graph<T> kruskalsMinimumSpanningTree(){

        if(type.equals(GraphType.DIRECTED)){
            throw new NotImplementedException();
        }

        Graph<T> mstGraph = new Graph<>();
        DisjointUnionSets dus = new DisjointUnionSets(size);
        List<Edge<Integer>> edgeList = new ArrayList<>();

        boolean []visited = new boolean[size];
        for(Integer u=0; u<size; u++){
            visited[u]=true;
            mstGraph.insert(vertices.get(u));

            Iterator<Pair<Integer, Integer>> iterator = adjListArray.get(u).iterator();
            while (iterator.hasNext()) {
                Pair<Integer, Integer> v = iterator.next();
                if(!visited[v.key]){
                    edgeList.add(new Edge<>(u, v.key, v.value));
                }
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
                mstGraph.addEdge(edge.u, edge.v, edge.weight);
                dus.union(leftEnd, rightEnd);
            }
        }
        return mstGraph;
    }

    /**
     * Prim's MST: Greedy Algorithm
     * -> Unlike Kruskal, Prims operates on vertices rather than edges.
     *
     *  1. Create a new set of vertices with a weight INFINITY to all. Assign weight 0 to the first element for it to be picked up.
     *  3. Insert min weighted node u to the MST.
     *  3. Edge Relaxation: Update the weight of all the adjacent nodes v of u to minimum of current_weight_v and weight(u,v) and change its parent if required.
     *
     *  Prims vs Kruskal
     *      * ========================================================
     *      * 1.   Prims operates on vertices and can start with any node.
     *      *      Kruskal's prepares sorted weight edge list and starts with the minimum weight edge.
     *      *
     *      * 2.   Prims works only connected graph.
     *      *      Kruskal's can work on disconnected/forest graph.
     *      *
     *      * 3.   Prims is suitable for dense graph.
     *      *      Kruskal runs faster in sparse graphs.
     *
     * @return
     */
    public Graph<T> primsMinimumSpanningTree(){

        if(type.equals(GraphType.DIRECTED)){
            throw new NotImplementedException();
        }
        int INF= Integer.MAX_VALUE;
        Graph<T> mstGraph = new Graph<>(GraphType.UNDIRECTED);

        if(size==0){
            return mstGraph;
        }

        int [] weight = new int[size];
        int [] parent = new int[size];
        boolean [] selected = new boolean[size];

        for(int i=0; i<size; i++) {
            weight[i]=INF; parent[i]=i;
            mstGraph.insert(vertices.get(i));
        }
        weight[0]=0;

        int minWeightedNode = 0;
        while(minWeightedNode !=-1) {

            selected[minWeightedNode]=true;
            if(parent[minWeightedNode] != minWeightedNode) {
                mstGraph.addEdge(parent[minWeightedNode], minWeightedNode, weight[minWeightedNode]);
            }

            for(Pair<Integer, Integer> v : adjListArray.get(minWeightedNode)){
                if(!selected[v.key] && v.value < weight[v.key]){
                    weight[v.key] = v.value;
                    parent[v.key] = minWeightedNode;
                }
            }

            int minValue=INF;
            minWeightedNode=-1;
            for(int u=0; u<size; u++) {
                if(!selected[u] && weight[u]<minValue) {
                    minWeightedNode = u;
                    minValue = weight[u];
                }
            }
        }
        return mstGraph;
    }

    /**
     * DijkstraShortestPathTree: Greedy Algorithm
     * -> This is same as Prim's except it calculates total distance instead of node's weight.
     * -> It works for both directed and undirected graphs, except for NEGATIVE EDGES.
     *
     *  1. Create a new set of vertices with dist INFINITY to all. Assign dist 0 to the SOURCE element for it to be picked up.
     *  3. Insert min distance node u to the MST.
     *  3. Edge Relaxation: Update the dist of all the adjacent nodes v of u to minimum of current_dist_v and weight(u,v)+current_dist_u and change its parent if required.
     *
     * @return
     */
    public Graph<T> dijkstraShortestPathTree(int source){

        int INF= Integer.MAX_VALUE;
        Graph<T> mstGraph = new Graph<>(this.type);

        if(size==0){
            return mstGraph;
        }

        int [] dist = new int[size];
        int [] weight = new int[size];      // Required only for building SPT graph
        int [] parent = new int[size];      // Required only for building SPT graph
        boolean [] selected = new boolean[size];

        for(int i=0; i<size; i++) {
            dist[i]=INF; weight[i]=0; parent[i]=i;
            mstGraph.insert(vertices.get(i));
        }
        dist[source]=0;

        int minDistNode = source;
        while(minDistNode !=-1) {

            selected[minDistNode]=true;
            if(parent[minDistNode] != minDistNode) {
                mstGraph.addEdge(parent[minDistNode], minDistNode, weight[minDistNode]);
            }

            for(Pair<Integer, Integer> v : adjListArray.get(minDistNode)){
                if(!selected[v.key] &&
                        v.value+dist[minDistNode] < dist[v.key]){

                    dist[v.key] = v.value+dist[minDistNode];
                    weight[v.key] = v.value;
                    parent[v.key] = minDistNode;
                }
            }

            int minValue=INF;
            minDistNode=-1;
            for(int u=0; u<size; u++) {
                if(!selected[u] && dist[u]<minValue) {
                    minDistNode = u;
                    minValue = dist[u];
                }
            }
        }
        return mstGraph;
    }

    /**
     * Dijkstra O(E log(V)) using heap/priority queue
     *
     * @param source
     * @return
     */
    public Graph<T> dijkstraShortestPathTreeHeap(int source){

        int INF= Integer.MAX_VALUE;
        Graph<T> mstGraph = new Graph<>(this.type);

        if(size==0){
            return mstGraph;
        }

        int [] dist = new int[size];
        int [] weight = new int[size];      // Required only for building SPT graph
        int [] parent = new int[size];      // Required only for building SPT graph
        boolean [] selected = new boolean[size];

        for(int i=0; i<size; i++) {
            dist[i]=INF; weight[i]=0; parent[i]=i;
            mstGraph.insert(vertices.get(i));
        }
        dist[source]=0;

        PriorityQueue<Pair<Integer,Integer>> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair<>(0, source));

        while(!priorityQueue.isEmpty()) {
            int minDistNode = priorityQueue.remove().value;
            if(selected[minDistNode]){
                continue;
            }

            selected[minDistNode]=true;
            if(parent[minDistNode] != minDistNode) {
                mstGraph.addEdge(parent[minDistNode], minDistNode, weight[minDistNode]);
            }

            for(Pair<Integer, Integer> v : adjListArray.get(minDistNode)){
                if(!selected[v.key] &&
                        v.value+dist[minDistNode] < dist[v.key]){

                    dist[v.key] = v.value+dist[minDistNode];
                    weight[v.key] = v.value;
                    parent[v.key] = minDistNode;
                    priorityQueue.add(new Pair<>(dist[v.key], v.key));
                }
            }
        }
        return mstGraph;
    }

    /**
     * Dijkstra does not work with negative edges.
     *
     * Bellman's idea is to relax all the edges exactly v-1 times, so that all the minimum possible distance can be considered..
     * The i'th iteration gives shortest paths upto i edges long.
     *
     * Again this does not work for graphs having negative edge cycle.
     *
     * @return
     */
    public Graph<T> bellmanShortestPathTree(int source){

        int INF= Integer.MAX_VALUE;
        Graph<T> mstGraph = new Graph<>(this.type);

        if(size==0){
            return mstGraph;
        }

        int [] dist = new int[size];
        int [] weight = new int[size];      // Required only for building SPT graph
        int [] parent = new int[size];      // Required only for building SPT graph

        for(int i=0; i<size; i++) {
            dist[i]=INF; weight[i]=0; parent[i]=i;
            mstGraph.insert(vertices.get(i));
        }
        dist[source]=0;

        for(int i=0; i<size-1; i++) {

            for (int u=0; u<size;u++) {
                if(dist[u]==INF){
                    continue;
                }
                for (Pair<Integer, Integer> v : adjListArray.get(u)) {

                    if (v.value + dist[u] < dist[v.key]) {
                        dist[v.key] = v.value + dist[u];
                        weight[v.key] = v.value;
                        parent[v.key] = u;
                    }
                }
            }
        }

        for(int v=0; v<size; v++){
            if(parent[v]!=v){
                mstGraph.addEdge(parent[v], v, weight[v]);
            }
        }
        return mstGraph;
    }
}
