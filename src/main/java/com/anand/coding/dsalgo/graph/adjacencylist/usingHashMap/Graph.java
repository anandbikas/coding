package com.anand.coding.dsalgo.graph.adjacencylist.usingHashMap;

import com.anand.coding.dsalgo.disjointset.DisjointUnionSetsGeneric;
import com.anand.coding.dsalgo.graph.Edge;
import com.anand.coding.dsalgo.graph.GraphType;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import com.anand.coding.dsalgo.graph.adjacencylist.Pair;

import java.util.*;

/**
 * 1. A non-linear data structure consisting of nodes(vertices) and edges.
 *    It has set of vertices V = {0,1,2,3,4,5} and the set of edges E = {23,30,41,50,52,54}.
 *
 * 2. In Connected graph there can be one such node from which all other nodes can be traversed.
 *
 * 3. Disconnected graph is like DFS forest having collection of different sub graphs.
 *    Loop through all nodes in such cases until all are visited.
 *
 * @param <T>
 */
public class Graph<T extends Comparable<T>> {

    private final HashMap<T,LinkedList<Pair<T,Integer>>> vertices;
    private final GraphType type;

    private int size=0;

    public Graph() {
        this(GraphType.UNDIRECTED);
    }

    public Graph(GraphType type) {
        this.type = type;
        vertices = new HashMap<>();
    }

    /**
     *
     * @param node
     */
    public void insert(T node) {
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
     * @param u
     * @param v
     */
    public void removeEdge(T u, T v) {
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }
        vertices.get(u).remove(new Pair<>(v, 1));
        if (type.equals(GraphType.UNDIRECTED)) {
            vertices.get(v).remove(new Pair<>(u, 1));
        }
    }

    /**
     * Display node along with all of its adjacent nodes with weight.
     */
    public void display() {
        System.out.println("Adjacency List Graph");

        for(T u : vertices.keySet()){
            System.out.print(u + " -> ");
            vertices.get(u).forEach(v -> System.out.printf("%s(%s), ", vertices.get(v.key), v.value));
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
        if(!vertices.containsKey(u)){
            throw new IllegalArgumentException();
        }

        Queue<T> queue = new ArrayDeque<>(size);
        Set<T> visited = new HashSet<>();

        queue.add(u);
        visited.add(u);

        while(!queue.isEmpty()) {
            u = queue.remove();
            System.out.print(u + "  ");

            for(Pair<T, Integer> v: vertices.get(u)) {
                if(!visited.contains(v.key)) {
                    queue.add(v.key);
                    visited.add(v.key);
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth First Search algorithm (PreOrder)
     * For PostOrder print node after processing adjList
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
        visited.add(u);

        while(!stack.isEmpty()){
            u = stack.pop();
            System.out.print(u + "  ");

            Iterator<Pair<T, Integer>> iterator = vertices.get(u).descendingIterator();
            while (iterator.hasNext()){
                Pair<T, Integer> v = iterator.next();
                if(!visited.contains(v.key)){
                    stack.push(v.key);
                    visited.add(v.key);
                }
            }
        }
        System.out.println();
    }

    /**
     *
     * @param u
     */
    public void dfsDisplayPreOrderRec(T u) {
        System.out.println("\nDFS Display PreOrder Recursive from node: " + u);
        if (!vertices.containsKey(u)) {
            throw new IllegalArgumentException();
        }

        dfsDisplayPreOrderRec(u, new HashSet<>());
        System.out.println();
    }

    /**
     *
     * @param u
     * @param visited
     */
    private void dfsDisplayPreOrderRec(T u, Set<T> visited) {

        System.out.print(u + "  ");
        visited.add(u);

        for(Pair<T, Integer> v: vertices.get(u)){
            if(!visited.contains(v.key)){
                dfsDisplayPreOrderRec(v.key, visited);
            }
        }
    }

    /**
     *
     * @param node
     */
    public int inDegree(T node) {

        //To calculate inDegrees for all nodes, take an array instead.
        int inDegree=0;
        for(T u : vertices.keySet()) {
            for(Pair<T, Integer> v: vertices.get(u)) {
                if(v.key.equals(node)) {
                    inDegree++;
                }
            }
        }

        return inDegree;
    }

    /**
     *
     * @param u
     */
    public int outDegree(T u) {
        return vertices.get(u).size();
    }

    /**
     * Use DFS to count number of disconnected sub graphs (DFS forest).
     *
     * @return
     */
    public int countDfsForests(){

        Set<T> visited = new HashSet<>();

        int dfsForests = 0;
        for(T u : vertices.keySet()) {
            if(!visited.contains(u)) {
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
    private void countDfsForests(T u, Set<T> visited) {

        visited.add(u);

        for(Pair<T, Integer> v: vertices.get(u)) {
            if(!visited.contains(v.key)) {
                countDfsForests(v.key, visited);
            }
        }
    }

    /**
     * Use DFS to find a cycle in a directed graph
     *
     * @return
     */
    public boolean isCyclicDfsRec() {
        if(type.equals(GraphType.UNDIRECTED)){
            throw new UnsupportedOperationException();
        }

        Set<T> visited = new HashSet<>();
        for(T u : vertices.keySet()) {
            if(!visited.contains(u) && isCyclicDfsRec(u, visited, new HashSet<>())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param u
     * @param visited
     * @param inPathStack
     * @return
     */
    private boolean isCyclicDfsRec(T u, Set<T> visited, Set<T> inPathStack) {

        inPathStack.add(u);
        visited.add(u);

        for(Pair<T, Integer> v: vertices.get(u)) {
            if(inPathStack.contains(v.key)) {
                return true;
            }
            if(!visited.contains(v.key)) {
                if (isCyclicDfsRec(v.key, visited, inPathStack)) {
                    return true;
                }
            }
        }
        inPathStack.remove(u);
        return false;
    }


    /**
     * Use DFS to find a cycle in a directed graph
     *
     * @return
     */
    public boolean isCyclicDfs() {
        if(type.equals(GraphType.UNDIRECTED)){
            throw new UnsupportedOperationException();
        }

        Set<T> visited = new HashSet<>();
        for(T u : vertices.keySet()) {
            if(!visited.contains(u) && isCyclicDfs(u, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param u
     * @param visited
     * @return
     */
    private boolean isCyclicDfs(T u, Set<T> visited) {

        Stack<T> stack = new Stack<>();
        Set<T> inPathStack = new HashSet<>();

        stack.push(u);
        while (!stack.isEmpty()) {

            u = stack.pop();
            if (u==null) {
                //Means all child processed, now remove parent from pathStack
                inPathStack.remove(stack.pop());
                continue;
            }

            stack.push(u);
            stack.push(null); //Put a marker in the stack for parent placeholder.

            inPathStack.add(u);
            visited.add(u);

            for(Pair<T, Integer> v: vertices.get(u)) {
                if (inPathStack.contains(v.key)) {
                    return true; //LOOP FOUND
                }
                if(!visited.contains(v.key)) {
                    stack.push(v.key);
                }
            }
        }
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
        if(type.equals(GraphType.DIRECTED)) {
            throw new UnsupportedOperationException();
        }

        DisjointUnionSetsGeneric<T> dus = new DisjointUnionSetsGeneric<>();
        Set<T> visited = new HashSet<>();
        for(T u: vertices.keySet()) dus.insert(u);

        for(T u: vertices.keySet()) {
            visited.add(u);

            for(Pair<T, Integer> v: vertices.get(u)) {
                if(visited.contains(v.key)) {
                    continue;
                }
                if(!dus.union(u, v.key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Topological Sorting is a linear ordering of vertices where for each edge uv, vertex u comes before v.
     * Applicable for Directed Acyclic Graph (DAG)
     *
     * BFS Algorithm for topological sorting:
     *      Apply BFS algorithm to process as following
     *          1. Calculate and store inDegree of all the vertices
     *          2. Put all the vertices with inDegree=0 in a queue
     *          3. For each vertices in the queue
     *              3.1 Consider the vertex in the topological sorting list.
     *              3.2 Reduce indegree of all its children and put the one's with inDegree=0 in the queue
     *
     *  NOTE:
     *      If the result list size is less than vertices count, then there is a cycle in the graph
     *
     * @return
     */
    public List<T> topologicalSortingBfs(){

        if(type.equals(GraphType.UNDIRECTED) || isCyclicDfsRec()) {
            return null;
        }

        Queue<T> queue = new ArrayDeque<>(size);
        List<T> sortedVertices = new ArrayList<>();

        Map<T,Integer> inDegrees = new HashMap<>();
        for(T u: vertices.keySet()) {
            inDegrees.put(u, 0);
        }

        for(T u: vertices.keySet()){
            for(Pair<T, Integer> v: vertices.get(u)) {
                inDegrees.put(v.key, inDegrees.get(v.key)+1);
            }
        }

        vertices.keySet().stream().filter(u->inDegrees.get(u)==0).forEach(queue::add);

        while(!queue.isEmpty()) {
            T u = queue.remove();
            sortedVertices.add(u);

            for(Pair<T, Integer> v: vertices.get(u)) {
                //Reduce inDegree once its parent is processed.
                inDegrees.put(v.key,inDegrees.get(v.key)-1);
                if(inDegrees.get(v.key)==0) {
                    queue.add(v.key);
                }
            }
        }

        return sortedVertices;
    }

    /**
     * DFS Algorithm for topological sorting:
     *      Apply DFS (PostOrder) to process as follows
     *          1. traverse all the DFS forests
     *          2. Once all the children are processed, put the vertex in a stack
     *          3. Empty the stack and it gives topological sorting.
     *
     * @return
     */
    public List<T> topologicalSortingDfsRec() {

        if(type.equals(GraphType.UNDIRECTED) || isCyclicDfsRec()){
            return null;
        }

        Set<T> visited = new HashSet<>();
        Stack<T> sortedVerticesStack = new Stack<>();

        for(T u: vertices.keySet()) {
            if(!visited.contains(u)) {
                topologicalSortingDfsRec(u, visited, sortedVerticesStack);
            }
        }

        List<T> sortedVertices = new ArrayList<>();
        while(!sortedVerticesStack.isEmpty()) {
            sortedVertices.add(sortedVerticesStack.pop());
        }

        return sortedVertices;
    }

    /**
     *
     * @param u
     * @param visited
     * @param sortedVerticesStack
     */
    private void topologicalSortingDfsRec(T u, Set<T> visited, Stack<T> sortedVerticesStack){

        visited.add(u);

        for(Pair<T, Integer> v: vertices.get(u)){
            if(!visited.contains(v.key)){
                topologicalSortingDfsRec(v.key, visited, sortedVerticesStack);
            }
        }
        sortedVerticesStack.push(u);
    }

    /**
     * use DFS(PreOrder) to find all paths from u to v
     * problems/all-paths-from-source-to-target
     *
     * @param u
     * @param v
     * @param
     */
    public List<List<T>> findAllPathsDFSRec(T u, T v) {
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }

        List<List<T>> pathList= new ArrayList<>();
        findAllPathsDFSRec(u, v, new HashSet<>(), new ArrayList<>(), pathList);
        return pathList;
    }

    /**
     *
     * @param u
     * @param v
     * @param inPathStack
     * @param pathStack
     * @param pathList
     */
    private void findAllPathsDFSRec(T u, T v, Set<T> inPathStack, List<T> pathStack, List<List<T>> pathList) {

        pathStack.add(u);
        if(u==v) {
            pathList.add(new ArrayList<>(pathStack));
            pathStack.remove(pathStack.size()-1);
            return;
        }

        inPathStack.add(u);

        for(Pair<T, Integer> vNode: vertices.get(u)) {
            if(!inPathStack.contains(vNode.key)) {
                findAllPathsDFSRec(vNode.key, v, inPathStack, pathStack, pathList);
            }
        }

        inPathStack.remove(u);
        pathStack.remove(pathStack.size()-1);
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
        Set<T> inPathStack = new HashSet<>();

        List<T> pathStack = new ArrayList<>();
        List<List<T>> pathList = new ArrayList<>();

        stack.push(u);
        while (!stack.isEmpty()) {

            u = stack.pop();
            if (u==null) {
                //Means all child processed, now remove parent from pathStack
                u = pathStack.remove(pathStack.size()-1);
                inPathStack.remove(u);
                continue;
            }

            pathStack.add(u);
            if (u==v) {
                pathList.add(new ArrayList<> (pathStack));
                pathStack.remove(pathStack.size()-1);
                continue;
            }

            stack.push(null); //Put a marker in the stack for parent placeholder.

            inPathStack.add(u); //May not be required for DAG graph.

            Iterator<Pair<T, Integer>> iterator = vertices.get(u).descendingIterator();
            while (iterator.hasNext()) {
                Pair<T, Integer> vNode = iterator.next();
                if (inPathStack.contains(vNode.key)) {
                    continue; //Avoid LOOP
                }
                stack.push(vNode.key);
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
     *  2. Insert the smallest edge into spanning tree.
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
                    edgeList.add(new Edge<>(u, v.key, v.value));
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
            if(!minWeightedNode.equals(parent.get(minWeightedNode))) {
                mstGraph.addEdge(parent.get(minWeightedNode), minWeightedNode, weight.get(minWeightedNode));
            }

            for(Pair<T, Integer> v : vertices.get(minWeightedNode)){
                if(!selected.contains(v.key) && v.value < weight.get(v.key)){
                    weight.put(v.key, v.value);
                    parent.put(v.key, minWeightedNode);
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
            if(!minDistNode.equals(parent.get(minDistNode))) {
                mstGraph.addEdge(parent.get(minDistNode), minDistNode, weight.get(minDistNode));
            }

            for(Pair<T, Integer> v : vertices.get(minDistNode)){
                if(!selected.contains(v.key) &&
                        v.value+dist.get(minDistNode) < dist.get(v.key)){

                    dist.put(v.key, v.value+dist.get(minDistNode));
                    weight.put(v.key, v.value);
                    parent.put(v.key, minDistNode);
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

    /**
     * Dijkstra O(E log(V)) using heap/priority queue
     *
     * @param source
     * @return
     */
    public Graph<T> dijkstraShortestPathTreeHeap(T source){

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

        PriorityQueue<Pair<Integer,T>> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair<>(0, source));

        while(!priorityQueue.isEmpty()) {
            T minDistNode = priorityQueue.remove().value;
            if(selected.contains(minDistNode)){
                continue;
            }

            selected.add(minDistNode);
            if(!minDistNode.equals(parent.get(minDistNode))) {
                mstGraph.addEdge(parent.get(minDistNode), minDistNode, weight.get(minDistNode));
            }

            for(Pair<T, Integer> v : vertices.get(minDistNode)){
                if(!selected.contains(v.key) &&
                        v.value+dist.get(minDistNode) < dist.get(v.key)){

                    dist.put(v.key, v.value+dist.get(minDistNode));
                    weight.put(v.key, v.value);
                    parent.put(v.key, minDistNode);
                    priorityQueue.add(new Pair<>(dist.get(v.key), v.key));

                }
            }
        }
        return mstGraph;
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

}
