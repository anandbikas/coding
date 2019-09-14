package com.anand.coding.dsalgo.graph.adjacencylist.usingHashMap;

import com.anand.coding.dsalgo.disjointset.DisjointUnionSetsGeneric;
import com.anand.coding.dsalgo.graph.Edge;
import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.graph.adjacencylist.Pair;
import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;
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

        Queue<T> queue = new ArrayCircularQueue<>(size);
        Map<T, Boolean> visited = new HashMap<>();

        queue.insert(u);

        while(!queue.isEmpty()){

            u = queue.delete();
            System.out.print(u + "  ");
            visited.put(u,true);

            for(Pair<T, Integer> child: vertices.get(u)){
                if(!visited.containsKey(child.getKey())){
                    queue.insert(child.getKey());
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

        Stack<T> stack = new ArrayStack<>(size);
        Map<T, Boolean> visited = new HashMap<>();

        stack.push(u);

        while(!stack.isEmpty()){

            u = stack.pop();

            //For PostOrder printing will be done after processing adjList.
            System.out.print(u + "  ");
            visited.put(u,true);

            Iterator<Pair<T, Integer>> iterator = vertices.get(u).descendingIterator();

            while (iterator.hasNext()){
                Pair<T, Integer> child = iterator.next();
                if(!visited.containsKey(child.getKey())){
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

        Map<T, Boolean> visited = new HashMap<>();
        dfsDisplayPreOrderRec(u, visited);
        System.out.println();
    }

    /**
     * @param u
     * @param visited
     */
    private void dfsDisplayPreOrderRec(T u, Map<T, Boolean> visited){

        if(visited.containsKey(u)) {
            return;
        }

        //For PostOrder printing will be done after processing adjList.
        System.out.print(u + "  ");
        visited.put(u,true);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.containsKey(child.getKey())){
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

        Map<T, Boolean> visited = new HashMap<>();

        int dfsForests = 0;
        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u : vertices.keySet()){
            if(!visited.containsKey(u)){
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
    private void countDfsForests(T u, Map<T, Boolean> visited){

        if(visited.containsKey(u)) {
            return;
        }

        visited.put(u,true);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.containsKey(child.getKey())){
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

        Map<T, Boolean> visited = new HashMap<>();
        Map<T, Boolean> inRecStack = new HashMap<>();

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u : vertices.keySet()){
            if(!visited.containsKey(u) && isCyclicDfsRec(u, visited, inRecStack)){
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
    private boolean isCyclicDfsRec(T u, Map<T, Boolean> visited, Map<T, Boolean> inRecStack){

        if(inRecStack.containsKey(u)){
            return true;
        }

        if(visited.containsKey(u)) {
            return false;
        }

        inRecStack.put(u,true);
        visited.put(u,true);

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

        HashMap<T, Boolean> visited = new HashMap<>();

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u: vertices.keySet()) {
            visited.put(u,true);

            for(Pair<T, Integer> child: vertices.get(u)){
                if(visited.containsKey(child.getKey())){
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

        Queue<T> queue = new ArrayCircularQueue<>(size);
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
                queue.insert(u);
            }
        }

        while(!queue.isEmpty()){
            T u = queue.delete();
            topologicallySortedVertices.add(u);

            for(Pair<T, Integer> child: vertices.get(u)){
                //Reduce indegree once its parent is processed.
                inDegrees.put(child.getKey(),inDegrees.get(child.getKey())-1);
                if(inDegrees.get(child.getKey()) == 0){
                    queue.insert(child.getKey());
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

        Map<T, Boolean> visited = new HashMap<>();
        Stack<T> topologicalVertexStack = new ArrayStack<>(size);

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(T u: vertices.keySet()) {
            if(!visited.containsKey(u)) {
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
    private void topologicalSortingDfsRec(T u, Map<T,Boolean> visited, Stack<T> topologicalVertexStack){

        if(visited.containsKey(u)) {
            return;
        }
        visited.put(u,true);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.containsKey(child.getKey())){
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
    public List<T[]> findPathDFSRec(T u, T v){
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }

        Map<T, Boolean> visited = new HashMap<>();
        Stack<T> pathStack = new ArrayStack<>(size);

        List<T[]> pathList= new ArrayList<>();

        findPathDFSRec(u, v, visited, pathStack, pathList);
        return pathList;
    }

    /**
     *
     * @param u
     * @param v
     * @param visited
     */
    private void findPathDFSRec(T u, T v, Map<T, Boolean> visited, Stack<T> pathStack, List<T[]> pathList){

        if(visited.containsKey(u)) {
            return;
        }

        pathStack.push(u);

        if(u.equals(v)){
            pathList.add(pathStack.getAsList());

            pathStack.pop();
            return;
        }

        visited.put(u,true);

        for(Pair<T, Integer> child: vertices.get(u)){
            if(!visited.containsKey(child.getKey())){
                findPathDFSRec(child.getKey(), v, visited, pathStack, pathList);
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
    public List<T[]> findPathDFS(T u, T v) {
        if (!(vertices.containsKey(u) || vertices.containsKey(v))) {
            throw new IllegalArgumentException();
        }

        Stack<T> stack = new ArrayStack<>(size);
        Map<T, Boolean> visited = new HashMap<>();

        Stack<T> pathStack = new ArrayStack<>(size);

        List<T[]> pathList = new ArrayList<>();

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
                pathList.add(pathStack.getAsList());
                pathStack.pop();
                continue;
            }

            visited.put(u,true);

            //Put a marker in stack for parent place holder.
            stack.push(null);

            Iterator<Pair<T, Integer>> iterator = vertices.get(u).descendingIterator();
            while (iterator.hasNext()) {
                Pair<T, Integer> child = iterator.next();
                if (!visited.containsKey(child.getKey())) {
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

        List<Edge<T>> edgeList = new ArrayList<>();

        for(T u: vertices.keySet()) {
            Iterator<Pair<T, Integer>> iterator = vertices.get(u).iterator();
            while (iterator.hasNext()) {
                Pair<T, Integer> v = iterator.next();
                edgeList.add(new Edge<>(u, v.getKey(), v.getValue()));
            }
        }
        Collections.sort(edgeList);

        Graph<T> mstGraph = new Graph<>();

        for(T u: vertices.keySet()) {
            mstGraph.insert(u);
        }

        //DisjointSets: Union find for loop detection.
        DisjointUnionSetsGeneric<T> dus = new DisjointUnionSetsGeneric<>();
        for(T u: vertices.keySet()) {
            dus.insert(u);
        }

        int edgeCount=0;
        for(Edge<T> edge: edgeList){
            if(edgeCount >= size-1){
                break;
            }

            T leftEnd = dus.find(edge.getU());
            T rightEnd = dus.find(edge.getV());

            // If u,v does not create loop add it to mstGraph.
            if(leftEnd!=rightEnd){
                edgeCount++;
                mstGraph.addEdge(edge.getU(), edge.getV(), edge.getWeight());
                dus.union(leftEnd, rightEnd);
            }
        }

        return mstGraph;
    }

    /**
     * Prim's MST: Greedy Algorithm
     * -> Unlike Kruskal, Prims operates on vertices rather than edges.
     *
     *  1. Create a new set of vertices with a key value INFINITY to all. Assign key value 0 to the first element for it to be picked up.
     *  3. Insert min valued node u to the MST.
     *  3. Update the key value of all the adjacent nodes v of u to minimum of current_key_value_v and weight(u,v) and change its parent if required.
     *
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

        Map<T, Integer> weightValueMap = new HashMap<>();
        Map<T, T> parentMap = new HashMap<>();
        Map<T, Boolean> selectedMap = new HashMap<>();

        for(T u: vertices.keySet()) {
            weightValueMap.put(u,INF);
            parentMap.put(u,u);
            mstGraph.insert(u);
        }

        T minValuedNode = vertices.keySet().iterator().next();
        weightValueMap.put(minValuedNode, 0);

        while(minValuedNode !=null) {

            selectedMap.put(minValuedNode, true);
            if(parentMap.get(minValuedNode) != minValuedNode) {
                mstGraph.addEdge(parentMap.get(minValuedNode), minValuedNode, weightValueMap.get(minValuedNode));
            }

            for(Pair<T, Integer> child : vertices.get(minValuedNode)){
                if(!selectedMap.containsKey(child.getKey()) && child.getValue() < weightValueMap.get(child.getKey())){
                    weightValueMap.put(child.getKey(), child.getValue());
                    parentMap.put(child.getKey(), minValuedNode);
                }
            }

            int minValue=INF;
            minValuedNode=null;
            for(T u: vertices.keySet()) {
                if(!selectedMap.containsKey(u) && weightValueMap.get(u)<minValue) {
                    minValuedNode = u;
                    minValue = weightValueMap.get(u);
                }
            }
        }
        return mstGraph;
    }

    /**
     * DijkstraShortestPathTree: Greedy Algorithm
     * -> This is same as Prim's except it calculates distance pathValue instead of weightValue.
     * -> It works for both directed and undirected graphs
     *
     *  1. Create a new set of vertices with a key value INFINITY to all. Assign key value 0 to the SOURCE element for it to be picked up.
     *  3. Insert min valued node u to the MST.
     *  3. Update the key value of all the adjacent nodes v of u to minimum of current_key_value_v and weight(u,v)+current_key_value_u and change its parent if required.
     *
     *
     * @return
     */
    public Graph<T> dijkstraShortestPathTree(T source){

        int INF= Integer.MAX_VALUE;
        Graph<T> mstGraph = new Graph<>(this.type);

        if(size==0){
            return mstGraph;
        }

        Map<T, Integer> pathValueMap = new HashMap<>();
        Map<T, Integer> weightValueMap = new HashMap<>();
        Map<T, T> parentMap = new HashMap<>();
        Map<T, Boolean> selectedMap = new HashMap<>();

        for(T u: vertices.keySet()) {
            pathValueMap.put(u,INF);
            weightValueMap.put(u, 0);
            parentMap.put(u,u);
            mstGraph.insert(u);
        }

        pathValueMap.put(source, 0);

        T minValuedNode = source;
        while(minValuedNode !=null) {

            selectedMap.put(minValuedNode, true);
            if(parentMap.get(minValuedNode) != minValuedNode) {
                mstGraph.addEdge(parentMap.get(minValuedNode), minValuedNode, weightValueMap.get(minValuedNode));
            }

            for(Pair<T, Integer> child : vertices.get(minValuedNode)){

                if(!selectedMap.containsKey(child.getKey()) &&
                        child.getValue()+pathValueMap.get(minValuedNode) < pathValueMap.get(child.getKey())){

                    pathValueMap.put(child.getKey(), child.getValue()+pathValueMap.get(minValuedNode));
                    weightValueMap.put(child.getKey(), child.getValue());
                    parentMap.put(child.getKey(), minValuedNode);
                }
            }

            int minValue=INF;
            minValuedNode=null;
            for(T u: vertices.keySet()) {
                if(!selectedMap.containsKey(u) && pathValueMap.get(u)<minValue) {
                    minValuedNode = u;
                    minValue = pathValueMap.get(u);
                }
            }
        }
        return mstGraph;
    }
}
