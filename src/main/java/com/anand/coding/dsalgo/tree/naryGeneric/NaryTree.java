package com.anand.coding.dsalgo.tree.naryGeneric;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Generic Nary Tree
 * Generic tree implementation where each node can have any number of children.
 */
public class NaryTree <T extends Comparable<T>>{

    private Node<T> root;

    public NaryTree(final T data){
        root = new Node<T>(data);
    }

    public List<Node<T>> preOrderTraversalRecNodeList() {
        return preOrderTraversalRecNodeList(root);
    }

    public List<Node<T>> preOrderTraversalRecNodeList(final Node<T> node){
        List<Node<T>> nodeList = new ArrayList<>();
        preOrderTraversalRecNodeList(node, nodeList);
        return nodeList;
    }

    private void preOrderTraversalRecNodeList(final Node<T> node, List<Node<T>> nodeList){
        if(node != null){
            nodeList.add(node);
            node.child.forEach(child-> preOrderTraversalRecNodeList(child, nodeList));
        }
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
    private void preOrderTraversalRec(final Node<T> node){
        if(node == null){
            return;
        }
        System.out.print(node.data + "  ");

        node.child.forEach(this::preOrderTraversalRec);
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
    private void postOrderTraversalRec(final Node<T> node){
        if(node == null){
            return;
        }
        node.child.forEach(this::postOrderTraversalRec);

        System.out.print(node.data + "  ");
    }

    /**
     * Print in order root, children (left to right)
     * Without recursion
     */
    public void preOrderTraversal(){
        System.out.println("preOrderTraversal");

        Stack<Node<T>> stack = new Stack<>();
        if(root!=null){
            stack.push(root);
        }

        while(!stack.isEmpty()) {
            Node<T> node = stack.pop();
            System.out.print(node.data + "  ");

            for(int i=node.child.size()-1; i>=0; i--){
                stack.push(node.child.get(i));
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

        Stack<Node<T>> stack = new Stack<>();
        if(root != null){
            stack.push(root);
        }

        while(!stack.isEmpty()){
            Node<T> node = stack.peek();
            if(node==null) { //Process root node
                stack.pop();
                System.out.print(stack.pop().data + "  ");
                continue;
            }

            //Push null to indicate a root needs to be processed from the stack
            stack.push(null);
            for(int i=node.child.size()-1; i>=0; i--){
                stack.push(node.child.get(i));
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

        Queue<Node<T>> queue = new ArrayDeque<>(); //new LinkedList<>();
        if(root!=null){
            queue.add(root);
        }

        while(!queue.isEmpty()){
            Node<T> node = queue.remove();
            System.out.print(node.data + "  ");

            node.child.forEach(queue::add);
        }
        System.out.println();
    }

    /**
     * Call printLevel method for each level
     */
    public void levelOrderTraversalBruteForce(){
        System.out.println("levelOrderTraversalBruteForce :");

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
    private void printLevel(Node<T> node, int level, int currentLevel){
        if(node == null || currentLevel > level){
            return;
        }
        if(level == currentLevel){
            System.out.print( node.data + " ");
            return;
        }
        node.child.forEach(child -> printLevel(child, level, currentLevel+1));
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> searchRec(T data){
        return searchRec(root, data);
    }

    /**
     *
     * @param node
     * @param data
     * @return
     */
    private Node<T> searchRec(Node<T> node, T data){
        if(node==null || node.data.equals(data)){
            return node;
        }

        for(Node<T> child: node.child) {
            Node<T> foundNode = searchRec(child, data);
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
    private int leftSideView(Node<T> node, int currentLevel, int processingLevel){
        if(node== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(node.data);
            processingLevel++;
        }

        for(Node<T> child: node.child) {
            processingLevel = leftSideView(child, currentLevel+1, processingLevel);
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
    private int rightSideView(Node<T> node, int currentLevel, int processingLevel){
        if(node== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(node.data);
            processingLevel++;
        }

        for(int i=node.child.size()-1; i>=0; i--){
            processingLevel = rightSideView(node.child.get(i), currentLevel+1, processingLevel);
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
    private void printLeaves(final Node<T> node){
        if(node == null){
            return;
        }
        if(node.isLeafNode()){
            System.out.print(node.data + "  ");
            return;
        }
        node.child.forEach(this::printLeaves);
    }

    /**
     *
     */
    public void displayPaths() {
        System.out.println("displayPaths");

        Stack<Node<T>> pathStack = new Stack<>();
        displayPaths(root, pathStack);
        System.out.println();
    }

    /**
     *  Display all paths in the tree.
     *
     * @param node
     * @param pathStack keep track of the nodes in the traversed path.
     */
    private void displayPaths(final Node<T> node, final Stack<Node<T>> pathStack){
        if(node == null){
            return;
        }
        pathStack.push(node);
        if(node.isLeafNode()){
            //Instead of displaying paths we can add them to pathList provided in function
            System.out.println(pathStack.toString());
//            StringBuffer sb = new StringBuffer();
//            for(Object x: pathStack.toArray()) {
//                sb.append(x).append("->");
//            }
//            sb.setLength(sb.length()-2);
//            paths.add(sb.toString());
        } else {
            node.child.forEach(child -> displayPaths(child, pathStack));
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
     *
     * @param node
     * @return
     */
    public int height(Node<T> node){
        if(node==null){
            return 0;
        }

        int maxHeightWithinChildren =0;

        for (Node<T> child: node.child) {
            maxHeightWithinChildren = Math.max(height(child),maxHeightWithinChildren);
        }
        return maxHeightWithinChildren+1;
    }

    /**
     * Diameter of the tree (The longest path in the tree)
     *
     * @return
     */
    public int diameter(){
        return diameter(root).diameter;
    }

    /**
     * Just like height method. Twist is to calculate diameter as well.
     * @param node
     * @return
     */
    private HiDi diameter(Node<T> node){
        if(node==null){
            return new HiDi();
        }

        int maxChildNodeHeight = 0;
        int secondMaxChildNodeHeight = 0;

        int maxChildNodeDiameter = 0;

        for (Node<T> child: node.child) {
            HiDi childHidi = diameter(child);
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

    public static class HiDi{
        int height=0,diameter=0;
    }

    /**
     *
     * @param tree1
     * @param tree2
     * @return
     */
    public static boolean isSimilar(NaryTree<?> tree1, NaryTree<?> tree2){
        return isSimilar(tree1.root, tree2.root);
    }

    /**
     * Checks whether two trees are similar in structure
     *
     * @param node1
     * @param node2
     * @return
     */
    private static boolean isSimilar(Node<?> node1, Node<?> node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 == null || node1.child.size() != node2.child.size()){
            return false;
        }

        boolean isSimilar = true;
        for(int i=0; i<node1.child.size() && isSimilar; i++) {
            isSimilar = isSimilar(node1.child.get(i), node2.child.get(i));
        }

        return isSimilar;
    }

    /**
     *
     * @param tree1
     * @param tree2
     * @return
     */
    public static boolean isCopy(NaryTree<?> tree1, NaryTree<?> tree2){
        return isCopy(tree1.root, tree2.root);
    }

    /**
     * Checks whether two trees are similar in structure as well as data
     *
     * @param node1
     * @param node2
     * @return
     */
    private static boolean isCopy(Node<?> node1, Node<?> node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 == null || node1.child.size() != node2.child.size()){
            return false;
        }

        boolean isCopy = node1.data.equals(node2.data);
        for(int i=0; i<node1.child.size() && isCopy; i++) {
            isCopy = isCopy(node1.child.get(i), node2.child.get(i));
        }

        return isCopy;
    }

    /**
     *
     * @return
     */
    public int size(){
        return size(root);
    }

    /**
     * Calculate number of nodes
     *
     * @param node
     * @return
     */
    private int size(Node<T> node){
        if(node==null){
            return 0;
        }

        int n = 1;
        for(Node<T> child : node.child) {
            n += size(child);
        }
        return n;
    }

    /**
     *
     * @param data
     * @param nodeData
     * @return
     */
    public boolean insertAsChild(final T data, final T nodeData){
        return insertAsChild(root, data, nodeData);
    }

    /**
     * Insert a node to a provided childIndex of the provided node
     *
     * @param node
     * @param data
     * @param nodeData
     * @return
     */
    private boolean insertAsChild(final Node<T> node, final T data, final T nodeData){

        if(null == node){
            return false;
        }

        if(node.data.equals(nodeData)){
            node.child.add(new Node<>(data));
            return true;
        }

        boolean isInserted = false;
        for(int i=0; i<node.child.size() && !isInserted; i++) {
            isInserted = insertAsChild(node.child.get(i), data, nodeData);
        }
        return isInserted;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        /*
         * A Nary Tree.
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        final NaryTree<Integer> tree = new NaryTree<>(1);

        tree.insertAsChild(2, 1);
        tree.insertAsChild(3, 1);
        tree.insertAsChild(4, 2);
        tree.insertAsChild(5, 2);
        tree.insertAsChild(6, 3);
        tree.insertAsChild(7, 3);
        tree.insertAsChild(8, 6);

        // Traversals
        tree.preOrderTraversal();
        tree.preOrderTraversalRec();

        tree.postOrderTraversalRec();
        tree.postOrderTraversal();

        tree.levelOrderTraversal();
        tree.levelOrderTraversalBruteForce();

        // Searching
        System.out.println("searchRec(4) " + tree.searchRec(4));
        System.out.println("searchRec(9) " + tree.searchRec(9));

        // Paths (Selective prints)
        tree.displayPaths();
        tree.printLevel(3);
        tree.printLeaves();
        tree.leftSideView();
        tree.rightSideView();
        System.out.println("Diameter: "  + tree.diameter());

        // Utility
        System.out.println("tree.size(): " + tree.size());
        System.out.println("tree.height(): " + tree.height());

        // Comparison
        System.out.println("isSimilar(tree, tree): " + NaryTree.isSimilar(tree, tree));
        System.out.println("isCopy(tree, tree): " + NaryTree.isCopy(tree, tree));
    }
}
