package com.anand.coding.dsalgo.graph.adjacencylist.usingHashMap;

import com.anand.coding.dsalgo.disjointset.DisjointUnionSetsGeneric;
import com.anand.coding.dsalgo.graph.Edge;
import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.graph.adjacencylist.Pair;
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
public class Graph<T extends Comparable<T>> {

    private HashMap<T,LinkedList<Pair<T,Integer>>> vertices;

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
        vertices = new HashMap<>();
        this.type = type;
    }

    /**
     *
     * @param node
     */
    public void insert(T node){
        if(!vertices.containsKey(node)) {
            vertices.put(node, new LinkedList<>());
            size++;
        }
    }

    /**
     *
     * @param u
     * @param v
     */
    public void removeEdge(T u, T v){
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }

        vertices.get(u).remove(new Pair<>(v, 1));
        if(type.equals(GraphType.UNDIRECTED)) {
            vertices.get(v).remove(new Pair<>(u, 1));
        }
    }

    /**
     *
     * @param u
     * @param v
     */
    public void addEdge(T u, T v) {
        addEdge(u, v, 1);
    }

    /**
     *
     * @param u
     * @param v
     * @param wt
     */
    public void addEdge(T u, T v, int wt) {
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }

        vertices.get(u).addLast(new Pair<>(v, wt));
        if (type.equals(GraphType.UNDIRECTED)) {
            vertices.get(v).addLast(new Pair<>(u, wt));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                ", type=" + type +
                ", size=" + size +
                '}';
    }

    /**
     *
     */
    public void display(){
        System.out.println("Adjacency List Graph");

        for(T u : vertices.keySet()){
            System.out.print(u + " -> ");

            for(Pair<T, Integer> child: vertices.get(u)){
                System.out.print(String.format("%s, ", child.getKey()));
            }
            System.out.println();
        }
    }

    /**
     *
     */
    public void displayWeighted(){
        System.out.println("Adjacency List Graph");


        for(T u : vertices.keySet()){
            System.out.print(u + " -> ");

            for(Pair<T, Integer> child: vertices.get(u)){
                System.out.print(String.format("%s(%s), ", child.getKey(), child.getValue()));

            }
            System.out.println();
        }
    }

    /**
     * Breath First Search algorithm (similar to levelOrderTraversal of a tree)
     *
     * @param u
     */
    public void bfsDisplay(T u) {
        System.out.println("BFS Display from node: " + u);
        if (!vertices.containsKey(u)) {
            throw new IllegalArgumentException();
        }

        Queue<T> queue = new ArrayDeque<>(size);
        Set<T> visited = new HashSet<>();

        queue.add(u);

        while(!queue.isEmpty()){

            u = queue.remove();
            System.out.print(u + "  ");
            visited.add(u);

            for(Pair<T, Integer> child: vertices.get(u)){
                if(!visited.contains(child.getKey())){
                    queue.add(child.getKey());
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
    public void dfsDisplayPreOrder(T u) {
        System.out.println("DFS Display PreOrder from node: " + u);
        if (!vertices.containsKey(u)) {
            throw new IllegalArgumentException();
        }

        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        stack.push(u);

        while(!stack.isEmpty()){

            u = stack.pop();

            //For PostOrder printing will be done after processing adjList.
            System.out.print(u + "  ");
            visited.add(u);

            Iterator<Pair<T, Integer>> iterator = vertices.get(u).descendingIterator();

            while (iterator.hasNext()){
                Pair<T, Integer> child = iterator.next();
                if(!visited.contains(child.getKey())){
                    stack.push(child.getKey());
                }
            }
        }
        System.out.println();
    }

    /**
     *
     * @param u
     */
    public void dfsDisplayPreOrderRec(T u){
        System.out.println("\nDFS Display PreOrder Recursive from node: " + u);
        if (!vertices.containsKey(u)) {
            throw new IllegalArgumentException();
        }

        Set<T> visited = new HashSet<>();
        dfsDisplayPreOrderRec(u, visited);
        System.out.println();
    }

    /**
     * @param u
     * @param visited
     */
    private void dfsDisplayPreOrderRec(T u, Set<T> visited){

        if(visited.contains(u)) {
            return;
        }

        //For PostOrder printing will be done after processing adjList.
        System.out.print(u + "  ");
        visited.add(u);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.contains(child.getKey())){
                dfsDisplayPreOrderRec(child.getKey(), visited);
            }
        }
    }

    /**
     * Use DFS to count number of disconnected sub graphs.
     *
     * @return
     */
    public int countDfsForests(){

        Set<T> visited = new HashSet<>();

        int dfsForests = 0;
        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u : vertices.keySet()){
            if(!visited.contains(u)){
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
    private void countDfsForests(T u, Set<T> visited){

        if(visited.contains(u)) {
            return;
        }

        visited.add(u);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.contains(child.getKey())){
                countDfsForests(child.getKey(), visited);
            }
        }
    }


    /**
     *
     * @param u
     */
    public int outDegree(T u) {
        return vertices.get(u).size();
    }

    /**
     *
     * @param u
     */
    public int inDegree(T u) {

        //To calculate inDegrees for all nodes, take an array instead.
        int inDegree=0;

        for(T U : vertices.keySet()){
            for(Pair<T, Integer> child: vertices.get(U)) {
                if(child.getKey().equals(u)) {
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

        Set<T> visited = new HashSet<>();
        Set<T> inRecStack = new HashSet<>();

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u : vertices.keySet()){
            if(!visited.contains(u) && isCyclicDfsRec(u, visited, inRecStack)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param u
     * @param visited
     * @param inRecStack
     * @return
     */
    private boolean isCyclicDfsRec(T u, Set<T> visited, Set<T> inRecStack){

        if(inRecStack.contains(u)){
            return true;
        }

        if(visited.contains(u)) {
            return false;
        }

        inRecStack.add(u);
        visited.add(u);

        for(Pair<T, Integer> child: vertices.get(u)){
            //Note: no need to check visited here
            if(isCyclicDfsRec(child.getKey(), visited, inRecStack)){
                return true;

            }
        }
        inRecStack.remove(u);
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

        DisjointUnionSetsGeneric<T> dus = new DisjointUnionSetsGeneric<>();
        for(T u: vertices.keySet()) {
            dus.insert(u);
        }

        Set<T> visited = new HashSet<>();

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u: vertices.keySet()) {
            visited.add(u);

            for(Pair<T, Integer> child: vertices.get(u)){
                if(visited.contains(child.getKey())){
                    continue;
                }

                T leftEnd = dus.find(u);
                T rightEnd = dus.find(child.getKey());

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
     * @return
     */
    public List<T> topologicalSortingBfs(){

        if(type.equals(GraphType.UNDIRECTED) || isCyclicDfsRec()){
            return null;
        }

        Queue<T> queue = new ArrayDeque<>(size);
        List<T> topologicallySortedVertices = new ArrayList<>();

        Map<T,Integer> inDegrees = new HashMap<>();
        for(T u: vertices.keySet()) {
            inDegrees.put(u, 0);
        }

        for(T u: vertices.keySet()){
            for(Pair<T, Integer> child: vertices.get(u)) {
                inDegrees.put(child.getKey(), inDegrees.get(child.getKey())+1);
            }
        }

        for(T u: vertices.keySet()){
            if(inDegrees.get(u)==0){
                queue.add(u);
            }
        }

        while(!queue.isEmpty()){
            T u = queue.remove();
            topologicallySortedVertices.add(u);

            for(Pair<T, Integer> child: vertices.get(u)){
                //Reduce indegree once its parent is processed.
                inDegrees.put(child.getKey(),inDegrees.get(child.getKey())-1);
                if(inDegrees.get(child.getKey()) == 0){
                    queue.add(child.getKey());
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

        Set<T> visited = new HashSet<>();
        Stack<T> topologicalVertexStack = new Stack<>();

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u: vertices.keySet()) {
            if(!visited.contains(u)) {
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
    private void topologicalSortingDfsRec(T u, Set<T> visited, Stack<T> topologicalVertexStack){

        if(visited.contains(u)) {
            return;
        }
        visited.add(u);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.contains(child.getKey())){
                topologicalSortingDfsRec(child.getKey(), visited, topologicalVertexStack);
            }
        }
        topologicalVertexStack.push(u);
    }

    /**
     * use DFS(PreOrder) to find all paths from u to v
     *
     * @param u
     * @param v
     * @param
     */
    public List<List<T>> findAllPathsDFSRec(T u, T v){
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }

        Set<T> visited = new HashSet<>();
        Stack<T> pathStack = new Stack<>();

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
    private void findAllPathsDFSRec(T u, T v, Set<T> visited, Stack<T> pathStack, List<List<T>> pathList){

        if(visited.contains(u)) {
            return;
        }

        pathStack.push(u);

        if(u.equals(v)){
            pathList.add(Arrays.asList((T[])pathStack.toArray()));

            pathStack.pop();
            return;
        }

        visited.add(u);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.contains(child.getKey())){
                findAllPathsDFSRec(child.getKey(), v, visited, pathStack, pathList);
            }
        }

        pathStack.pop();
    }

    /**
     * use DFS(PostOrder) to find all paths from u to v
     *
     * @param u
     * @param v
     * @param
     */
    public List<List<T>> findAllPathsDFS(T u, T v) {
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }

        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        Stack<T> pathStack = new Stack<>();

        List<List<T>> pathList = new ArrayList<>();

        stack.push(u);

        while (!stack.isEmpty()) {

            u = stack.pop();
            if (u == null) {
                //Means all childs processed, now remove parent from pathStack
                pathStack.pop();
                continue;
            }

            pathStack.push(u);

            if (u.equals(v)) {
                pathList.add(Arrays.asList((T[])pathStack.toArray()));
                pathStack.pop();
                continue;
            }

            visited.add(u);

            //Put a marker in stack for parent place holder.
            stack.push(null);

            Iterator<Pair<T, Integer>> iterator = vertices.get(u).descendingIterator();
            while (iterator.hasNext()) {
                Pair<T, Integer> child = iterator.next();
                if (!visited.contains(child.getKey())) {
                    stack.push(child.getKey());
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
        DisjointUnionSetsGeneric<T> dus = new DisjointUnionSetsGeneric<>();
        List<Edge<T>> edgeList = new ArrayList<>();

        Set<T> visited = new HashSet<>();
        for(T u: vertices.keySet()) {
            visited.add(u);
            mstGraph.insert(u);
            dus.insert(u);

            Iterator<Pair<T, Integer>> iterator = vertices.get(u).iterator();
            while (iterator.hasNext()) {
                Pair<T, Integer> v = iterator.next();
                if(!visited.contains(v)) {
                    edgeList.add(new Edge<>(u, v.getKey(), v.getValue()));
                }
            }
        }
        Collections.sort(edgeList);

        int edgeCount=0;
        for(Edge<T> edge: edgeList){
            if(edgeCount >= size-1){
                break;
            }

            T leftEnd = dus.find(edge.u);
            T rightEnd = dus.find(edge.v);

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

        Map<T, Integer> weight = new HashMap<>();
        Map<T, T> parent = new HashMap<>();
        Set<T> selected = new HashSet<>();

        for(T u: vertices.keySet()) {
            weight.put(u,INF);  parent.put(u,u);
            mstGraph.insert(u);
        }

        T minWeightedNode = vertices.keySet().iterator().next();
        weight.put(minWeightedNode, 0);

        while(minWeightedNode !=null) {

            selected.add(minWeightedNode);
            if(parent.get(minWeightedNode) != minWeightedNode) {
                mstGraph.addEdge(parent.get(minWeightedNode), minWeightedNode, weight.get(minWeightedNode));
            }

            for(Pair<T, Integer> v : vertices.get(minWeightedNode)){
                if(!selected.contains(v.getKey()) && v.getValue() < weight.get(v.getKey())){
                    weight.put(v.getKey(), v.getValue());
                    parent.put(v.getKey(), minWeightedNode);
                }
            }

            int minValue=INF;
            minWeightedNode=null;
            for(T u: vertices.keySet()) {
                if(!selected.contains(u) && weight.get(u)<minValue) {
                    minWeightedNode = u;
                    minValue = weight.get(u);
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
    public Graph<T> dijkstraShortestPathTree(T source){

        int INF= Integer.MAX_VALUE;
        Graph<T> mstGraph = new Graph<>(this.type);

        if(size==0){
            return mstGraph;
        }

        Map<T, Integer> dist = new HashMap<>();
        Map<T, Integer> weight = new HashMap<>();   // Required only for building SPT graph
        Map<T, T> parent = new HashMap<>();         // Required only for building SPT graph
        Set<T> selected = new HashSet<>();

        for(T u: vertices.keySet()) {
            dist.put(u,INF);
            weight.put(u, 0);
            parent.put(u,u);
            mstGraph.insert(u);
        }

        dist.put(source, 0);

        T minDistNode = source;
        while(minDistNode !=null) {

            selected.add(minDistNode);
            if(parent.get(minDistNode) != minDistNode) {
                mstGraph.addEdge(parent.get(minDistNode), minDistNode, weight.get(minDistNode));
            }

            for(Pair<T, Integer> v : vertices.get(minDistNode)){
                if(!selected.contains(v.getKey()) &&
                        v.getValue()+dist.get(minDistNode) < dist.get(v.getKey())){

                    dist.put(v.getKey(), v.getValue()+dist.get(minDistNode));
                    weight.put(v.getKey(), v.getValue());
                    parent.put(v.getKey(), minDistNode);
                }
            }

            int minValue=INF;
            minDistNode=null;
            for(T u: vertices.keySet()) {
                if(!selected.contains(u) && dist.get(u)<minValue) {
                    minDistNode = u;
                    minValue = dist.get(u);
                }
            }
        }
        return mstGraph;
    }
}
