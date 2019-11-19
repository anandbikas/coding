package com.anand.coding.dsalgo.tree;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.print(node.getData() + "  ");
        preOrderTraversalRec(node.getLeft());
        preOrderTraversalRec(node.getRight());
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
        inOrderTraversalRec(node.getLeft());
        System.out.print(node.getData() + "  ");
        inOrderTraversalRec(node.getRight());
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
        postOrderTraversalRec(node.getLeft());
        postOrderTraversalRec(node.getRight());
        System.out.print(node.getData() + "  ");
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
            System.out.print(node.getData() + "  ");

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
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

        for(Node node = root; node!= null; node=node.getLeft()){
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.getData() + "  ");

            for(node=node.getRight(); node!= null; node = node.getLeft()){
                stack.push(node);
            }
        }

        System.out.println();
    }

    /**
     * Print in order left, right, root
     * Use loop with the help of two stacks.
     *
     * Space complexity: O(height)
     */
    public void postOrderTraversal(){
        System.out.println("postOrderTraversal");

        Stack<Node> stack = new ArrayStack<>();
        Stack<Node> rootStack = new ArrayStack<>();

        if(root != null){
            stack.push(root);
        }
        while(!stack.isEmpty()){
            Node node = stack.pop();

            if(node==null){
                //Process root node
                System.out.print(rootStack.pop().getData() + "  ");

            } else {
                //Push null to indicate a root needs to be processed from rootStack
                stack.push(null);
                rootStack.push(node);

                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
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

        Queue<Node> queue = new ArrayCircularQueue<>();

        if(root!=null){
            queue.insert(root);
        }

        while(!queue.isEmpty()){
            Node node = queue.delete();
            System.out.print(node.getData() + "  ");
            if(node.getLeft()!=null){
                queue.insert(node.getLeft());
            }
            if(node.getRight()!=null){
                queue.insert(node.getRight());
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
                System.out.print(node.getData() + "  ");

                if(level%2==1) {
                    if (node.getLeft() != null) {
                        stack.push(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        stack.push(node.getRight());
                    }
                } else {
                    if (node.getRight() != null) {
                        stack.push(node.getRight());
                    }
                    if (node.getLeft() != null) {
                        stack.push(node.getLeft());
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
            System.out.print( node.getData() + " ");
            return;
        }
        printLevel(node.getLeft(), level, currentLevel+1);
        printLevel(node.getRight(), level, currentLevel+1);

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
                System.out.print( node.getData() + " ");
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

        verticalOrderTraversal(node.getLeft(), nodeMap, currentLevel-1, range);
        verticalOrderTraversal(node.getRight(), nodeMap,currentLevel+1, range);
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

        calculateVerticalLevelRange(node.getLeft(), currentLevel-1, range);
        calculateVerticalLevelRange(node.getRight(), currentLevel+1, range);
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
            System.out.print( node.getData() + " ");
        }
        printVerticalLevel(node.getLeft(), level, currentLevel-1);
        printVerticalLevel(node.getRight(), level, currentLevel+1);
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

        if(node==null || node.getData().equals(data)){
            return node;
        }
        Node<T> foundNode = searchRec(node.getLeft(), data);
        if(foundNode != null){
            return foundNode;
        }
        return searchRec(node.getRight(), data);
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
            System.out.println(node.getData());
            processingLevel++;
        }
        processingLevel = leftSideView(node.getLeft(), currentLevel+1, processingLevel);
        processingLevel = leftSideView(node.getRight(), currentLevel+1, processingLevel);

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
            System.out.println(node.getData());
            processingLevel++;
        }
        processingLevel = rightSideView(node.getRight(), currentLevel+1, processingLevel);
        processingLevel = rightSideView(node.getLeft(), currentLevel+1, processingLevel);

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
        if(node.getLeft()==null && node.getRight()==null){
            System.out.print(node.getData() + "  ");
            return;
        }
        printLeaves(node.getLeft());
        printLeaves(node.getRight());
    }

    /**
     *
     * @param data
     */
    public void printPath(T data) {
        System.out.println("printPath");

        Stack<Node> pathStack = new ArrayStack<>();
        printPath(root, data, pathStack);
        System.out.println();
    }

    /**
     *
     * @param node
     * @param data
     * @param pathStack
     */
    private void printPath(final Node<T> node, T data , final Stack<Node> pathStack){
        if(node == null){
            return;
        }
        pathStack.push(node);
        if(node.getData().equals(data)){
            pathStack.display();
            return;
        }

        printPath(node.getLeft(), data, pathStack);
        printPath(node.getRight(), data, pathStack);

        //Once right subTree is processed, remove its parent
        pathStack.pop();
    }

    /**
     *
     */
    public void printAllPaths() {
        System.out.println("printAllPaths");

        Stack<Node> pathStack = new ArrayStack<>();
        printAllPaths(root, pathStack);
        System.out.println();
    }

    /**
     *  Print all the paths of the tree.
     *  with the help of a stack
     *
     * @param node
     * @param pathStack used to push the traversed node one by one.
     *              Pop the node once its path is processed.
     *              Pop the node once all the paths of its right subTree is processed.
     */
    private void printAllPaths(final Node node, final Stack<Node> pathStack){
        if(node == null){
            return;
        }
        pathStack.push(node);
        if(node.getLeft()==null && node.getRight()==null){
            //TODO: Instead of printing paths we can add the path to pathList provided in function parameter
            pathStack.display();
            pathStack.pop();
            return;
        }
        printAllPaths(node.getLeft(), pathStack);
        printAllPaths(node.getRight(), pathStack);

        //Once right subTree is processed, remove its parent
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
    public int height(Node node){
        return node==null? 0 : Math.max(height(node.getLeft()),height(node.getRight())) + 1;
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
        return height(node.getLeft()) - height(node.getRight());
    }

    /**
     * Diameter of the tree (longest path in the tree)
     *
     * @return
     */
    public int diameter(){
        int[] A = diameterCalculator(root);
        return A[1];
    }

    /**
     * Just like height method. Twist is to calculate diameter as well.
     *
     * Return array of 2 elements: [height, diameter].
     * @param node
     * @return []
     */
    private int[] diameterCalculator(Node<T> node){

        if(node == null){
            return new int[]{0,0};
        }
        int [] left = diameterCalculator(node.getLeft());
        int [] right = diameterCalculator(node.getRight());

        int thisNodeHeight = Math.max(left[0], right[0]) +1;
        int thisNodeDiameter = left[0]+right[0]+1;

        int maxDiameterInThisTree = Math.max((Math.max(left[1], right[1])), thisNodeDiameter);
        return new int[]{thisNodeHeight, maxDiameterInThisTree};

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

        return isSimilar(node1.getLeft(), node2.getLeft()) && isSimilar(node1.getRight(), node2.getRight());
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


        return node1.getData().equals(node2.getData())
                && isCopy(node1.getLeft(), node2.getLeft())
                && isCopy(node1.getRight(), node2.getRight());
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

            result = pair.node1.getData().equals(pair.node2.getData());

            stack.push(new NodePair(pair.node1.getLeft(), pair.node2.getLeft()));
            stack.push(new NodePair(pair.node1.getRight(), pair.node2.getRight()));
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

        if(!isBinarySearchTree(node.getLeft(), prevVisitedNode)){
            return false;
        }

        if(prevVisitedNode.getData()!=null && prevVisitedNode.getData().compareTo(node.getData())>0){
            return false;
        }
        prevVisitedNode.setData(node.getData());
        return isBinarySearchTree(node.getRight(), prevVisitedNode);
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
        return 1 + numberOfNodes(node.getLeft()) + numberOfNodes(node.getRight());
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
        Node<T> temp =  node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(temp);

        toMirrorImage(node.getLeft());
        toMirrorImage(node.getRight());
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
        if(root.getData().equals(nodeData)){
            final Node<T> newNode = new Node<>(data);
            newNode.setLeft(root.getLeft());
            root.setLeft(newNode);
            return true;
        } else{
            return insertAsLeftChild(root.getLeft(), data, nodeData)
                    || insertAsLeftChild(root.getRight(), data, nodeData);
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
        if(root.getData().equals(nodeData)){
            final Node<T> newNode = new Node<>(data);
            newNode.setRight(root.getRight());
            root.setRight(newNode);
            return true;
        } else{
            return insertAsRightChild(root.getLeft(), data, nodeData)
                    || insertAsRightChild(root.getRight(), data, nodeData);
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

        binaryTree.inOrderTraversal();
        binaryTree.inOrderTraversalRec();

        binaryTree.preOrderTraversal();
        binaryTree.preOrderTraversalRec();

        binaryTree.postOrderTraversalRec();
        binaryTree.postOrderTraversal();

        binaryTree.levelOrderTraversal();
        binaryTree.levelOrderTraversalBruteForce();
        binaryTree.levelOrderSpiralTraversal();

        binaryTree.printPath(8);
        binaryTree.printAllPaths();
        binaryTree.printLevel(3);
        binaryTree.printLeaves();
        binaryTree.leftSideView();
        binaryTree.rightSideView();

        System.out.println("binaryTree.heightBalanceFactor(3): " + binaryTree.heightBalanceFactor(binaryTree.searchRec(3)));
        System.out.println("binaryTree.heightBalanceFactor(1): " + binaryTree.heightBalanceFactor(binaryTree.searchRec(1)));

        System.out.println("binaryTree.isBinarySearchTree(): " + binaryTree.isBinarySearchTree());

        binaryTree.toMirrorImage();

        System.out.println("After Converting the tree to mirror image");
        binaryTree.leftSideView();
        binaryTree.rightSideView();

        System.out.println("binaryTree.numberOfNodes(): " + binaryTree.numberOfNodes());
        System.out.println("binaryTree.isSimilar(binaryTree): " + binaryTree.isSimilar(binaryTree));
        System.out.println("binaryTree.isCopy(binaryTree): " + binaryTree.isCopy(binaryTree));
        System.out.println("binaryTree.isCopyUsingLoop(binaryTree): " + binaryTree.isCopyUsingLoop(binaryTree));
        System.out.println("binaryTree.height(): " + binaryTree.height());
        System.out.println("binaryTree.searchRec(4) " + binaryTree.searchRec(4));
        System.out.println("binaryTree.searchRec(9) " + binaryTree.searchRec(9));


        // Construct tree using preOrder and inOrder traversals
        /**
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
        tree1.verticalOrderTraversalBruteForce();
        tree1.verticalOrderTraversal();

        System.out.println("binaryTree.isCopyUsingLoop(tree1): " + binaryTree.isCopyUsingLoop(tree1));


        /**
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
        BinaryTree<String> tree2 =
                new BinaryTree<>("x a b d f h e g".split("\\s+"), "f h d b g e a x".split("\\s+"));

        System.out.println(tree2.diameter());
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
        node.setLeft(treeFromPreAndInOrder(preOrder, i+1, inOrder, left, inOrderNodeIndex-1));

        //Rewind i to the element which lies in the right side of the node in inOrder traversal.
        i += (inOrderNodeIndex)-left + 1;

        node.setRight(treeFromPreAndInOrder(preOrder, i, inOrder, inOrderNodeIndex+1, right));

        return node;
    }
}
