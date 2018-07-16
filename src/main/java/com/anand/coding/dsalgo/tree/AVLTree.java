package com.anand.coding.dsalgo.tree;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

/**
 * AVLTree
 */
public class AVLTree extends BinarySearchTree {

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
        if(root == null){
            return 0;
        }
        return height(root.getLeft()) - height(root.getRight());
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
    public boolean isAVLTree(Node root){
        if(root == null){
            return true;
        }
        if(isAVLTree(root.getLeft()) && isAVLTree(root.getRight())){
            return Math.abs(heightBalanceFactor(root))<2;
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
    public boolean isHeightCorrectForAllNodes(Node root){
        if(root == null){
            return true;
        }
        if(isHeightCorrectForAllNodes(root.getLeft()) && isHeightCorrectForAllNodes(root.getRight())){
            return Math.max(height(root.getLeft()), height(root.getRight()))+1 == root.getHeight();
        }
        return false;
    }

    /**
     *
     * @param pivotNode
     * @param parent
     */
    private void leftRotate(Node pivotNode, Node parent){

        if(pivotNode == null || pivotNode.getRight() == null){
            return;
        }

        Node tmpNode = pivotNode.getRight();
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
    private void rightRotate(Node pivotNode, Node parent) {

        if (pivotNode == null || pivotNode.getLeft() == null) {
            return;
        }

        Node tmpNode = pivotNode.getLeft();
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
    public Node insert(int data){

        Stack<Node> stack = new ArrayStack();

        Node parent = null;
        Node pivotNode = root;

        // Simple BST insertion
        while(pivotNode != null && pivotNode.getData()!=data){
            stack.push(parent);
            parent = pivotNode;
            pivotNode = pivotNode.getData() > data ? pivotNode.getLeft() : pivotNode.getRight();
        }

        if(pivotNode!=null) {
            return null;
        }
        pivotNode = new Node(data);
        if(parent == null){
            root = pivotNode;
        } else if(parent.getData()>data){
            parent.setLeft(pivotNode);
        } else {
            parent.setRight(pivotNode);
        }

        // AVL Tree height recalculation and heightBalanceFactor rotation algorithm
        calculateHeight(parent);
        Node child = pivotNode;

        while(!(stack.isEmpty() || stack.peek()==null)){
            Node grandParent = stack.pop();
            calculateHeight(grandParent);

            final int heightBalanceFactor = heightBalanceFactor(grandParent);
            if(heightBalanceFactor>1) {
                //Insertion is in left subTree
                if (parent.getLeft() == child) {
                    //Left To Left Case
                    rightRotate(grandParent, stack.peek());
                } else {
                    //Left To Right Case
                    leftRotate(parent, grandParent);
                    rightRotate(grandParent, stack.peek());
                }
                break;
            } else if(heightBalanceFactor<-1){
                //Insertion is in right subTree
                if(parent.getRight()==child){
                    //Right To Right case
                    leftRotate(grandParent, stack.peek());
                } else {
                    //Right To Left case
                    rightRotate(parent, grandParent);
                    leftRotate(grandParent, stack.peek());
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
    public Node delete(int data){

        Stack<Node> stack = new ArrayStack();

        Node parent = null;
        Node pivotNode = root;

        //Find the pivotNode and its parent.
        while(pivotNode != null && pivotNode.getData() != data){
            stack.push(parent);
            parent = pivotNode;
            pivotNode = pivotNode.getData() > data ? pivotNode.getLeft() : pivotNode.getRight();
        }
        stack.push(parent);

        // if pivotNode not found, return
        if(pivotNode == null){
            return null;
        }

        /**
         * Deletion Algorithm:
         *  1. If right of pivotNode is null,
         *     1.1 Replace pivotNode with its left (may be null or a valid node)
         *
         *  2. Else if left of right of pivotNode is null, i.e; pivotNode.right is the inOrder successor
         *     2.1 left of pivotNode becomes left of right of pivotNode
         *     2.2 Replace pivotNode with right of pivotNode
         *
         *  3. Else Find the inOrder successor of pivotNode along with its parent.
         *     3.1 right of inOrderSuccessor becomes left of inOrderSuccessor's parent
         *     3.2 left and right of pivotNode becomes left and right of inOrderSuccessor respectively
         *     3.3 Replace pivotNode with inOrderSuccessor
         *
         */
        Queue<Node> queue = new ArrayCircularQueue<>();

        Node nodeToShift;
        if(pivotNode.getRight() == null) {
            nodeToShift = pivotNode.getLeft();

        } else if (pivotNode.getRight().getLeft() == null){
            nodeToShift = pivotNode.getRight();
            nodeToShift.setLeft(pivotNode.getLeft());

        } else {
            //Find inorder successor of pivotNode

            Node parentOfNodeToShift = pivotNode.getRight();
            nodeToShift = parentOfNodeToShift.getLeft();
            while(nodeToShift.getLeft()!= null){
                queue.insert(parentOfNodeToShift);
                parentOfNodeToShift = nodeToShift;
                nodeToShift = nodeToShift.getLeft();
            }
            queue.insert(parentOfNodeToShift);

            parentOfNodeToShift.setLeft(nodeToShift.getRight());

            nodeToShift.setLeft(pivotNode.getLeft());
            nodeToShift.setRight(pivotNode.getRight());
            nodeToShift.setHeight(pivotNode.getHeight());
        }

        if(parent == null){
            root = nodeToShift;
        } else if(parent.getLeft() == pivotNode){
            parent.setLeft(nodeToShift);
        } else {
            parent.setRight(nodeToShift);
        }

        // Add nodeToShift and all nodes in the queue to stack.
        if(nodeToShift != null) {
            stack.push(nodeToShift);
        }
        while(!queue.isEmpty()){
            stack.push(queue.delete());
        }

        // AVL Tree height recalculation and heightBalanceFactor rotation algorithm

        while(!(stack.isEmpty() || stack.peek()==null)){
            Node node = stack.pop();
            calculateHeight(node);

            final int heightBalanceFactor = heightBalanceFactor(node);
            //Check if this node is unbalanced, then four cases are there
            if(heightBalanceFactor>1) {
                //left subTree heavy
                if (heightBalanceFactor(node.getLeft())>=0) {
                    //Left To Left Case
                    rightRotate(node, stack.peek());
                } else {
                    //Left To Right Case
                    leftRotate(node.getLeft(), node);
                    rightRotate(node, stack.peek());
                }
            } else if(heightBalanceFactor<-1){
                //right subTree heavy
                if(heightBalanceFactor(node.getRight())<=0){
                    //Right To Right case
                    leftRotate(node, stack.peek());
                } else {
                    //Right To Left case
                    rightRotate(node.getRight(), node);
                    leftRotate(node, stack.peek());
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
        final AVLTree avlTree = new AVLTree();

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
        System.out.println("avlTree.isHeightCorrectForAllNodes(): " + avlTree.isAVLTree());


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
    public static void intenseInsertionDeletionTest(final int numberOfNodes){
        final AVLTree avlTree = new AVLTree();

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
