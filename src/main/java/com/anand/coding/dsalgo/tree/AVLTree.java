package com.anand.coding.dsalgo.tree;

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
        if(root==null){
            return 0;
        }
        return root.getHeight();
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
        } else if(parent.getData()>pivotNode.getData()){
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
        } else if (parent.getData() > pivotNode.getData()) {
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

        // Height recalculation and heightBalanceFactor rotation algorithm
        Node grandParent;
        calculateHeight(parent);
        Node child = pivotNode;

        while(!stack.isEmpty()){
            grandParent = stack.pop();
            calculateHeight(grandParent);

            if(Math.abs(heightBalanceFactor(grandParent))>1){

                if(grandParent.getLeft()==parent){
                    if(parent.getLeft()==child){
                        //Left To Left Case
                        rightRotate(grandParent, stack.pop());
                    } else {
                        //Left To Right Case
                        leftRotate(parent, grandParent);
                        rightRotate(grandParent, stack.pop());
                    }
                } else {
                    if(parent.getRight()==child){
                        //Right To Right case
                        leftRotate(grandParent, stack.pop());
                    } else {
                        //Right To Left case
                        rightRotate(parent, grandParent);
                        leftRotate(grandParent, stack.pop());
                    }
                }
                //Rotations completed, break.
                break;
            }
            child=parent;
            parent=grandParent;
        }

        return pivotNode;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){

        /**
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

        System.out.println(avlTree);
        avlTree.inOrderTraversalRec();

        avlTree.leftRotate(avlTree.search(7), null);
        avlTree.printAllPaths();
        System.out.println("avlTree.height(): " + avlTree.height());
        System.out.println("avlTree.heightBalanceFactor(5): " + avlTree.heightBalanceFactor(avlTree.searchRec(3)));

        avlTree.rightRotate(avlTree.search(11), null);
        avlTree.printAllPaths();

        System.out.println("avlTree.isBinarySearchTree(): " + avlTree.isBinarySearchTree());

        System.out.println("avlTree.lowestCommonAncestor(4, 8): " + avlTree.lowestCommonAncestor(4, 8));
        System.out.println("avlTree.lowestCommonAncestor(4, 11): " + avlTree.lowestCommonAncestor(4, 11));

        System.out.println("avlTree.delete(4): " + avlTree.delete(4));
        avlTree.inOrderTraversalRec();
        System.out.println("avlTree.search(4): " + avlTree.search(4));
    }
}
