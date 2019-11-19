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
     * @param node
     * @param data
     * @return
     */
    private Node<T> insertRec(Node<T> node, T data){

        if(node==null){
            node = new Node<>(data);
            return node;
        }

        if(node.getData().compareTo(data)>0) {
            node.setLeft(insertRec(node.getLeft(), data));
        } else if(node.getData().compareTo(data)<0){
            node.setRight(insertRec(node.getRight(), data));
        }
        return node;
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
    public boolean contains(T data){
        return search(data)!=null;
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
     * @param node
     * @param data
     * @return
     */
    private Node<T> searchRec(Node<T> node, T data){

        if(node==null || node.getData().compareTo(data)==0){
            return node;
        }
        return searchRec(node.getData().compareTo(data)>0 ? node.getLeft() : node.getRight(), data);
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
     * Floor value: largest value <= x
     *
     * @param x
     * @return
     */
    public T floor(T x){
        Node<T> node = root;
        while(node != null){
            if(node.getData().compareTo(x) > 0){
                node = node.getLeft();

            } else {
                if(node.getRight() == null
                        || node.getRight().getData().compareTo(x)>0){
                    break;
                } else {
                    node = node.getRight();
                }
            }
        }
        return node == null ? null : node.getData();
    }

    /**
     * Ceil value: smallest value >= x
     *
     * @param x
     * @return
     */
    public T ceil(T x){
        Node<T> node = root;
        while(node != null){
            if(node.getData().compareTo(x) < 0){
                node = node.getRight();

            } else {
                if(node.getLeft() == null
                        || node.getLeft().getData().compareTo(x)<0){
                    break;
                } else {
                    node = node.getLeft();
                }
            }
        }
        return node == null ? null : node.getData();
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

        Integer [] preOrder = {5, 3, 2, 4, 7, 6, 8};
        final BinarySearchTree<Integer> bst1 = new BinarySearchTree<Integer>(preOrder);
        System.out.println("bst.isCopy(bst1)" + bst.isCopy(bst1));


        System.out.println(bst.floor(7));
        System.out.println(bst.ceil(1));

        System.out.println(bst);
        bst.inOrderTraversalRec();
        System.out.println(bst.getSortedList());
        bst.printPath(6);
        bst.printAllPaths();

        System.out.println("bst.height(): " + bst.height());
        System.out.println("bst.search(8): " + bst.search(8));
        System.out.println("bst.containsData(8): " + bst.contains(8));

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

    /**
     * Construct a tree using preOrder
     *
     * @param preOrder
     */
    public BinarySearchTree(T [] preOrder){
        this.root = bstFromPreOrder(preOrder);
    }

    private Node<T> bstFromPreOrder(T [] preOrder) {

        if (preOrder.length == 0) {
            return null;
        }
        Node<T> rootNode = new Node<>(preOrder[0]);

        Stack<Node<T>> stack = new ArrayStack<>();
        stack.push(rootNode);

        for (int i = 1; i < preOrder.length; i++) {
            Node<T> newNode = new Node<>(preOrder[i]);

            if (preOrder[i].compareTo(stack.peek().getData()) < 0) {
                stack.peek().setLeft(newNode);
            } else {
                Node<T> node=stack.pop();
                for (;!stack.isEmpty() && preOrder[i].compareTo(stack.peek().getData()) > 0; node = stack.pop());
                node.setRight(newNode);
            }

            stack.push(newNode);
        }
        return rootNode;
    }
}
