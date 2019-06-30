package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    private ArrayList<LinkedList<Integer>> adjListArray;

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

        adjListArray.get(u).remove(v);
        if(type.equals(GraphType.UNDIRECTED)) {
            adjListArray.get(v).remove(u);
        }
    }

    /**
     *
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        if (u >= size || v >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        adjListArray.get(u).addLast(v);
        if (type.equals(GraphType.UNDIRECTED)) {
            adjListArray.get(v).addLast(u);
        }
    }

    /**
     *
     */
    public void display(){
        System.out.println("Adjacency List Graph");

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            System.out.print(vertices.get(nodeIndex) + " -> ");
            for(Integer childIndex: adjListArray.get(nodeIndex)){
                System.out.print(String.format("%s, ", vertices.get(childIndex)));
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

            for(int childIndex: adjListArray.get(nodeIndex)){
                if(!visited[childIndex]){
                    queue.insert(childIndex);
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
        System.out.println("DFS Display from index: " + nodeIndex);
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

            Iterator<Integer> iterator = adjListArray.get(nodeIndex).descendingIterator();

            while (iterator.hasNext()){
                Integer childIndex = iterator.next();
                if(!visited[childIndex]){
                    stack.push(childIndex);
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
        System.out.println("\nDFS Display Recursive from index: " + nodeIndex);
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

        for(int childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex]){
                dfsDisplayPreOrderRec(childIndex, visited);
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
        // In case of disconnected graph, there can be DFS forest. Loop through all nodes in such cases.
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

        for(int childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex]){
                countDfsForests(childIndex, visited);
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
            for(int childIndex: adjListArray.get(nodeIndex)) {
                if(childIndex==u) {
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

        boolean []visited = new boolean[size];
        boolean []inRecStack = new boolean[size];

        // In case of disconnected graph, there can be DFS forest. Loop through all nodes in such cases.
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

        for(int childIndex: adjListArray.get(nodeIndex)){
            //Note: no need to check visited here
            if(isCyclicDfsRec(childIndex, visited, inRecStack)){
                return true;

            }
        }
        inRecStack[nodeIndex] = false;
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
    public T[] topologicalSortingBfs(){

        if(type.equals(GraphType.UNDIRECTED) || isCyclicDfsRec()){
            return null;
        }

        Queue<Integer> queue = new ArrayCircularQueue<>(size);

        T[] topologicallySortedVertices = (T[])new Object[size];
        int k =0;

        int[] inDegrees = new int[size];

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            for(int childIndex: adjListArray.get(nodeIndex)) {
                inDegrees[childIndex]++;
            }
        }

        for(int nodeIndex=0; nodeIndex<size; nodeIndex++){
            if(inDegrees[nodeIndex]==0){
                queue.insert(nodeIndex);
            }
        }

        while(!queue.isEmpty()){
            int nodeIndex = queue.delete();
            topologicallySortedVertices[k++] = vertices.get(nodeIndex);

            for(int childIndex: adjListArray.get(nodeIndex)){
                //Reduce indegree once its parent is processed.
                if(--inDegrees[childIndex] == 0){
                    queue.insert(childIndex);
                }
            }
        }

        return topologicallySortedVertices;
    }

    /**
     *
     * It is a linear ordering of vertices such where for each edge uv, vertex u comes before v in the ordering.
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
    public T[] topologicalSortingDfsRec(){

        if(type.equals(GraphType.UNDIRECTED) || isCyclicDfsRec()){
            return null;
        }

        boolean []visited = new boolean[size];
        Stack<T> topologicalVertexStack = new ArrayStack<>(size);

        // In case of disconnected graph, there can be DFS forest. Loop through all nodes in such cases.
        for(int nodeIndex=0; nodeIndex<size; nodeIndex++) {
            if(!visited[nodeIndex]) {
                topologicalSortingDfsRec(nodeIndex, visited, topologicalVertexStack);
            }
        }

        T[] topologicallySortedVertices = (T[])new Object[size];
        int k=0;

        while(!topologicalVertexStack.isEmpty()){
            topologicallySortedVertices[k++] = topologicalVertexStack.pop();
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

        for(int childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex]){
                topologicalSortingDfsRec(childIndex, visited, topologicalVertexStack);
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
    private boolean findPathDFSRec(int nodeIndex, int destIndex, boolean []visited, Stack<T> pathStack, List<T[]> pathList){

        if(visited[nodeIndex]) {
            return false;
        }

        pathStack.push(vertices.get(nodeIndex));

        //No don't put visited here, we want destIndex to be visited as many times as the possible paths.
        //visited[nodeIndex] = true;

        if(nodeIndex == destIndex){
            pathList.add(pathStack.getAsList());

            pathStack.pop();
            return true;
        }
        //Instead keep visited flag after processing destIndex.
        visited[nodeIndex] = true;

        for(int childIndex: adjListArray.get(nodeIndex)){
            if(!visited[childIndex]){
                 findPathDFSRec(childIndex, destIndex, visited, pathStack, pathList);
            }
        }

        pathStack.pop();
        return false;
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

            Iterator<Integer> iterator = adjListArray.get(nodeIndex).descendingIterator();
            while (iterator.hasNext()) {
                Integer childIndex = iterator.next();
                if (!visited[childIndex]) {
                    stack.push(childIndex);
                }
            }
        }
        return pathList;
    }
}
