package com.anand.coding.dsalgo.tree;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

/**
 * AVLTree
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     *
     */
    public AVLTree() {
        super();
    }

    /**
     *
     * @return
     */
    @Override
    public int height(){
        return height(root);
    }

    /**
     *
     * @param node
     * @return
     */
    @Override
    public int height(final Node node){
        if(node==null){
            return 0;
        }
        return node.getHeight();
    }

    /**
     *
     * @param node
     */
    private void calculateHeight(final Node node){
        if(node == null){
            return;
        }
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))+1);
    }

    /**
     * heightBalanceFactor of root node
     * @return
     */
    public int heightBalanceFactor(){
        return heightBalanceFactor(root);
    }

    /**
     * heightBalanceFactor of a node is heightOfLeftSubTree - heightOfRightSubTree
     * @param node
     * @return
     */
    @Override
    public int heightBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    /**
     *
     * @return
     */
    public boolean isAVLTree() {
        return isAVLTree(root);
    }

    /**
     *
     * @return
     */
    public boolean isAVLTree(Node node){
        if(node == null){
            return true;
        }
        if(isAVLTree(node.getLeft()) && isAVLTree(node.getRight())){
            return Math.abs(heightBalanceFactor(node))<2;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isHeightCorrectForAllNodes() {
        return isHeightCorrectForAllNodes(root);
    }

    /**
     *
     * @return
     */
    public boolean isHeightCorrectForAllNodes(Node node){
        if(node == null){
            return true;
        }
        if(isHeightCorrectForAllNodes(node.getLeft()) && isHeightCorrectForAllNodes(node.getRight())){
            return Math.max(height(node.getLeft()), height(node.getRight()))+1 == node.getHeight();
        }
        return false;
    }

    /**
     *
     * @param pivotNode
     * @param parent
     */
    private void leftRotate(Node<T> pivotNode, Node<T> parent){

        if(pivotNode == null || pivotNode.getRight() == null){
            return;
        }

        Node<T> tmpNode = pivotNode.getRight();
        pivotNode.setRight(tmpNode.getLeft());
        tmpNode.setLeft(pivotNode);

        if(parent == null){
            this.root = tmpNode;
        } else if(parent.getLeft()==pivotNode){
            parent.setLeft(tmpNode);
        } else {
            parent.setRight(tmpNode);
        }
        calculateHeight(pivotNode);
        calculateHeight(tmpNode);
    }

    /**
     *
     * @param pivotNode
     * @param parent
     */
    private void rightRotate(Node<T> pivotNode, Node<T> parent) {

        if (pivotNode == null || pivotNode.getLeft() == null) {
            return;
        }

        Node<T> tmpNode = pivotNode.getLeft();
        pivotNode.setLeft(tmpNode.getRight());
        tmpNode.setRight(pivotNode);

        if (parent == null) {
            this.root = tmpNode;
        } else if (parent.getLeft() == pivotNode) {
            parent.setLeft(tmpNode);
        } else {
            parent.setRight(tmpNode);
        }
        calculateHeight(pivotNode);
        calculateHeight(tmpNode);
    }

    /**
     *
     * @param data
     * @return
     */
    @Override
    public Node insert(T data){

        // Part A: Search and insert if not found, maintain a parent pathStack
        // -------------------------------------------------------------------
        Stack<Node<T>> pathStack = new ArrayStack<>();

        Node<T> parent = null;
        Node<T> pivotNode = root;

        // Simple BST insertion
        while(!(pivotNode == null || pivotNode.getData().compareTo(data)==0)){
            pathStack.push(parent);
            parent = pivotNode;
            pivotNode = pivotNode.getData().compareTo(data) > 0 ? pivotNode.getLeft() : pivotNode.getRight();
        }

        if(pivotNode!=null) {
            //Duplicate node rejected.
            return null;
        }

        pivotNode = new Node<>(data);
        if(parent == null){
            this.root = pivotNode;
        } else if(parent.getData().compareTo(data)>0){
            parent.setLeft(pivotNode);
        } else {
            parent.setRight(pivotNode);
        }


        // Part B: AVL Tree height recalculation and perform heightBalanceFactor rotation algorithm
        // ----------------------------------------------------------------------------------------
        calculateHeight(parent);
        Node<T> child = pivotNode;

        while(!(pathStack.isEmpty() || pathStack.peek()==null)){
            Node<T> grandParent = pathStack.pop();
            calculateHeight(grandParent);

            final int heightBalanceFactor = heightBalanceFactor(grandParent);
            if(heightBalanceFactor>1) {
                //Insertion is in left subTree
                if (parent.getLeft() == child) {
                    //Left To Left Case
                    rightRotate(grandParent, pathStack.peek());
                } else {
                    //Left To Right Case
                    leftRotate(parent, grandParent);
                    rightRotate(grandParent, pathStack.peek());
                }
                break;
            } else if(heightBalanceFactor<-1){
                //Insertion is in right subTree
                if(parent.getRight()==child){
                    //Right To Right case
                    leftRotate(grandParent, pathStack.peek());
                } else {
                    //Right To Left case
                    rightRotate(parent, grandParent);
                    leftRotate(grandParent, pathStack.peek());
                }
                break;
            }
            child=parent;
            parent=grandParent;
        }

        return pivotNode;
    }


    /**
     * Delete the pivotNode matching the data
     *
     * @param data
     * @return
     */
    public Node<T> delete(T data){

        // Part A: Search and delete if found, maintain a parent pathStack
        // ---------------------------------------------------------------
        Stack<Node<T>> pathStack = new ArrayStack<>();

        Node<T> parent = null;
        Node<T> pivotNode = root;

        //Find the pivotNode and its parent.
        while(!(pivotNode == null || pivotNode.getData().compareTo(data)==0)){
            pathStack.push(parent);
            parent = pivotNode;
            pivotNode = pivotNode.getData().compareTo(data) > 0 ? pivotNode.getLeft() : pivotNode.getRight();
        }
        pathStack.push(parent);

        // if pivotNode not found, return
        if(pivotNode == null){
            return null;
        }

        Queue<Node<T>> queue = new ArrayCircularQueue<>();

        Node<T> nodeToShift;

        if(pivotNode.getRight() == null) {
            //If pivot node has no inOrder successor, replace pivot with its left node.
            nodeToShift = pivotNode.getLeft();

        } else {
            // Replace pivot with its inorder successor
            // 1. update left of inOrderSuccessor's parent with inOrderSuccessor's right node.
            // 2. update left and right of inOrderSuccessor node with those of pivot node.
            Node<T> parentOfNodeToShift = pivotNode;
            nodeToShift = pivotNode.getRight();

            while(nodeToShift.getLeft()!= null){
                queue.insert(parentOfNodeToShift);
                parentOfNodeToShift = nodeToShift;
                nodeToShift = nodeToShift.getLeft();
            }
            queue.insert(parentOfNodeToShift);

            if(parentOfNodeToShift != pivotNode) {
                parentOfNodeToShift.setLeft(nodeToShift.getRight());
                nodeToShift.setRight(pivotNode.getRight());
            }
            nodeToShift.setLeft(pivotNode.getLeft());
        }

        if(parent == null){
            root = nodeToShift;
        } else if(parent.getLeft() == pivotNode){
            parent.setLeft(nodeToShift);
        } else {
            parent.setRight(nodeToShift);
        }

        // Add nodeToShift along with all nodes in the queue to stack.
        if(nodeToShift != null) {
            pathStack.push(nodeToShift);
        }
        while(!queue.isEmpty()){
            pathStack.push(queue.delete());
        }

        // AVL Tree height recalculation and heightBalanceFactor rotation algorithm
        // ------------------------------------------------------------------------
        while(!(pathStack.isEmpty() || pathStack.peek()==null)){
            Node<T> node = pathStack.pop();
            calculateHeight(node);

            final int heightBalanceFactor = heightBalanceFactor(node);

            //Check if this node is unbalanced, then four cases are there
            if(heightBalanceFactor>1) {
                //left subTree heavy
                if (heightBalanceFactor(node.getLeft())>=0) {
                    //Left To Left Case
                    rightRotate(node, pathStack.peek());
                } else {
                    //Left To Right Case
                    leftRotate(node.getLeft(), node);
                    rightRotate(node, pathStack.peek());
                }
            } else if(heightBalanceFactor<-1){
                //right subTree heavy
                if(heightBalanceFactor(node.getRight())<=0){
                    //Right To Right case
                    leftRotate(node, pathStack.peek());
                } else {
                    //Right To Left case
                    rightRotate(node.getRight(), node);
                    leftRotate(node, pathStack.peek());
                }
            }
        }

        pivotNode.setLeft(null);
        pivotNode.setRight(null);
        return pivotNode;
    }


    /**
     * main function to test AVLTree
     *
     * @param args
     */
    public static void main(String args[]){

        intenseInsertionDeletionTest(64);


        /** Now perform normal testing with a small tree
         *
         *                 7
         *            3          11
         *         2     6    8      12
         *       1     4        10      13
         */
        final AVLTree<Integer> avlTree = new AVLTree<>();

        for(int x : new int[]{ 7,11,3,8,6,12,2,1,4,13,10}) {
            avlTree.insert(x);
        }
        avlTree.printAllPaths();

        //Insertions in AVL Tree
        //LL Case rotation
        avlTree.insert(0);
        avlTree.printAllPaths();

        //LR Case rotation
        avlTree.insert(5);
        avlTree.printAllPaths();

        //RR Case rotation
        avlTree.insert(14);
        avlTree.printAllPaths();

        //RL Case rotation
        avlTree.insert(9);
        avlTree.printAllPaths();


        /** Now the tree is
         *
         *                  7
         *            3           11
         *         1     5     9      13
         *       0  2  4  6  8  10  12  14
         */

        System.out.println(avlTree);
        avlTree.inOrderTraversalRec();

        avlTree.leftRotate(avlTree.search(7), null);
        avlTree.printAllPaths();
        System.out.println("avlTree.height(): " + avlTree.height());
        System.out.println("avlTree.heightBalanceFactor(): " + avlTree.heightBalanceFactor());

        avlTree.rightRotate(avlTree.search(11), null);
        avlTree.printAllPaths();
        System.out.println("avlTree.height(): " + avlTree.height());
        System.out.println("avlTree.heightBalanceFactor(): " + avlTree.heightBalanceFactor());

        System.out.println("avlTree.isBinarySearchTree(): " + avlTree.isBinarySearchTree());
        System.out.println("avlTree.isHeightCorrectForAllNodes(): " + avlTree.isHeightCorrectForAllNodes());
        System.out.println("avlTree.isAVLTree(): " + avlTree.isAVLTree());


        //Deletions in AVL Tree

        /** Now the tree is
         *
         *                  7
         *            3           11
         *         1     5     9      13
         *       0  2  4  6  8  10  12  14
         */
        avlTree.delete(12);
        avlTree.delete(14);

        // LL case rotation
        avlTree.delete(13);

        /** Now the tree is
         *
         *                  7
         *            3          9
         *         1     5    8    11
         *       0  2  4  6      10
         */

        //RL case rotation
        avlTree.delete(8);

        /** Now the tree is
         *
         *                  7
         *            3          10
         *         1     5     9    11
         *       0  2  4  6
         */

        //RR case rotation
        avlTree.delete(9);

        /** Now the tree is
         *
         *                  7
         *            3          10
         *         1     5         11
         *       0  2  4  6
         */

        avlTree.delete(4);
        avlTree.delete(6);
        avlTree.delete(0);


        /** Now the tree is
         *
         *                  7
         *            3          10
         *         1     5          11
         *           2
         */

        //LR case rotation
        avlTree.delete(5);

        /** Now the tree is
         *
         *                  7
         *            2          10
         *         1    3           11
         *
         */
        assert avlTree.isBinarySearchTree()
                && avlTree.isHeightCorrectForAllNodes()
                && avlTree.isAVLTree(): "AVL property failed";
    }

    /**
     *
     */
    private static void intenseInsertionDeletionTest(final int numberOfNodes){
        final AVLTree<Integer> avlTree = new AVLTree<>();

        for(int x=1; x<=numberOfNodes; x++) {
            avlTree.insert(x);
            assert avlTree.numberOfNodes()==x;
            assert avlTree.isBinarySearchTree()
                    && avlTree.isHeightCorrectForAllNodes()
                        && avlTree.isAVLTree(): "AVL property failed for value: " + x;
        }

        for(int x=1; x<=numberOfNodes; x++) {
            assert avlTree.search(x) != null: "search failed for: " + x;
        }

        for(int x=1; x<=numberOfNodes; x++) {
            avlTree.delete(x);
            assert avlTree.search(x) == null;

            assert avlTree.numberOfNodes()==numberOfNodes-x;

            assert avlTree.isBinarySearchTree()
                    && avlTree.isHeightCorrectForAllNodes()
                    && avlTree.isAVLTree() :  "AVL property failed for value: " + x;
        }
    }
}
