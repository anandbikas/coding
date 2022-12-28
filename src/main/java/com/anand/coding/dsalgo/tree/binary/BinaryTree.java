package com.anand.coding.dsalgo.tree.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Binary Tree
 *
 * A binary tree has two child nodes left and right.
 *
 * 1. A binary tee with n nodes has exactly n-1 edges
 * 2. Maximum number of nodes at level l is 2 pow(l-1).
 * 3. Maximum number of nodes in a binary tree of height h is 2 pow(h) – 1.
 * 4. In a Binary Tree with n nodes, minimum possible height is ceil(log2(n+1))
 * 5. Number of nodes in a complete binary tree lies between 2 pow(h-1) to 2 pow(h) -1
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
    private void preOrderTraversalRec(final Node<T> node){
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
    private void inOrderTraversalRec(final Node<T> node){
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
    private void postOrderTraversalRec(final Node<T> node){
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

        Stack<Node<T>> stack = new Stack<>();
        if(root!=null){
            stack.push(root);
        }

        while(!stack.isEmpty()) {
            Node<T> node = stack.pop();
            System.out.print(node.data + "  ");

            if (node.right != null) stack.push(node.right);
            if (node.left != null)  stack.push(node.left);
        }
        System.out.println();
    }

    /**
     * Print in order left, root, right
     * Use loop with the help of a stack
     */
    public void inOrderTraversal(){
        System.out.println("inOrderTraversal");

        Stack<Node<T>> stack = new Stack<>();
        for(Node<T> node = root; node!=null; node=node.left){
            stack.push(node);
        }

        while(!stack.isEmpty()){
            Node<T> node = stack.pop();
            System.out.print(node.data + "  ");

            for(node=node.right; node!=null; node=node.left){
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

            if (node.right != null) stack.push(node.right);
            if (node.left != null)  stack.push(node.left);
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

            if(node.left!=null)  queue.add(node.left);
            if(node.right!=null) queue.add(node.right);
        }

        System.out.println();
    }

    /**
     * Print in level order
     * Use loop with the help of a queue
     */
    public List<List<T>> levelOrderTraversal1() {

        List<List<T>> list = new LinkedList<>();

        Queue<Node<T>> q = new ArrayDeque<>(); //new LinkedList<>();
        if(root!=null){
            q.add(root);
        }

        while(!q.isEmpty()) {

            List<T> currentList = new ArrayList<>();
            list.add(currentList);

            for (int size=q.size(); size > 0; size--) {
                Node<T> node = q.remove();
                currentList.add(node.data);

                if(node.left!=null)  q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }

        return list;
    }

    /**
     * Print in level order zigzag/spiral
     * Use loop with the help of a queue and a stack
     */
    public void levelOrderSpiralTraversal(){
        System.out.println("levelOrderSpiralTraversal");

        Queue<Node<T>> queue = new ArrayDeque<>();
        Stack<Node<T>> stack = new Stack<>();
        if(root!=null){
            queue.add(root);
        }

        for (int level=1; !queue.isEmpty(); level++) {

            while (!queue.isEmpty()) {
                Node<T> node = queue.remove();
                System.out.print(node.data + "  ");

                if(level%2==1) {
                    if (node.left != null) stack.push(node.left);
                    if (node.right!= null) stack.push(node.right);
                } else {
                    if (node.right!= null) stack.push(node.right);
                    if (node.left != null) stack.push(node.left);
                }
            }
            while(!stack.isEmpty()) {
                queue.add(stack.pop());
            }
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
        printLevel(node.left, level, currentLevel+1);
        printLevel(node.right, level, currentLevel+1);
    }

    /**
     *
     * @param level
     */
    public void printVerticalLevel(int level){
        System.out.print("printVerticalLevel " + level + " : ");
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
    private void printVerticalLevel(Node<T> node, int level, int currentLevel){
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
     * @param node
     * @param vLevel
     * @param range
     */
    public void verticalLevelRange(Node<T> node, int vLevel, Range range){
        if(node == null){
            return;
        }

        if(vLevel<range.left)  range.left=vLevel;
        if(vLevel>range.right) range.right = vLevel;

        verticalLevelRange(node.left, vLevel-1, range);
        verticalLevelRange(node.right, vLevel+1, range);
    }

    public static class Range {
        public int left=0, right=-1; //Range: an empty range has right < left
    }

    /**
     * Call printLevelVertical method for each vertical level
     */
    public void verticalOrderTraversalBruteForce(){
        System.out.println("verticalOrderTraversalBruteForce :");

        Range verticalRange = new Range();
        verticalLevelRange(root, 0, verticalRange);

        for(int vLevel = verticalRange.left; vLevel<=verticalRange.right; vLevel++){
            printVerticalLevel(vLevel);
        }
        System.out.println();
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

        Node<T> foundNode;
        return (foundNode=searchRec(node.left, data)) == null ? searchRec(node.right, data) : foundNode;
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
    private int rightSideView(Node<T> node, int currentLevel, int processingLevel){
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
    private void printLeaves(final Node<T> node){
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

        Stack<Node<T>> pathStack = new Stack<>();
        displayPaths(root, data, pathStack);
        System.out.println();
    }

    /**
     *
     * @param node
     * @param data
     * @param pathStack keep track of the nodes in the traversed path.
     */
    private void displayPaths(final Node<T> node, T data , final Stack<Node<T>> pathStack){
        if(node == null){
            return;
        }
        pathStack.push(node);
        if(node.data.equals(data)){
            //Instead of displaying paths we can add them to pathList provided in function
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
        if(node.left==null && node.right==null){
            //Instead of displaying paths we can add them to pathList provided in function
            System.out.println(pathStack.toString());
//            StringBuffer sb = new StringBuffer();
//            for(Object x: pathStack.toArray()) {
//                sb.append(x).append("->");
//            }
//            sb.setLength(sb.length()-2);
//            paths.add(sb.toString());
        } else {
            displayPaths(node.left, pathStack);
            displayPaths(node.right, pathStack);
        }

        pathStack.pop();
    }

    /**
     * List of paths (from root to leaf) with matching targetSum
     */
    public List<List<Integer>> pathsMatchingSum(int targetSum) {
        Stack<Integer> pathStack = new Stack<>();
        List<List<Integer>> paths = new ArrayList<>();
        pathsMatchingSum((Node<Integer>)root, pathStack, 0, paths, targetSum);

        return paths;
    }

    /**
     *
     * @param node
     * @param pathStack
     * @param sum
     * @param paths
     * @param targetSum
     */
    private void pathsMatchingSum(final Node<Integer> node, final Stack<Integer> pathStack, int sum, List<List<Integer>> paths, int targetSum){
        if(node == null) {
            return;
        }
        sum += node.data;
        pathStack.push(node.data);
        if(node.left==null && node.right==null){
            if(sum==targetSum) {
                paths.add(new ArrayList<>(pathStack));
            }
        } else {
            pathsMatchingSum(node.left, pathStack, sum, paths, targetSum);
            pathsMatchingSum(node.right, pathStack, sum, paths, targetSum);
        }

        pathStack.pop();
    }

    /**
     * List of paths (any path) with matching targetSum
     * path-sum-iii/submissions/854651208
     */
    public List<List<Integer>> pathsMatchingSumAll(int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();

        pathsMatchingSumAll((Node<Integer>)root, new Stack<>(), new int[100], 1, 0, paths, targetSum);
        return paths;
    }

    /**
     *
     * @param node
     * @param pathStack
     * @param levelSum
     * @param level
     * @param paths
     * @param targetSum
     */
    private void pathsMatchingSumAll(final Node<Integer> node, final Stack<Integer> pathStack,
                                     int []levelSum, int level, int sum, List<List<Integer>> paths, int targetSum){
        if(node == null) {
            return;
        }

        sum +=node.data;
        levelSum[level] = sum;
        pathStack.push(node.data);
        for(int l=0; l<level; l++) {
            if (sum-levelSum[l] == targetSum) {
                paths.add(new ArrayList<>(pathStack.subList(l,pathStack.size())));
            }
        }

        pathsMatchingSumAll(node.left, pathStack, levelSum, level+1, sum, paths, targetSum);
        pathsMatchingSumAll(node.right, pathStack, levelSum, level+1, sum, paths, targetSum);

        pathStack.pop();
    }

    /**
     * Find the lowest common ancestor node of the two given nodes
     * Method 1: find path for both x and y and find the parent by comparing both the paths.
     * Method 2: Using single traversal
     *
     * @return
     */
    public Node<T> lowestCommonAncestor(T x, T y){
        return lowestCommonAncestor(root, x, y);
    }

    private Node<T> lowestCommonAncestor(Node<T> n, T x, T y){
        if(n==null || n.data.compareTo(x)==0 || n.data.compareTo(y)==0) {
            return n;
        }

        Node<T> left  = lowestCommonAncestor(n.left, x, y);
        Node<T> right = lowestCommonAncestor(n.right, x, y);

        if(left!=null && right!=null){
            return n;
        }

        return (left==null) ? right : left;
    }

    /**
     * Find the lowest common ancestor node of the deepest leaves
     * Method 1: Find the list of the deepest leaves and then LCA of 1st and last nodes of the deepest leaves.
     *
     * problems/lowest-common-ancestor-of-deepest-leaves
     * problems/smallest-subtree-with-all-the-deepest-nodes
     * * @return
     */
    public Node<T> lowestCommonAncestorDeepestLeaves(){
        DeepestLeaves deepestLeaves = new DeepestLeaves();
        findDeepestLeaves(root, deepestLeaves, 0);

        return lowestCommonAncestor(root, deepestLeaves.start.data, deepestLeaves.end.data);
    }

    public void findDeepestLeaves(Node<T> node, DeepestLeaves deepestLeaves, int currentLevel){
        if (node==null){
            return;
        }

        if(node.left==null && node.right==null){
            if(deepestLeaves.level<currentLevel){
                deepestLeaves.level=currentLevel;
                deepestLeaves.start=deepestLeaves.end=null;
            }
            if(deepestLeaves.level==currentLevel) {
                if(deepestLeaves.start==null) deepestLeaves.start=node;
                deepestLeaves.end=node;
            }
        }

        findDeepestLeaves(node.left, deepestLeaves, currentLevel+1);
        findDeepestLeaves(node.right, deepestLeaves, currentLevel+1);
    }

    public class DeepestLeaves {
        int level=0;
        Node<T> start, end;
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
        return node==null ? 0 : Math.max(height(node.left),height(node.right)) + 1;
    }

    /**
     *
     * @return
     */
    public int minHeight(){
        return minHeight(root);
    }

    /**
     * Calculate minHeight of the tree
     *
     * @return
     */
    public int minHeight(Node<T> node){
        if(node==null){
            return 0;
        }

        if(node.left==null)     return minHeight(node.right)+1;
        if (node.right==null)   return minHeight(node.left)+1;

        return Math.min(minHeight(node.left),minHeight(node.right)) + 1;
    }

    /**
     * heightBalanceFactor of a node is heightOfLeftSubTree - heightOfRightSubTree
     * @param node
     * @return
     */
    public int heightBalanceFactor(Node<T> node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
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

        HiDi left  = diameter(node.left);
        HiDi right = diameter(node.right);

        HiDi hidi = new HiDi();
        hidi.height = Math.max(left.height,right.height) + 1;
        hidi.diameter = Math.max((left.height+right.height+1), Math.max(left.diameter,right.diameter));

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
    public static boolean isSimilar(BinaryTree<?> tree1, BinaryTree<?> tree2){
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

        if(node1 == null || node2 == null){
            return false;
        }

        return isSimilar(node1.left, node2.left) && isSimilar(node1.right, node2.right);
    }

    /**
     *
     * @param tree1
     * @param tree2
     * @return
     */
    public static boolean isCopy(BinaryTree<?> tree1, BinaryTree<?> tree2){
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

        if(node1 == null || node2 ==null){
            return false;
        }


        return node1.data.equals(node2.data)
                && isCopy(node1.left, node2.left)
                && isCopy(node1.right, node2.right);
    }

    /**
     * Checks whether the tree is mirror image of itself
     *
     * @return
     */
    public boolean isSymmetric(){
        return root==null || isSymmetric(root.left, root.right);
    }

    /**
     *
     * @param node1
     * @param node2
     * @return
     */
    private boolean isSymmetric(Node<T> node1, Node<T> node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if(node1 == null || node2 ==null){
            return false;
        }

        return node1.data.equals(node2.data)
                && isSymmetric(node1.left, node2.right)
                && isSymmetric(node1.right, node2.left);
    }


    private static class NodePair{
        Node<?> n1, n2;

        public NodePair(Node<?> n1, Node<?> n2){
            this.n1 = n1; this.n2 = n2;
        }
    }

    /**
     *
     * @param tree1
     * @param tree2
     * @return
     */
    public static boolean isCopyUsingLoop(BinaryTree tree1, BinaryTree tree2){
        return isCopyUsingLoop(tree1.root, tree2.root);
    }

    /**
     * Checks whether two trees are similar in structure as well as data
     *
     * @param node1
     * @param node2
     * @return
     */
    private static boolean isCopyUsingLoop(Node<?> node1, Node<?> node2){

        Stack<NodePair> stack = new Stack<>();
        stack.push(new NodePair(node1, node2));

        boolean result = true;
        while(!stack.isEmpty() && result){
            NodePair pair = stack.pop();
            if(pair.n1 == null && pair.n2 == null){
                result = true;
                continue;
            }

            if(pair.n1 == null || pair.n2 ==null){
                result = false;
                continue;
            }

            result = pair.n1.data.equals(pair.n2.data);

            stack.push(new NodePair(pair.n1.left, pair.n2.left));
            stack.push(new NodePair(pair.n1.right, pair.n2.right));
        }

        return result;
    }


    /**
     *
     *
     * @return
     */
    public boolean isBST(){
        return isBST(root, new Node<>());
    }

    /**
     * Checks if the tree is a binary search tree
     * using Inorder Traversal.
     *
     * @param node
     * @return
     */
    private boolean isBST(Node<T> node, Node<T> prevVisitedNode){
        if(node == null){
            return true;
        }

        if(!isBST(node.left, prevVisitedNode)){
            return false;
        }

        if(prevVisitedNode.data!=null && prevVisitedNode.data.compareTo(node.data)>=0){
            return false;
        }
        prevVisitedNode.data = node.data;
        return isBST(node.right, prevVisitedNode);
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
        return size(node.left) + size(node.right) + 1;
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
     * @param n
     * @return
     */
    private void toMirrorImage(Node<T> n){
        if(n==null){
            return;
        }
        Node<T> t = n.left; n.left = n.right; n.right = t;

        toMirrorImage(n.left);
        toMirrorImage(n.right);
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
     * @param node
     * @param data
     * @param nodeData
     * @return
     */
    private boolean insertAsLeftChild(final Node<T> node, final T data, final T nodeData){
        if(null == node){
            return false;
        }

        if(node.data.equals(nodeData)){
            final Node<T> newNode = new Node<>(data);
            newNode.left = node.left;
            node.left = newNode;
            return true;
        }

        return insertAsLeftChild(node.left, data, nodeData)
                || insertAsLeftChild(node.right, data, nodeData);
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
     * @param node
     * @param data
     * @param nodeData
     * @return
     */
    private boolean insertAsRightChild(final Node<T> node, final T data, final T nodeData){
        if(null == node){
            return false;
        }

        if(node.data.equals(nodeData)){
            final Node<T> newNode = new Node<>(data);
            newNode.right = node.right;
            node.right = newNode;
            return true;
        }
        return insertAsRightChild(node.left, data, nodeData)
                || insertAsRightChild(node.right, data, nodeData);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        /*
         * A Binary Tree.
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        final BinaryTree<Integer> tree = new BinaryTree<>(1);
        tree.insertAsLeftChild(2, 1);
        tree.insertAsRightChild(3, 1);
        tree.insertAsLeftChild(4,2);
        tree.insertAsRightChild(5,2);
        tree.insertAsLeftChild(6,3);
        tree.insertAsRightChild(7, 3);
        tree.insertAsLeftChild(8,6);

        // Traversals
        tree.inOrderTraversal();
        tree.inOrderTraversalRec();

        tree.preOrderTraversal();
        tree.preOrderTraversalRec();

        tree.postOrderTraversalRec();
        tree.postOrderTraversal();

        tree.levelOrderTraversal();
        System.out.println(tree.levelOrderTraversal1());
        tree.levelOrderTraversalBruteForce();
        tree.levelOrderSpiralTraversal();
        tree.verticalOrderTraversalBruteForce();

        // Searching
        System.out.println("searchRec(4) " + tree.searchRec(4));
        System.out.println("searchRec(9) " + tree.searchRec(9));

        // Paths (Selective prints)
        tree.displayPaths(8);
        tree.displayPaths();
        tree.printLevel(3);
        tree.printLeaves();
        tree.leftSideView();
        tree.rightSideView();
        System.out.println("Diameter: "  + tree.diameter());

        System.out.println("Paths Matching targetSum=11: "  + tree.pathsMatchingSum(11));
        System.out.println("Paths Matching targetSum=18: "  + tree.pathsMatchingSum(18));

        final BinaryTree<Integer> bt = new BinaryTree<>(1);
        bt.insertAsRightChild(2, 1);
        bt.insertAsRightChild(3, 2);
        bt.insertAsRightChild(4, 3);
        bt.insertAsRightChild(5, 4);
        System.out.println("PathsAll Matching targetSum=3: "  + bt.pathsMatchingSumAll(3));

        // LCA
        System.out.println("findLca(4,5): " + tree.lowestCommonAncestor(8,5));
        System.out.println("findLca(5,8): " + tree.lowestCommonAncestor(3,8));
        System.out.println("lowestCommonAncestorDeepestLeaves(): " + tree.lowestCommonAncestorDeepestLeaves());

        // Utility
        System.out.println("numberOfNodes(): " + tree.size());
        System.out.println("height(): " + tree.height());
        System.out.println("heightBalanceFactor(3): " + tree.heightBalanceFactor(tree.searchRec(3)));

        // Comparison
        System.out.println("isBST(): " + tree.isBST());
        System.out.println("isSimilar(tree, tree): " + BinaryTree.isSimilar(tree, tree));
        System.out.println("isCopy(tree, tree): " + BinaryTree.isCopy(tree, tree));
        System.out.println("isCopyUsingLoop(tree, tree): " + BinaryTree.isCopyUsingLoop(tree, tree));

        // Conversion
        tree.toMirrorImage();
        System.out.println("After Converting the tree to mirror image");
        tree.leftSideView();
        tree.rightSideView();


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

        //Rewind i to the element which lies on the right side of the node in inOrder traversal.
        i += (inOrderNodeIndex)-left + 1;

        node.right = treeFromPreAndInOrder(preOrder, i, inOrder, inOrderNodeIndex+1, right);

        return node;
    }

    /**
     * Construct a tree using postOrder and inOrder traversals
     *
     * @param postOrder
     * @param inOrder
     */
//    public BinaryTree(T [] postOrder, T [] inOrder){
//        this.root = treeFromPreAndInOrder(postOrder, postOrder.length-1, inOrder, 0, inOrder.length-1);
//    }

    private Node<T> treeFromPostAndInOrder(T [] postOrder, int i, T [] inOrder, int left, int right){

        if(i==-1){
            return null;
        }

        int inOrderNodeIndex = -1;
        for(int k=left; k<=right; k++){
            if(inOrder[k].equals(postOrder[i])){
                inOrderNodeIndex = k;
                break;
            }
        }

        if(inOrderNodeIndex==-1) {
            return  null;
        }

        Node<T> node = new Node<>(postOrder[i]);
        node.right = treeFromPreAndInOrder(postOrder, i-1, inOrder, inOrderNodeIndex+1, right);

        //Rewind i to the element which lies on the right side of the node in inOrder traversal.
        i -= right - (inOrderNodeIndex) + 1;

        node.left = treeFromPreAndInOrder(postOrder, i, inOrder, left, inOrderNodeIndex-1);

        return node;
    }
}
