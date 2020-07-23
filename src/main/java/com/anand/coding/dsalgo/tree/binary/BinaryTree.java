package com.anand.coding.dsalgo.tree.binary;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Binary Tree
 *
 * A binary tree has two nods called left and right.
 *
 * 1. A binary tee with n nodes has exactly n-1 edges
 * 2. Maximum number of nodes at level l is 2 pow(l-1).
 * 3. Maximum number of nodes in a binary tree of height h is 2 pow(h) – 1.
 * 4. In a Binary Tree with n nodes, minimum possible height is ceil(log2(n+1))
 * 5. Number of nodes in a complete binary tree lies betwwen 2 pow(h-1) to 2 pow(h) -1
 */
public class BinaryTree <T extends Comparable<T>> {

    protected Node<T> root;

    public BinaryTree(){
        super();
    }

    public BinaryTree(final T data){
        root = new Node<>(data);
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
     * Print in order root, left, right
     * @param node
     */
    private void preOrderTraversalRec(final Node node){
        if(node == null){
            return;
        }
        System.out.print(node.data + "  ");
        preOrderTraversalRec(node.left);
        preOrderTraversalRec(node.right);
    }

    /**
     *
     */
    public void inOrderTraversalRec(){
        System.out.println("inOrderTraversalRec");
        inOrderTraversalRec(this.root);
        System.out.println();
    }

    /**
     * Print in order left, root, right
     * @param node
     */
    private void inOrderTraversalRec(final Node node){
        if(node == null){
            return;
        }
        inOrderTraversalRec(node.left);
        System.out.print(node.data + "  ");
        inOrderTraversalRec(node.right);
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
     * Print in order left, right, root
     * @param node
     */
    private void postOrderTraversalRec(final Node node){
        if(node == null){
            return;
        }
        postOrderTraversalRec(node.left);
        postOrderTraversalRec(node.right);
        System.out.print(node.data + "  ");
    }

    /**
     * Print in order root, left, right
     * Without recursion
     */
    public void preOrderTraversal(){
        System.out.println("preOrderTraversal");

        Stack<Node> stack = new ArrayStack<>();

        if(root!=null){
            stack.push(root);
        }
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + "  ");

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    /**
     * Print in order left, root, right
     * Use loop with the help of a stack
     */
    public void inOrderTraversal(){
        System.out.println("inOrderTraversal");

        Stack<Node> stack = new ArrayStack<>();

        for(Node node = root; node!= null; node=node.left){
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.data + "  ");

            for(node=node.right; node!= null; node = node.left){
                stack.push(node);
            }
        }

        System.out.println();
    }

    /**
     * Print in order left, right, root
     *
     * Space complexity: O(height)
     */
    public void postOrderTraversal(){
        System.out.println("postOrderTraversal");

        Stack<Node> stack = new Stack<>();

        for(Node node = root; node!= null; node=node.left){
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node node = stack.peek();
            if(node==null){
                //Process root node
                stack.pop();
                System.out.print(stack.pop().data + "  ");
                continue;
            }

            //Push null to indicate a root needs to be processed from the stack as its right child processed.
            stack.push(null);
            for(node=node.right; node!= null; node = node.left){
                stack.push(node);
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

        Queue<Node> queue = new ArrayCircularQueue<>();

        if(root!=null){
            queue.insert(root);
        }

        while(!queue.isEmpty()){
            Node node = queue.delete();
            System.out.print(node.data + "  ");
            if(node.left!=null){
                queue.insert(node.left);
            }
            if(node.right!=null){
                queue.insert(node.right);
            }
        }
        System.out.println();
    }

    /**
     * Print in level order zigzag/spiral
     * Use loop with the help of a queue and a stack
     */
    public void levelOrderSpiralTraversal(){
        System.out.println("levelOrderSpiralTraversal");

        Queue<Node> queue = new ArrayCircularQueue<>();
        Stack<Node> stack = new ArrayStack<>();

        if(root!=null){
            queue.insert(root);
        }

        int level=1;
        while (!(queue.isEmpty() && stack.isEmpty())) {

            while (!queue.isEmpty()) {
                Node node = queue.delete();
                System.out.print(node.data + "  ");

                if(level%2==1) {
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        stack.push(node.right);
                    }
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                }
            }
            while(!stack.isEmpty()){
                queue.insert(stack.pop());
            }
            level++;
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
    private void printLevel(Node node, int level, int currentLevel){
        if(node == null || currentLevel > level){
            return;
        }
        if(level == currentLevel){
            System.out.print( node.data + " ");
            return;
        }
        printLevel(node.left, level, currentLevel+1);
        printLevel(node.right, level, currentLevel+1);

    }

    /**
     * verticalOrderTraversal using HashMap
     */
    public void verticalOrderTraversal(){

        Map<Integer, List<Node>> nodeMap= new HashMap<>();
        Range verticalRange = new Range();
        verticalOrderTraversal(root, nodeMap, 0, verticalRange);

        for(int level = verticalRange.left; level<=verticalRange.right; level++) {
            System.out.println("printLevel " + level);

            nodeMap.get(level).forEach(node -> {
                System.out.print( node.data + " ");
            });
            System.out.println();
        }

    }

    /**
     *
     * @param node
     * @param nodeMap
     * @param currentLevel
     * @param range
     */
    private void verticalOrderTraversal(Node node, Map<Integer, List<Node>> nodeMap, int currentLevel, Range range){

        if(node == null){
            return;
        }

        if(currentLevel<range.left){
            range.left=currentLevel;
        }

        if(currentLevel>range.right){
            range.right = currentLevel;
        }

        nodeMap.computeIfAbsent(currentLevel, k->new ArrayList<>());
        nodeMap.get(currentLevel).add(node);

        verticalOrderTraversal(node.left, nodeMap, currentLevel-1, range);
        verticalOrderTraversal(node.right, nodeMap,currentLevel+1, range);
    }

    /**
     * Range: an empty range has right < left
     */
    private class Range{
        public int left=0;
        public int right=-1;
    }

    /**
     *
     * @param node
     * @param currentLevel
     * @param range
     */
    public void calculateVerticalLevelRange(Node node,  int currentLevel, Range range){

        if(node == null){
            return;
        }

        if(currentLevel<range.left){
            range.left=currentLevel;
        }

        if(currentLevel>range.right){
            range.right = currentLevel;
        }

        calculateVerticalLevelRange(node.left, currentLevel-1, range);
        calculateVerticalLevelRange(node.right, currentLevel+1, range);
    }


    /**
     * Call printLevelVertical method for each vertical level
     */
    public void verticalOrderTraversalBruteForce(){
        System.out.println("verticalOrderTraversalBruteForce");

        Range verticalLevelRange = new Range();
        calculateVerticalLevelRange(root, 0, verticalLevelRange);

        for(int level = verticalLevelRange.left; level<=verticalLevelRange.right; level++){
            printVerticalLevel(level);
        }
        System.out.println();
    }

    /**
     *
     * @param level
     */
    public void printVerticalLevel(int level){
        System.out.println("printLevel " + level);
        printVerticalLevel(root, level, 0);
        System.out.println();
    }

    /**
     * Print data in a given level vertical
     *
     * @param node
     * @param level
     * @param currentLevel this tags each node with its vertical level, is handy to find the level of a node while processing.
     */
    private void printVerticalLevel(Node node, int level, int currentLevel){
        if(node == null){
            return;
        }
        if(level == currentLevel){
            System.out.print( node.data + " ");
        }
        printVerticalLevel(node.left, level, currentLevel-1);
        printVerticalLevel(node.right, level, currentLevel+1);
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
     * If node is not the one, check in right subTree only if the data is not found in left subTree.
     *
     * @param node
     * @param data
     * @return
     */
    private Node<T> searchRec(Node<T> node, T data){

        if(node==null || node.data.equals(data)){
            return node;
        }
        Node<T> foundNode = searchRec(node.left, data);
        if(foundNode != null){
            return foundNode;
        }
        return searchRec(node.right, data);
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
    private int leftSideView(Node node, int currentLevel, int processingLevel){
        if(node== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(node.data);
            processingLevel++;
        }
        processingLevel = leftSideView(node.left, currentLevel+1, processingLevel);
        processingLevel = leftSideView(node.right, currentLevel+1, processingLevel);

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
    private int rightSideView(Node node, int currentLevel, int processingLevel){
        if(node== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(node.data);
            processingLevel++;
        }
        processingLevel = rightSideView(node.right, currentLevel+1, processingLevel);
        processingLevel = rightSideView(node.left, currentLevel+1, processingLevel);

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
    private void printLeaves(final Node node){
        if(node == null){
            return;
        }
        if(node.left==null && node.right==null){
            System.out.print(node.data + "  ");
            return;
        }
        printLeaves(node.left);
        printLeaves(node.right);
    }

    /**
     * Display all paths from root to data
     * @param data
     */
    public void displayPaths(T data) {
        System.out.println("displayPaths for data: " + data);

        Stack<Node> pathStack = new Stack<>();
        displayPaths(root, data, pathStack);
        System.out.println();
    }

    /**
     *
     * @param node
     * @param data
     * @param pathStack keep track of the nodes in the traversed path.
     */
    private void displayPaths(final Node<T> node, T data , final Stack<Node> pathStack){
        if(node == null){
            return;
        }
        pathStack.push(node);
        if(node.data.equals(data)){
            //Instead of displaying paths we can add the them to pathList provided in function
            System.out.println(pathStack.toString());
        } else {
            displayPaths(node.left, data, pathStack);
            displayPaths(node.right, data, pathStack);
        }

        pathStack.pop();
    }

    /**
     *
     */
    public void displayPaths() {
        System.out.println("displayPaths");

        Stack<Node> pathStack = new Stack<>();
        displayPaths(root, pathStack);
        System.out.println();
    }

    /**
     *  Display all paths in the tree.
     *
     * @param node
     * @param pathStack keep track of the nodes in the traversed path.
     */
    private void displayPaths(final Node node, final Stack<Node> pathStack){
        if(node == null){
            return;
        }
        pathStack.push(node);
        if(node.left==null && node.right==null){
            //Instead of displaying paths we can add the them to pathList provided in function
            System.out.println(pathStack.toString());
        } else {
            displayPaths(node.left, pathStack);
            displayPaths(node.right, pathStack);
        }

        pathStack.pop();
    }

    /**
     * Find the lowest common ancestor node of the two given nodes
     * Method 1: find path for both x and y and find the parent by comparing both the paths.
     * Method 2: Using single traversal with an object (x,y,parent) as the parameter
     *
     * @return
     */
    public Node findLca(T x, T y){

        LCA lca = findLca(root, x, y);
        return lca==null ? null : lca.parent;
    }

    private LCA findLca(Node node, T x, T y){

        if(node==null){
            return null;
        }

        LCA leftLca = findLca(node.left, x, y);
        if(!(leftLca==null || leftLca.parent==null)){
            return leftLca;
        }

        LCA rightLca = findLca(node.right, x, y);
        if(!(rightLca==null || rightLca.parent==null)){
            return rightLca;
        }

        LCA lca = leftLca;
        if(lca == null) {
            lca = rightLca;
        } else if(rightLca != null) {
            if(lca.x==null)
                lca.x = rightLca.x;
            if(lca.y==null)
                lca.y = rightLca.y;
        }

        if(node.data.equals(x)){
            if(lca==null) lca = new LCA();
            lca.x=node;
        } else if(node.data.equals(y)){
            if(lca==null) lca = new LCA();
            lca.y=node;
        }

        if(!(lca==null || lca.x==null || lca.y==null)) {
            lca.parent = node;
        }
        return lca;
    }

    private class LCA{
        Node x,y,parent;
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
    public int height(Node node){
        return node==null? 0 : Math.max(height(node.left),height(node.right)) + 1;
    }

    /**
     * heightBalanceFactor of a node is heightOfLeftSubTree - heightOfRightSubTree
     * @param node
     * @return
     */
    public int heightBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
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
    private HiDi diameterCalculator(Node<T> node){

        if(node==null){
            return new HiDi();
        }

        HiDi left = diameterCalculator(node.left);
        HiDi right = diameterCalculator(node.right);

        HiDi hidi = new HiDi();
        hidi.height = Math.max(left.height,right.height) + 1;
        hidi.diameter = Math.max((left.height+right.height+1), Math.max(left.diameter,right.diameter));

        return hidi;
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isSimilar(BinaryTree tree){
        return isSimilar(this.root, tree.root);
    }

    /**
     * Checks whether two trees are similar in structure
     *
     * @param node1
     * @param node2
     * @return
     */
    private boolean isSimilar(Node node1, Node node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 ==null){
            return false;
        }

        return isSimilar(node1.left, node2.left) && isSimilar(node1.right, node2.right);
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isCopy(BinaryTree tree){
        return isCopy(this.root, tree.root);
    }

    /**
     * Checks whether two trees are similar in structure as well as data
     *
     * @param node1
     * @param node2
     * @return
     */
    private boolean isCopy(Node node1, Node node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 ==null){
            return false;
        }


        return node1.data.equals(node2.data)
                && isCopy(node1.left, node2.left)
                && isCopy(node1.right, node2.right);
    }

    private class NodePair{
        Node node1;
        Node node2;

        public NodePair(Node node1, Node node2){
            this.node1 = node1;
            this.node2 = node2;
        }
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isCopyUsingLoop(BinaryTree tree){
        return isCopyUsingLoop(this.root, tree.root);
    }

    /**
     * Checks whether two trees are similar in structure as well as data
     *
     * @param node1
     * @param node2
     * @return
     */
    private boolean isCopyUsingLoop(Node node1, Node node2){

        java.util.Stack<NodePair> stack = new java.util.Stack<>();

        stack.push(new NodePair(node1, node2));

        boolean result = true;
        while(!stack.isEmpty() && result){
            NodePair pair = stack.pop();
            if(pair.node1 == null && pair.node2 == null){
                result = true;
                continue;
            }

            if(pair.node1 == null || pair.node2 ==null){
                result = false;
                continue;
            }

            result = pair.node1.data.equals(pair.node2.data);

            stack.push(new NodePair(pair.node1.left, pair.node2.left));
            stack.push(new NodePair(pair.node1.right, pair.node2.right));
        }

        return result;
    }


    /**
     *
     *
     * @return
     */
    public boolean isBinarySearchTree(){
        return isBinarySearchTree(root, new Node<>());
    }

    /**
     * Checks if the tree is a binary search tree
     * using Inorder Traversal.
     *
     * @param node
     * @return
     */
    private boolean isBinarySearchTree(Node<T> node, Node<T> prevVisitedNode){
        if(node == null){
            return true;
        }

        if(!isBinarySearchTree(node.left, prevVisitedNode)){
            return false;
        }

        if(prevVisitedNode.data!=null && prevVisitedNode.data.compareTo(node.data)>0){
            return false;
        }
        prevVisitedNode.data = node.data;
        return isBinarySearchTree(node.right, prevVisitedNode);
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
    private int numberOfNodes(Node node){
        if(node==null){
            return 0;
        }
        return 1 + numberOfNodes(node.left) + numberOfNodes(node.right);
    }

    /**
     *
     * @return
     */
    public void toMirrorImage(){
        toMirrorImage(root);
    }

    /**
     * Convert the tree to its mirror image by swapping left and right subTree.
     *
     * @param node
     * @return
     */
    private void toMirrorImage(Node<T> node){
        if(node==null){
            return;
        }
        Node<T> temp =  node.left;
        node.left = node.right;
        node.right = temp;

        toMirrorImage(node.left);
        toMirrorImage(node.right);
    }

    /**
     *
     * @param data
     * @param nodeData
     * @return
     */
    public boolean insertAsLeftChild(final T data, final T nodeData){
        return insertAsLeftChild(root, data, nodeData);
    }

    /**
     * Insert a node to the left of the provided node
     * Shifts the left child of the provided node to further left.
     *
     * @param root
     * @param data
     * @param nodeData
     * @return
     */
    private boolean insertAsLeftChild(final Node<T> root, final T data, final T nodeData){

        if(null == root){
            return false;
        }
        if(root.data.equals(nodeData)){
            final Node<T> newNode = new Node<>(data);
            newNode.left = root.left;
            root.left = newNode;
            return true;
        } else{
            return insertAsLeftChild(root.left, data, nodeData)
                    || insertAsLeftChild(root.right, data, nodeData);
        }
    }

    /**
     *
     * @param data
     * @param nodeData
     * @return
     */
    public boolean insertAsRightChild(final T data, final T nodeData){
        return insertAsRightChild(root, data, nodeData);

    }

    /**
     * Insert a node to the right of the provided node
     * Shifts the right child of the provided node to further right.
     *
     * @param root
     * @param data
     * @param nodeData
     * @return
     */
    private boolean insertAsRightChild(final Node<T> root, final T data, final T nodeData){

        if(null == root){
            return false;
        }
        if(root.data.equals(nodeData)){
            final Node<T> newNode = new Node<>(data);
            newNode.right = root.right;
            root.right = newNode;
            return true;
        } else{
            return insertAsRightChild(root.left, data, nodeData)
                    || insertAsRightChild(root.right, data, nodeData);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "BinaryTree{" +
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
         * Build a Binary Tree.
         *
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        final BinaryTree<Integer> binaryTree = new BinaryTree<>(1);
        binaryTree.insertAsLeftChild(2, 1);
        binaryTree.insertAsRightChild(3, 1);
        binaryTree.insertAsLeftChild(4,2);
        binaryTree.insertAsRightChild(5,2);
        binaryTree.insertAsLeftChild(6,3);
        binaryTree.insertAsRightChild(7, 3);
        binaryTree.insertAsLeftChild(8,6);

        // Traversals
        binaryTree.inOrderTraversal();
        binaryTree.inOrderTraversalRec();

        binaryTree.preOrderTraversal();
        binaryTree.preOrderTraversalRec();

        binaryTree.postOrderTraversalRec();
        binaryTree.postOrderTraversal();

        binaryTree.levelOrderTraversal();
        binaryTree.levelOrderTraversalBruteForce();
        binaryTree.levelOrderSpiralTraversal();

        binaryTree.verticalOrderTraversalBruteForce();
        binaryTree.verticalOrderTraversal();

        // Searching
        System.out.println("binaryTree.searchRec(4) " + binaryTree.searchRec(4));
        System.out.println("binaryTree.searchRec(9) " + binaryTree.searchRec(9));

        // Paths (Selective prints)
        binaryTree.displayPaths(8);
        binaryTree.displayPaths();
        binaryTree.printLevel(3);
        binaryTree.printLeaves();
        binaryTree.leftSideView();
        binaryTree.rightSideView();
        System.out.println("Diameter: "  + binaryTree.diameter());


        // LCA
        System.out.println("binaryTree.findLca(4,5): " + binaryTree.findLca(8,5));
        System.out.println("binaryTree.findLca(5,8): " + binaryTree.findLca(3,8));

        // Utility
        System.out.println("binaryTree.numberOfNodes(): " + binaryTree.numberOfNodes());
        System.out.println("binaryTree.height(): " + binaryTree.height());
        System.out.println("binaryTree.heightBalanceFactor(3): " + binaryTree.heightBalanceFactor(binaryTree.searchRec(3)));

        // Comparision
        System.out.println("binaryTree.isBinarySearchTree(): " + binaryTree.isBinarySearchTree());
        System.out.println("binaryTree.isSimilar(binaryTree): " + binaryTree.isSimilar(binaryTree));
        System.out.println("binaryTree.isCopy(binaryTree): " + binaryTree.isCopy(binaryTree));
        System.out.println("binaryTree.isCopyUsingLoop(binaryTree): " + binaryTree.isCopyUsingLoop(binaryTree));

        // Conversion
        binaryTree.toMirrorImage();
        System.out.println("After Converting the tree to mirror image");
        binaryTree.leftSideView();
        binaryTree.rightSideView();


        // Construct tree using preOrder and inOrder traversals
        /** 1.
         *                         a
         *                     /       \
         *                  b            c
         *                /   \        /   \
         *               d    e       f    g
         *                   / \     / \
         *                  j   k   h  i
         *
         */
        Character [] preOrder = {'a', 'b', 'd', 'e', 'j', 'k', 'c', 'f', 'h', 'i', 'g'};
        Character [] inOrder = {'d', 'b', 'j', 'e', 'k', 'a', 'h', 'f', 'i', 'c', 'g'};

        BinaryTree<Character> tree1 = new BinaryTree<>(preOrder, inOrder);
        System.out.println(tree1);
        tree1.preOrderTraversal();
        tree1.inOrderTraversal();

        /** 2.
         *                x
         *              /
         *            a
         *           /
         *          b
         *         / \
         *        d   e
         *       /     \
         *      f       g
         *       \
         *        h
         *  Diameter = 6
         */
        BinaryTree<String> tree2 = new BinaryTree<>("x a b d f h e g".split("\\s+"), "f h d b g e a x".split("\\s+"));
        System.out.println(tree2);
        tree2.preOrderTraversal();
        tree2.inOrderTraversal();

    }



    /**
     * Construct a tree using preOrder and inOrder traversals
     *
     * @param preOrder
     * @param inOrder
     */
    public BinaryTree(T [] preOrder, T [] inOrder){
        this.root = treeFromPreAndInOrder(preOrder, 0, inOrder, 0, inOrder.length-1);
    }

    private Node<T> treeFromPreAndInOrder(T [] preOrder, int i, T [] inOrder, int left, int right){

        if(i==preOrder.length){
            return null;
        }

        int inOrderNodeIndex = -1;
        for(int k=left; k<=right; k++){
            if(inOrder[k].equals(preOrder[i])){
                inOrderNodeIndex = k;
                break;
            }
        }

        if(inOrderNodeIndex==-1) {
            return  null;
        }

        Node<T> node = new Node<>(preOrder[i]);
        node.left = treeFromPreAndInOrder(preOrder, i+1, inOrder, left, inOrderNodeIndex-1);

        //Rewind i to the element which lies in the right side of the node in inOrder traversal.
        i += (inOrderNodeIndex)-left + 1;

        node.right = treeFromPreAndInOrder(preOrder, i, inOrder, inOrderNodeIndex+1, right);

        return node;
    }
}
