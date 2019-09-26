package com.anand.coding.dsalgo.tree;

import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Seach Tree
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree(){
        super();
    }

    /**
     *
     * @param data
     * @return
     */
    public Node insert(T data){

        Node<T> parent = null;
        Node<T> pivotNode = root;

        while(!(pivotNode == null || pivotNode.getData().compareTo(data)==0)){
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
        return pivotNode;
    }

    /**
     *
     * @param data
     */
    public void insertRec(T data){
        this.root = insertRec(this.root, data);
    }

    /**
     *
     * @param root
     * @param data
     * @return
     */
    private Node<T> insertRec(Node<T> root, T data){

        if(root==null){
            root = new Node<>(data);
            return root;
        }

        if(root.getData().compareTo(data)>0) {
            root.setLeft(insertRec(root.getLeft(), data));
        } else if(root.getData().compareTo(data)<0){
            root.setRight(insertRec(root.getRight(), data));
        }
        return root;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> search(T data){

        Node<T> pivotNode=root;
        while(!(pivotNode == null || pivotNode.getData().compareTo(data)==0)){
            pivotNode = pivotNode.getData().compareTo(data) > 0 ? pivotNode.getLeft() : pivotNode.getRight();
        }
        return pivotNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public boolean containsData(T data){

        Node<T> pivotNode=root;
        while(!(pivotNode == null || pivotNode.getData().compareTo(data)==0)){
            pivotNode = pivotNode.getData().compareTo(data) > 0 ? pivotNode.getLeft() : pivotNode.getRight();
        }

        return pivotNode!=null && pivotNode.getData().equals(data);
    }

    /**
     *
     * @param data
     * @return
     */
    @Override
    public Node<T> searchRec(T data){
        return searchRec(root, data);
    }

    /**
     *
     * @param root
     * @param data
     * @return
     */
    private Node<T> searchRec(Node<T> root, T data){

        if(root==null || root.getData().compareTo(data)==0){
            return root;
        }
        return searchRec(root.getData().compareTo(data)>0 ? root.getLeft() : root.getRight(), data);
    }

    /**
     * Delete the pivotNode matching the data
     *
     * @param data
     * @return
     */
    public Node<T> delete(T data){

        Node<T> parent = null;
        Node<T> pivotNode = root;

        //Find the pivotNode and its parent.
        while(!(pivotNode == null || pivotNode.getData().compareTo(data)==0)){
            parent = pivotNode;
            pivotNode = pivotNode.getData().compareTo(data) > 0 ? pivotNode.getLeft() : pivotNode.getRight();
        }

        // if pivotNode not found, return
        if(pivotNode == null){
            return null;
        }

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
                parentOfNodeToShift = nodeToShift;
                nodeToShift = nodeToShift.getLeft();
            }

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

        pivotNode.setLeft(null);
        pivotNode.setRight(null);
        return pivotNode;
    }

    /**
     * Find the minimum value node
     */
    public Node<T> min(){
        if(root==null){
            return null;
        }
        Node<T> node=root;
        for(; node.getLeft()!=null; node=node.getLeft());
        return node;
    }

    /**
     * Find the maximum value node
     * @return
     */
    public Node<T> max(){
        if(root==null){
            return null;
        }
        Node<T> node=root;
        for(; node.getRight()!=null; node=node.getRight());
        return node;
    }

    /**
     * Find the lowest common ancestor node of the two given nodes
     *
     * @return
     */
    public Node<T> lowestCommonAncestor(T data1, T data2){
        if(data1.compareTo(data2)==0){
            return null;
        }
        if(data1.compareTo(data2)>0) {
            T temp = data2;
            data2 = data1;
            data1 = temp;
        }

        Node<T> node = root;
        while(node != null){
            if(node.getData().compareTo(data1)<0){
                node = node.getRight();
            } else if(node.getData().compareTo(data2)>0){
                node = node.getLeft();
            } else {
                break;
            }
        }
        if(searchRec(node, data1)==null || searchRec(node, data2)==null){
            return null;
        }
        return node;
    }

    /**
     *
     * @param data
     */
    public void printPath(T data) {
        System.out.println("printPath");

        printPath(root, data);
        System.out.println();
    }

    /**
     *
     * @param node
     * @param data
     */
    private void printPath(final Node<T> node, T data){
        if(node == null){
            return;
        }

        if(node.getData().equals(data)){
            for(Node<T> n = this.root; !n.getData().equals(data);){
                System.out.print(n.getData() + "  ");
                n = n.compareTo(node) > 0 ? n.getLeft() : n.getRight();
            }
            System.out.print(data + "  ");
            System.out.println();
            return;
        }
        if(node.getData().compareTo(data)>0){
            printPath(node.getLeft(), data);
        } else {
            printPath(node.getRight(), data);
        }
    }

    /**
     *
     */
    @Override
    public void printAllPaths() {
        System.out.println("printAllPaths");
        printAllPaths(root);
        System.out.println();
    }

    /**
     * Print all the paths of the tree.
     * Use binary search tree feature to print the path by traversing from root to child
     *
     * @param node
     */
    private void printAllPaths(final Node<T> node){
        if(node == null){
            return;
        }
        if(node.getLeft()==null && node.getRight()==null){
            for(Node<T> n = this.root; n!=null;){
                System.out.print(n.getData() + "  ");
                n = n.compareTo(node) > 0 ? n.getLeft() : n.getRight();
            }
            System.out.println();
            return;
        }
        printAllPaths(node.getLeft());
        printAllPaths(node.getRight());
    }

    /**
     * InOrderTraversal gives sorted list.
     * @return
     */
    public List<T> getSortedList(){

        List<T> sortedList = new ArrayList<>();

        Stack<Node> stack = new ArrayStack<>();

        for(Node node = root; node!= null; node=node.getLeft()){
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node node = stack.pop();
            sortedList.add((T)node.getData());

            for(node=node.getRight(); node!= null; node = node.getLeft()){
                stack.push(node);
            }
        }

        return sortedList;
    }

    /**
     * Main function to test the code.
     *
     * @param args
     */
    public static void main(String args[]){

        /*
         *               5
         *           3      7
         *        2   4  6   8
         */
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for(int x : new int[]{ 5,7,3,6,4,8,2}) {
            bst.insertRec(x);
        }

        System.out.println(bst);
        bst.inOrderTraversalRec();
        System.out.println(bst.getSortedList());
        bst.printPath(6);
        bst.printAllPaths();

        System.out.println("bst.height(): " + bst.height());
        System.out.println("bst.search(8): " + bst.search(8));
        System.out.println("bst.containsData(8): " + bst.containsData(8));

        System.out.println("bst.searchRec(8): " + bst.searchRec(8));

        System.out.println("bst.isBinarySearchTree(): " + bst.isBinarySearchTree());

        System.out.println("bst.lowestCommonAncestor(4, 8): " + bst.lowestCommonAncestor(4, 8));
        System.out.println("bst.lowestCommonAncestor(4, 11): " + bst.lowestCommonAncestor(4, 11));

        System.out.println("bst.min():" + bst.min());
        System.out.println("bst.max():" + bst.max());

        System.out.println("bst.delete(4): " + bst.delete(4));
        bst.inOrderTraversalRec();
        System.out.println("bst.search(4): " + bst.search(4));
        System.out.println("bst.delete(4): " + bst.delete(4));
    }
}
