package com.anand.coding.dsalgo.tree;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

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
    public void inOrderTraversalRec(){
        println("inOrderTraversalRec");
        inOrderTraversalRec(root);
        System.out.println();
    }

    /**
     *
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
    public void preOrderTraversalRec(){
        println("preOrderTraversalRec");
        preOrderTraversalRec(root);
        System.out.println();
    }

    /**
     *
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
    public void postOrderTraversalRec(){
        println("postOrderTraversalRec");
        postOrderTraversalRec(root);
        System.out.println();
    }

    /**
     *
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
     *
     */
    public void preOrderTraversal(){
        println("preOrderTraversal");

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
     *
     */
    public void inOrderTraversal(){
        println("inOrderTraversal");

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
     *
     */
    public void postOrderTraversal(){
        println("postOrderTraversal");
        //TODO: incomplete

    }

    /**
     *
     */
    public void levelOrderTraversal(){
        println("levelOrderTraversal");

        Queue<Node> queue = new ArrayCircularQueue();

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
     *
     */
    public void levelOrderTraversalBruteForce(){
        println("levelOrderTraversalBruteForce");

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
        printLevel(root, level, 1);
        System.out.println();
    }

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
     */
    public void leftSideView(){
        println("leftSideView");
        leftSideView(root, 1, 1);
    }

    /**
     *
     * @param root
     * @param currentLevel
     * @param processingLevel
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
        println("rightSideView");
        rightSideView(root, 1, 1);
    }

    /**
     *
     * @param root
     * @param currentLevel
     * @param processingLevel
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
     * @return
     */
    public int height(){
        return height(root);
    }

    /**
     *
     * @param root
     * @return
     */
    private int height(Node root){
        return root==null? 0 : max(height(root.getLeft()),height(root.getRight())) + 1;
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isSimilar(BinaryTree tree){
        println("isSimilar");
        return isSimilar(this.root, tree.root);
    }

    /**
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
        println("isCopy");
        return isCopy(this.root, tree.root);
    }

    /**
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

    /***
     *
     * @return
     */
    public boolean isBinarySearchTree(){
        println("isBinarySearchTree");
        return isBinarySearchTree(root);
    }

    /**
     *
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
     * TODO:
     * diameter
     * printLeaves
     * convertMirror
     * printPaths
     * LCS lowest common ancestor
     */

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private int max(int x, int y){
        return (x>y) ? x : y;
    }


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
        binaryTree.preOrderTraversalRec();
        binaryTree.preOrderTraversal();
        binaryTree.postOrderTraversalRec();
        println("binaryTree.numberOfNodes(): " + binaryTree.numberOfNodes());

        println("binaryTree.isSimilar(binaryTree): " + binaryTree.isSimilar(binaryTree));
        println("binaryTree.isSimilar(binaryTree): " + binaryTree.isCopy(binaryTree));

        println("binaryTree.printLevel(3): ");
        binaryTree.printLevel(3);

        println("binaryTree.height(): " + binaryTree.height());

        binaryTree.levelOrderTraversalBruteForce();
        binaryTree.levelOrderTraversal();
        binaryTree.leftSideView();

        binaryTree.rightSideView();

    }

    public static void println(String s){
        System.out.println(s);
    }
}
