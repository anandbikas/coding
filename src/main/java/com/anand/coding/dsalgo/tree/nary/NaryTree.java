package com.anand.coding.dsalgo.tree.nary;

import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Nary Tree
 *
 * A Nary tree has n nodes called children.
 *
 * 1. A Nary tee with n nodes has exactly n-1 edges
 * 2. Maximum number of nodes at level l is N pow(l-1).
 * 3. Maximum number of nodes in a Nary tree of height h is N pow(h) – 1.
 * 4. In a Nary Tree with n nodes, minimum possible height is ceil(logN(n+1))
 * 5. Number of nodes in a complete Nary tree lies betwwen N pow(h-1) to N pow(h) -1
 */
public class NaryTree <T extends Comparable<T>>{

    private NaryNode<T> root;
    private int N;

    public NaryTree(final T data, int N){
        this.N = N;
        root = new NaryNode<T>(data, N, null);

    }

    /**
     *
     */
    public void preOrderTraversalRec(){
        System.out.println("preOrderTraversalRec");
        preOrderTraversalRec(root);
        System.out.println();
    }

    /**
     * Print in order root, children (left to right)
     * @param node
     */
    private void preOrderTraversalRec(final NaryNode<T> node){
        if(node == null){
            return;
        }
        System.out.print(node.data + "  ");

        for(int i=0; i<N; i++){
            preOrderTraversalRec(node.child[i]);
        }
    }

    /**
     *
     */
    public void postOrderTraversalRec(){
        System.out.println("postOrderTraversalRec");
        postOrderTraversalRec(root);
        System.out.println();
    }

    /**
     * Print in order children (left to right), root
     * @param node
     */
    private void postOrderTraversalRec(final NaryNode<T> node){
        if(node == null){
            return;
        }
        for(int i=0; i<N; i++){
            postOrderTraversalRec(node.child[i]);
        }
        System.out.print(node.data + "  ");
    }

    /**
     * Print in order root, children (left to right)
     * Without recursion
     */
    public void preOrderTraversal(){
        System.out.println("preOrderTraversal");

        Stack<NaryNode<T>> stack = new Stack<>();

        if(root!=null){
            stack.push(root);
        }
        while(!stack.isEmpty()) {
            NaryNode<T> node = stack.pop();
            System.out.print(node.data + "  ");

            for(int i=N-1; i>=0; i--){
                if(node.child[i] != null) {
                    stack.push(node.child[i]);
                }
            }
        }
        System.out.println();
    }

    /**
     * Print in order children (left to right), root
     *
     * Space complexity: O(height)
     */
    public void postOrderTraversal(){
        System.out.println("postOrderTraversal");

        Stack<NaryNode<T>> stack = new Stack<>();

        if(root != null){
            stack.push(root);
        }
        while(!stack.isEmpty()){
            NaryNode<T> node = stack.peek();

            if(node==null){
                //Process root node
                stack.pop();
                System.out.print(stack.pop().data + "  ");
                continue;
            }

            //Push null to indicate a root needs to be processed from the stack
            stack.push(null);
            for(int i=N-1; i>=0; i--){
                if(node.child[i] != null) {
                    stack.push(node.child[i]);
                }
            }
        }

        System.out.println();
    }

    /**
     * Print in level order
     * Use loop with the help of a queue
     */
    public void levelOrderTraversal(){
        System.out.println("levelOrderTraversal");

        Queue<NaryNode<T>> queue = new ArrayDeque<>(); //new LinkedList<>();

        if(root!=null){
            queue.add(root);
        }

        while(!queue.isEmpty()){
            NaryNode<T> node = queue.remove();
            System.out.print(node.data + "  ");

            for(int i=0; i<N; i++){
                if(node.child[i] != null) {
                    queue.add(node.child[i]);
                }
            }
        }
        System.out.println();
    }

    /**
     * Call printLevel method for each level
     */
    public void levelOrderTraversalBruteForce(){
        System.out.println("levelOrderTraversalBruteForce");

        for(int level = 1; level <=height(); level++){
            printLevel(level);
        }
        System.out.println();
    }

    /**
     *
     * @param level
     */
    public void printLevel(int level){
        System.out.println("printLevel " + level);
        printLevel(root, level, 1);
        System.out.println();
    }

    /**
     * Print data in a given level
     *
     * @param node
     * @param level
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     */
    private void printLevel(NaryNode<T> node, int level, int currentLevel){
        if(node == null || currentLevel > level){
            return;
        }
        if(level == currentLevel){
            System.out.print( node.data + " ");
            return;
        }

        for(int i=0; i<N; i++){
            printLevel(node.child[i], level, currentLevel+1);
        }
    }

    /**
     *
     * @param data
     * @return
     */
    public NaryNode<T> searchRec(T data){
        return searchRec(root, data);
    }

    /**
     * If root is not the one, check in all children tree one by one.
     *
     * @param node
     * @param data
     * @return
     */
    private NaryNode<T> searchRec(NaryNode<T> node, T data){

        if(node==null || node.data.equals(data)){
            return node;
        }

        for(int i=0; i<N; i++){
            NaryNode<T> foundNode = searchRec(node.child[i], data);
            if(foundNode != null){
                return foundNode;
            }
        }
        return null;
    }

    /**
     *
     */
    public void leftSideView(){
        System.out.println("leftSideView");
        leftSideView(root, 1, 1);
    }

    /**
     * Print all the nodes present in the left as seen from the left side
     *
     * @param node
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     * @param processingLevel decides how many levels have already been processed.
     * @return
     */
    private int leftSideView(NaryNode<T> node, int currentLevel, int processingLevel){
        if(node== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(node.data);
            processingLevel++;
        }


        for(int i=0; i<N; i++){
            processingLevel = leftSideView(node.child[i], currentLevel+1, processingLevel);
        }
        return processingLevel;
    }

    /**
     *
     */
    public void rightSideView(){
        System.out.println("rightSideView");
        rightSideView(root, 1, 1);
    }

    /**
     * Print all the nodes present in the right as seen from the right side
     *
     * @param node
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     * @param processingLevel decides how many levels have already been processed.
     * @return
     */
    private int rightSideView(NaryNode<T> node, int currentLevel, int processingLevel){
        if(node== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(node.data);
            processingLevel++;
        }


        for(int i=N-1; i>=0; i--){
            processingLevel = rightSideView(node.child[i], currentLevel+1, processingLevel);
        }
        return processingLevel;
    }

    /**
     *
     */
    public void printLeaves() {
        System.out.println("printLeaves");
        printLeaves(root);
        System.out.println();
    }

    /**
     * Print all the leave nodes
     *
     * @param node
     */
    private void printLeaves(final NaryNode<T> node){
        if(node == null){
            return;
        }
        if(node.isLeafNode()){
            System.out.print(node.data + "  ");
            return;
        }
        for(int i=0; i<N; i++){
            printLeaves(node.child[i]);
        }
    }

    /**
     *
     */
    public void displayPaths() {
        System.out.println("displayPaths");

        Stack<NaryNode<T>> pathStack = new Stack<>();
        displayPaths(root, pathStack);
        System.out.println();
    }

    /**
     *  Display all paths in the tree.
     *
     * @param node
     * @param pathStack keep track of the nodes in the traversed path.
     */
    private void displayPaths(final NaryNode<T> node, final Stack<NaryNode<T>> pathStack){
        if(node == null){
            return;
        }
        pathStack.push(node);
        if(node.isLeafNode()){
            //Instead of displaying paths we can add the them to pathList provided in function
            System.out.println(pathStack.toString());
        } else {
            for (int i = 0; i < N; i++) {
                displayPaths(node.child[i], pathStack);
            }
        }

        pathStack.pop();
    }

    /**
     *
     * @return
     */
    public int height(){
        return height(root);
    }

    /**
     * Calculate height of the tree
     *
     * @param node
     * @return
     */
    public int height(NaryNode<T> node){
        if(node==null) {
            return 0;
        }

        int maxHeightWithinChildren =0;

        for(int i=0; i<N; i++){
            maxHeightWithinChildren = Math.max(height(node.child[i]),maxHeightWithinChildren);
        }
        return maxHeightWithinChildren+1;
    }

    /**
     * Diameter of the tree (longest path in the tree)
     *
     * @return
     */
    public int diameter(){
        return diameterCalculator(root).diameter;
    }

    public class HiDi{
        int height=0,diameter=0;
    }

    /**
     * Just like height method. Twist is to calculate diameter as well.
     *
     * @param node
     * @return []
     */
    private HiDi diameterCalculator(NaryNode<T> node){

        if(node==null){
            return new HiDi();
        }

        int maxChildNodeHeight = 0;
        int secondMaxChildNodeHeight = 0;

        int maxChildNodeDiameter = 0;

        for(int i=0; i<N; i++) {
            HiDi childHidi = diameterCalculator(node.child[i]);
            if(childHidi.height>=maxChildNodeHeight){
                secondMaxChildNodeHeight = maxChildNodeHeight;
                maxChildNodeHeight = childHidi.height;
            }

            maxChildNodeDiameter = Math.max(childHidi.diameter, maxChildNodeDiameter);
        }

        HiDi hidi = new HiDi();
        hidi.height = maxChildNodeHeight + 1;
        hidi.diameter = Math.max((maxChildNodeHeight+secondMaxChildNodeHeight+1), maxChildNodeDiameter);

        return hidi;
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isSimilar(NaryTree<T> tree){
        return isSimilar(this.root, tree.root);
    }

    /**
     * Checks whether two trees are similar in structure
     *
     * @param node1
     * @param node2
     * @return
     */
    private boolean isSimilar(NaryNode<T> node1, NaryNode<T> node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 ==null){
            return false;
        }

        boolean isSimilar = true;
        for(int i=0; i<N && isSimilar; i++) {
            isSimilar = isSimilar(node1.child[i], node2.child[i]);
        }

        return isSimilar;
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isCopy(NaryTree<T> tree){
        return isCopy(this.root, tree.root);
    }

    /**
     * Checks whether two trees are similar in structure as well as data
     *
     * @param node1
     * @param node2
     * @return
     */
    private boolean isCopy(NaryNode<T> node1, NaryNode<T> node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 ==null){
            return false;
        }


        boolean isCopy = node1.data.equals(node2.data);
        for(int i=0; i<N && isCopy; i++) {
            isCopy = isCopy(node1.child[i], node2.child[i]);
        }

        return isCopy;
    }

    /**
     *
     * @return
     */
    public int numberOfNodes(){
        return numberOfNodes(root);
    }

    /**
     * Calculate number of nodes
     *
     * @param node
     * @return
     */
    private int numberOfNodes(NaryNode<T> node){
        if(node==null){
            return 0;
        }

        int numberOfNodes = 1;
        for(int i=0; i<N; i++) {
            numberOfNodes += numberOfNodes(node.child[i]);
        }
        return numberOfNodes;
    }

    /**
     *
     * @param data
     * @param nodeData
     * @param childIndex
     * @return
     */
    public boolean insertAsChild(final T data, final T nodeData, int childIndex){
        return insertAsChild(root, data, nodeData, childIndex);
    }

    /**
     * Insert a node to a provided childIndex of the provided node
     *
     * @param node
     * @param data
     * @param nodeData
     * @param childIndex
     * @return
     */
    private boolean insertAsChild(final NaryNode<T> node, final T data, final T nodeData, int childIndex){

        if(null == node){
            return false;
        }
        if(node.data.equals(nodeData)){
            final NaryNode<T> newNode = new NaryNode<>(data, N, node);
            newNode.child[childIndex] = node.child[childIndex];
            node.child[childIndex] = newNode;
            return true;
        } else{
            boolean isInserted = false;
            for(int i=0; i<N && !isInserted; i++) {
                isInserted = insertAsChild(node.child[i], data, nodeData, childIndex);
            }
            return isInserted;
        }
    }

    @Override
    public String toString() {
        return "NaryTree{" +
                "root=" + root +
                '}';
    }


    /**
     * Main function to test the code.
     *
     * @param args
     */
    public static void main(String[] args) {

        /*
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        final NaryTree<Integer> naryTree = new NaryTree<>(1, 2);

        naryTree.insertAsChild(2, 1, 0);
        naryTree.insertAsChild(3, 1, 1);

        naryTree.insertAsChild(4, 2, 0);
        naryTree.insertAsChild(5, 2, 1);

        naryTree.insertAsChild(6, 3, 0);
        naryTree.insertAsChild(7, 3, 1);

        naryTree.insertAsChild(8, 6, 0);

        System.out.println(naryTree);

        naryTree.preOrderTraversal();
        naryTree.preOrderTraversalRec();

        naryTree.postOrderTraversalRec();
        naryTree.postOrderTraversal();

        naryTree.levelOrderTraversal();
        naryTree.levelOrderTraversalBruteForce();

        naryTree.displayPaths();
        naryTree.printLevel(3);
        naryTree.printLeaves();

        naryTree.leftSideView();
        naryTree.rightSideView();

        System.out.println("naryTree.numberOfNodes(): " + naryTree.numberOfNodes());
        System.out.println("naryTree.isSimilar(naryTree): " + naryTree.isSimilar(naryTree));
        System.out.println("naryTree.isSimilar(naryTree): " + naryTree.isCopy(naryTree));
        System.out.println("naryTree.height(): " + naryTree.height());
        System.out.println("naryTree.diameter(): " + naryTree.diameter());
        System.out.println("naryTree.searchRec(4) " + naryTree.searchRec(4));
        System.out.println("naryTree.searchRec(9) " + naryTree.searchRec(9));

        System.out.println(naryTree);
        System.out.println(naryTree.diameter());

        //Lock/Unlock check
        System.out.println(naryTree.root.child[1].child[0].lock() + " = true");
        System.out.println(naryTree.root.child[1].child[0].lock() + " = false");

        System.out.println(naryTree.root.child[1].lock() + " = false");
        System.out.println(naryTree.root.child[0].lock() + " = true");
    }
}
