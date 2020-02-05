package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.disjointset.DisjointUnionSets;
import com.anand.coding.dsalgo.graph.Edge;
import com.anand.coding.dsalgo.graph.GraphType;
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

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            System.out.print(vertices.get(nodeIndex) + " -> ");
            for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
                System.out.print(String.format("%s, ", vertices.get(childIndex.getKey())));
            }
            System.out.println();
        }
    }

    /**
     *
     */
    public void displayWeighted(){
        System.out.println("Adjacency List Graph");

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            System.out.print(vertices.get(nodeIndex) + " -> ");
            for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
                System.out.print(String.format("%s(%s), ", vertices.get(childIndex.getKey()), childIndex.getValue()));
            }
            System.out.println();
        }
    }

    /**
     * Breath First Search algorithm (similar to levelOrderTraversal of a tree)
     *
     * @param nodeIndex
     */
    public void bfsDisplay(int nodeIndex) {
        System.out.println("BFS Display from index: " + nodeIndex);
        if(nodeIndex>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Queue<Integer> queue = new ArrayCircularQueue<>(size);
        boolean []visited = new boolean[size];

        queue.insert(nodeIndex);

        while(!queue.isEmpty()){

            nodeIndex = queue.delete();
            System.out.print(vertices.get(nodeIndex) + "  ");
            visited[nodeIndex] = true;

            for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
                if(!visited[childIndex.getKey()]){
                    queue.insert(childIndex.getKey());
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth First Search algorithm
     *
     * @param nodeIndex
     */
    public void dfsDisplayPreOrder(int nodeIndex) {
        System.out.println("DFS Display PreOrder from index: " + nodeIndex);
        if(nodeIndex>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        Stack<Integer> stack = new ArrayStack<>(size);
        boolean []visited = new boolean[size];

        stack.push(nodeIndex);

        while(!stack.isEmpty()){

            nodeIndex = stack.pop();

            //For PostOrder printing will be done after processing adjList.
            System.out.print(vertices.get(nodeIndex) + "  ");
            visited[nodeIndex] = true;

            Iterator<Pair<Integer, Integer>> iterator = adjListArray.get(nodeIndex).descendingIterator();

            while (iterator.hasNext()){
                Pair<Integer, Integer> childIndex = iterator.next();
                if(!visited[childIndex.getKey()]){
                    stack.push(childIndex.getKey());
                }
            }
        }
        System.out.println();
    }

    /**
     *
     * @param nodeIndex
     */
    public void dfsDisplayPreOrderRec(int nodeIndex){
        System.out.println("\nDFS Display PreOrder Recursive from index: " + nodeIndex);
        if(nodeIndex>=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        boolean []visited = new boolean[size];
        dfsDisplayPreOrderRec(nodeIndex, visited);
        System.out.println();
    }

    /**
     * @param nodeIndex
     * @param visited
     */
    private void dfsDisplayPreOrderRec(int nodeIndex, boolean []visited){

        if(visited[nodeIndex]) {
            return;
        }

        //For PostOrder printing will be done after processing adjList.
        System.out.print(vertices.get(nodeIndex) + "  ");
        visited[nodeIndex] = true;

        for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex.getKey()]){
                dfsDisplayPreOrderRec(childIndex.getKey(), visited);
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
        for(int nodeIndex=0; nodeIndex<size; nodeIndex++) {
            if(!visited[nodeIndex]){
                countDfsForests(nodeIndex, visited);
                dfsForests++;
            }
        }
        return dfsForests;
    }

    /**
     *
     * @param nodeIndex
     * @param visited
     * @return
     */
    private void countDfsForests(int nodeIndex, boolean []visited){

        if(visited[nodeIndex]) {
            return;
        }

        visited[nodeIndex] = true;

        for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex.getKey()]){
                countDfsForests(childIndex.getKey(), visited);
            }
        }
    }


    /**
     *
     * @param u
     */
    public int outDegree(int u) {
        return adjListArray.get(u).size();
    }

    /**
     *
     * @param u
     */
    public int inDegree(int u) {

        //To calculate inDegrees for all nodes, take an array instead.
        int inDegree=0;

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)) {
                if(childIndex.getKey()==u) {
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
        boolean []inRecStack = new boolean[size];

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(int nodeIndex=0; nodeIndex<size; nodeIndex++) {
            if(!visited[nodeIndex] && isCyclicDfsRec(nodeIndex, visited, inRecStack)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param nodeIndex
     * @param visited
     * @param inRecStack
     * @return
     */
    private boolean isCyclicDfsRec(int nodeIndex, boolean []visited, boolean []inRecStack){

        if(inRecStack[nodeIndex]){
            return true;
        }

        if(visited[nodeIndex]) {
            return false;
        }

        inRecStack[nodeIndex] = true;
        visited[nodeIndex] = true;

        for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
            //Note: no need to check visited here
            if(isCyclicDfsRec(childIndex.getKey(), visited, inRecStack)){
                return true;

            }
        }
        inRecStack[nodeIndex] = false;
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

        DisjointUnionSets dus = new DisjointUnionSets(size);

        boolean []visited = new boolean[size];

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(int nodeIndex=0; nodeIndex<size; nodeIndex++) {
            visited[nodeIndex] = true;

            for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
                if(visited[childIndex.getKey()]){
                    continue;
                }

                int leftEnd = dus.find(nodeIndex);
                int rightEnd = dus.find(childIndex.getKey());

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

        Queue<Integer> queue = new ArrayCircularQueue<>(size);
        List<T> topologicallySortedVertices = new ArrayList<>();

        int[] inDegrees = new int[size];

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)) {
                inDegrees[childIndex.getKey()]++;
            }
        }

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            if(inDegrees[nodeIndex]==0){
                queue.insert(nodeIndex);
            }
        }

        while(!queue.isEmpty()){
            int nodeIndex = queue.delete();
            topologicallySortedVertices.add(vertices.get(nodeIndex));

            for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
                //Reduce indegree once its parent is processed.
                if(--inDegrees[childIndex.getKey()] == 0){
                    queue.insert(childIndex.getKey());
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
        Stack<T> topologicalVertexStack = new ArrayStack<>(size);

        // In case of disconnected graph, there can be DFS forest.
        // Loop through all nodes in such cases.
        for(int nodeIndex=0; nodeIndex<size; nodeIndex++) {
            if(!visited[nodeIndex]) {
                topologicalSortingDfsRec(nodeIndex, visited, topologicalVertexStack);
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
     * @param nodeIndex
     * @param visited
     * @param topologicalVertexStack
     */
    private void topologicalSortingDfsRec(int nodeIndex, boolean []visited, Stack<T> topologicalVertexStack){

        if(visited[nodeIndex]) {
            return;
        }
        visited[nodeIndex] = true;

        for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex.getKey()]){
                topologicalSortingDfsRec(childIndex.getKey(), visited, topologicalVertexStack);
            }
        }
        topologicalVertexStack.push(vertices.get(nodeIndex));
    }

    /**
     * use DFS(PreOrder) to find all paths from u to v
     *
     * @param sourceIndex
     * @param destIndex
     * @param
     */
    public List<T[]> findPathDFSRec(int sourceIndex, int destIndex){
        if(sourceIndex>=size || destIndex >=size){
            throw new ArrayIndexOutOfBoundsException();
        }

        boolean []visited = new boolean[size];
        Stack<T> pathStack = new ArrayStack<>(size);

        List<T[]> pathList= new ArrayList<>();

        findPathDFSRec(sourceIndex, destIndex, visited, pathStack, pathList);
        return pathList;
    }

    /**
     *
     * @param nodeIndex
     * @param destIndex
     * @param visited
     */
    private void findPathDFSRec(int nodeIndex, int destIndex, boolean []visited, Stack<T> pathStack, List<T[]> pathList){

        if(visited[nodeIndex]) {
            return;
        }

        pathStack.push(vertices.get(nodeIndex));

        if(nodeIndex == destIndex){
            pathList.add(pathStack.getAsList());

            pathStack.pop();
            return;
        }

        visited[nodeIndex] = true;

        for(Pair<Integer, Integer> childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex.getKey()]){
                 findPathDFSRec(childIndex.getKey(), destIndex, visited, pathStack, pathList);
            }
        }

        pathStack.pop();
    }

    /**
     * use DFS(PostOrder) to find all paths from u to v
     *
     * @param sourceIndex
     * @param destIndex
     * @param
     */
    public List<T[]> findPathDFS(int sourceIndex, int destIndex) {
        if (sourceIndex >= size || destIndex >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Stack<Integer> stack = new ArrayStack<>(size);
        boolean[] visited = new boolean[size];

        Stack<T> pathStack = new ArrayStack<>(size);

        List<T[]> pathList = new ArrayList<>();

        stack.push(sourceIndex);

        while (!stack.isEmpty()) {

            int nodeIndex = stack.pop();
            if (nodeIndex == -1) {
                //Means all childs processed, now remove parent from pathStack
                pathStack.pop();
                continue;
            }

            pathStack.push(vertices.get(nodeIndex));

            if (nodeIndex == destIndex) {
                pathList.add(pathStack.getAsList());
                pathStack.pop();
                continue;
            }

            visited[nodeIndex] = true;

            //Put a marker in stack for parent place holder.
            stack.push(-1);

            Iterator<Pair<Integer, Integer>> iterator = adjListArray.get(nodeIndex).descendingIterator();
            while (iterator.hasNext()) {
                Pair<Integer, Integer> childIndex = iterator.next();
                if (!visited[childIndex.getKey()]) {
                    stack.push(childIndex.getKey());
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

        List<Edge<Integer>> edgeList = new ArrayList<>();

        for(int u=0; u<size; u++) {
            Iterator<Pair<Integer, Integer>> iterator = adjListArray.get(u).iterator();
            while (iterator.hasNext()) {
                Pair<Integer, Integer> v = iterator.next();
                edgeList.add(new Edge(u, v.getKey(), v.getValue()));
            }
        }
        Collections.sort(edgeList);

        Graph<T> mstGraph = new Graph<>();

        for(int i=0; i<size; i++){
            mstGraph.insert(vertices.get(i));
        }

        //DisjointSets: Union find for loop detection.
        DisjointUnionSets dus = new DisjointUnionSets(size);

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

        int [] weightValue = new int[size];
        int [] parent = new int[size];
        boolean [] selected = new boolean[size];

        for(int i=0; i<size; i++) {
            weightValue[i]=INF;
            parent[i]=i;
            mstGraph.insert(vertices.get(i));
        }
        weightValue[0]=0;

        int minValuedNode = 0;
        while(minValuedNode !=-1) {

            selected[minValuedNode]=true;
            if(parent[minValuedNode] != minValuedNode) {
                mstGraph.addEdge(parent[minValuedNode], minValuedNode, weightValue[minValuedNode]);
            }

            for(Pair<Integer, Integer> childIndex : adjListArray.get(minValuedNode)){
                if(!selected[childIndex.getKey()] && childIndex.getValue() < weightValue[childIndex.getKey()]){
                    weightValue[childIndex.getKey()] = childIndex.getValue();
                    parent[childIndex.getKey()] = minValuedNode;
                }
            }

            int minValue=INF;
            minValuedNode=-1;
            for(int u=0; u<size; u++) {
                if(!selected[u] && weightValue[u]<minValue) {
                    minValuedNode = u;
                    minValue = weightValue[u];
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
    public Graph<T> dijkstraShortestPathTree(int source){

        int INF= Integer.MAX_VALUE;
        Graph<T> mstGraph = new Graph<>(this.type);

        if(size==0){
            return mstGraph;
        }

        int [] pathValue = new int[size];
        int [] weightValue = new int[size];
        int [] parent = new int[size];
        boolean [] selected = new boolean[size];

        for(int i=0; i<size; i++) {
            pathValue[i]=INF;
            weightValue[i]=0;
            parent[i]=i;
            mstGraph.insert(vertices.get(i));
        }
        pathValue[source]=0;

        int minValuedNode = source;
        while(minValuedNode !=-1) {

            selected[minValuedNode]=true;
            if(parent[minValuedNode] != minValuedNode) {
                mstGraph.addEdge(parent[minValuedNode], minValuedNode, weightValue[minValuedNode]);
            }

            for(Pair<Integer, Integer> childIndex : adjListArray.get(minValuedNode)){

                if(!selected[childIndex.getKey()] &&
                        childIndex.getValue()+pathValue[minValuedNode] < pathValue[childIndex.getKey()]){

                    pathValue[childIndex.getKey()] = childIndex.getValue()+pathValue[minValuedNode];
                    weightValue[childIndex.getKey()] = childIndex.getValue();
                    parent[childIndex.getKey()] = minValuedNode;
                }
            }

            int minValue=INF;
            minValuedNode=-1;
            for(int u=0; u<size; u++) {
                if(!selected[u] && pathValue[u]<minValue) {
                    minValuedNode = u;
                    minValue = pathValue[u];
                }
            }
        }
        return mstGraph;
    }
}
