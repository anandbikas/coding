package com.anand.coding.dsalgo.tree;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

/**
 * Binary Tree
 */
public class BinaryTree {

    protected Node root;

    public BinaryTree(){
        super();
    }

    public BinaryTree(final int data){
        root = new Node(data);
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
     * @param root
     */
    private void preOrderTraversalRec(final Node root){
        if(root == null){
            return;
        }
        System.out.print(root.getData() + "  ");
        preOrderTraversalRec(root.getLeft());
        preOrderTraversalRec(root.getRight());
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
     * @param root
     */
    private void inOrderTraversalRec(final Node root){
        if(root == null){
            return;
        }
        inOrderTraversalRec(root.getLeft());
        System.out.print(root.getData() + "  ");
        inOrderTraversalRec(root.getRight());
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
     * @param root
     */
    private void postOrderTraversalRec(final Node root){
        if(root == null){
            return;
        }
        postOrderTraversalRec(root.getLeft());
        postOrderTraversalRec(root.getRight());
        System.out.print(root.getData() + "  ");
    }

    /**
     * Print in order root, left, right
     * Without recursion
     */
    public void preOrderTraversal(){
        System.out.println("preOrderTraversal");

        Stack<Node> stack = new ArrayStack();

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

        Stack<Node> stack = new ArrayStack<Node>();

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
     * Use loop with the help of
     */
    public void postOrderTraversal(){
        System.out.println("postOrderTraversal");
        //TODO: incomplete

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
     * @param root
     * @param level
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     */
    private void printLevel(Node root, int level, int currentLevel){
        if(root == null){
            return;
        }
        if(level == currentLevel){
            System.out.print( root.getData() + " ");
            return;
        }
        printLevel(root.getLeft(), level, currentLevel+1);
        printLevel(root.getRight(), level, currentLevel+1);

    }

    /**
     *
     * @param data
     * @return
     */
    public Node searchRec(int data){
        return searchRec(root, data);
    }

    /**
     * If root is not the one, check in right subTree only if the data is not found in left subTree.
     *
     * @param root
     * @param data
     * @return
     */
    private Node searchRec(Node root, int data){

        if(root==null || root.getData()==data){
            return root;
        }
        Node node = searchRec(root.getLeft(), data);
        if(node != null){
            return node;
        }
        return searchRec(root.getRight(), data);
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
     * @param root
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     * @param processingLevel decides how many levels have already been processed.
     * @return
     */
    private int leftSideView(Node root, int currentLevel, int processingLevel){
        if(root== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(root.getData());
            processingLevel++;
        }
        processingLevel = leftSideView(root.getLeft(), currentLevel+1, processingLevel);
        processingLevel = leftSideView(root.getRight(), currentLevel+1, processingLevel);

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
     * @param root
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     * @param processingLevel decides how many levels have already been processed.
     * @return
     */
    private int rightSideView(Node root, int currentLevel, int processingLevel){
        if(root== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(root.getData());
            processingLevel++;
        }
        processingLevel = rightSideView(root.getRight(), currentLevel+1, processingLevel);
        processingLevel = rightSideView(root.getLeft(), currentLevel+1, processingLevel);

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
     * @param root
     */
    private void printLeaves(final Node root){
        if(root == null){
            return;
        }
        if(root.getLeft()==null && root.getRight()==null){
            System.out.print(root.getData() + "  ");
            return;
        }
        printLeaves(root.getLeft());
        printLeaves(root.getRight());
    }

    /**
     *
     */
    public void printAllPaths() {
        System.out.println("printAllPaths");

        Stack<Node> stack = new ArrayStack<Node>();
        printAllPaths(root, stack);
        System.out.println();
    }

    /**
     *  Print all the paths of the tree.
     *  with the help of a stack
     *
     * @param root
     * @param stack used to push the traversed node one by one.
     *              Pop the node once its path is processed.
     *              Pop the node once all the paths of its right subTree is processed.
     */
    private void printAllPaths(final Node root, final Stack<Node> stack){
        if(root == null){
            return;
        }
        stack.push(root);
        if(root.getLeft()==null && root.getRight()==null){
            stack.display();
            stack.pop();
            return;
        }
        printAllPaths(root.getLeft(), stack);
        printAllPaths(root.getRight(), stack);

        //Once right subTree is processed, remove its parent
        stack.pop();
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
     * @param root1
     * @param root2
     * @return
     */
    private boolean isSimilar(Node root1, Node root2){
        if(root1 == null && root2 == null){
            return true;
        }

        if(root1 == null || root2 ==null){
            return false;
        }

        return isSimilar(root1.getLeft(), root2.getLeft()) && isSimilar(root1.getRight(), root2.getRight());
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
     * @param root1
     * @param root2
     * @return
     */
    private boolean isCopy(Node root1, Node root2){
        if(root1 == null && root2 == null){
            return true;
        }

        if(root1 == null || root2 ==null){
            return false;
        }


        return root1.getData()==root2.getData()
                && isCopy(root1.getLeft(), root2.getLeft())
                && isCopy(root1.getRight(), root2.getRight());
    }

    /**
     *
     *
     * @return
     */
    public boolean isBinarySearchTree(){
        return isBinarySearchTree(root);
    }

    /**
     * Checks if the tree is a binary search tree
     * @param root
     * @return
     */
    private boolean isBinarySearchTree(Node root){
        if(root == null){
            return true;
        }

        return (root.getLeft()==null || root.getLeft().getData() < root.getData())
                    && (root.getRight()==null || root.getRight().getData() > root.getData())
                        && isBinarySearchTree(root.getLeft())
                            && isBinarySearchTree(root.getRight());
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
     * @param root
     * @return
     */
    private int numberOfNodes(Node root){
        if(root==null){
            return 0;
        }
        return 1 + numberOfNodes(root.getLeft()) + numberOfNodes(root.getRight());
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
     * @param root
     * @return
     */
    private void toMirrorImage(Node root){
        if(root==null){
            return;
        }
        Node temp =  root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(temp);

        toMirrorImage(root.getLeft());
        toMirrorImage(root.getRight());
    }

    /**
     * TODO:
     * diameter
     */

    /**
     *
     * @param data
     * @param nodeData
     * @return
     */
    public boolean insertAsLeftChild(final int data, final int nodeData){
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
    private boolean insertAsLeftChild(final Node root, final int data, final int nodeData){

        if(null == root){
            return false;
        }
        if(root.getData() == nodeData){
            final Node newNode = new Node(data);
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
    public boolean insertAsRightChild(final int data, final int nodeData){
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
    private boolean insertAsRightChild(final Node root, final int data, final int nodeData){

        if(null == root){
            return false;
        }
        if(root.getData() == nodeData){
            final Node newNode = new Node(data);
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

        /**
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        final BinaryTree binaryTree = new BinaryTree(1);

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

        binaryTree.printAllPaths();
        binaryTree.printLevel(3);
        binaryTree.printLeaves();
        binaryTree.leftSideView();
        binaryTree.rightSideView();

        System.out.println("binaryTree.heightBalanceFactor(3): " + binaryTree.heightBalanceFactor(binaryTree.searchRec(3)));
        System.out.println("binaryTree.heightBalanceFactor(1): " + binaryTree.heightBalanceFactor(binaryTree.searchRec(1)));

        binaryTree.toMirrorImage();

        System.out.println("After Converting the tree to mirror image");
        binaryTree.leftSideView();
        binaryTree.rightSideView();

        System.out.println("binaryTree.numberOfNodes(): " + binaryTree.numberOfNodes());
        System.out.println("binaryTree.isSimilar(binaryTree): " + binaryTree.isSimilar(binaryTree));
        System.out.println("binaryTree.isSimilar(binaryTree): " + binaryTree.isCopy(binaryTree));
        System.out.println("binaryTree.height(): " + binaryTree.height());
        System.out.println("binaryTree.searchRec(4) " + binaryTree.searchRec(4));
        System.out.println("binaryTree.searchRec(9) " + binaryTree.searchRec(9));
    }
}
