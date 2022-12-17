package com.anand.coding.dsalgo.tree.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Binary Search Tree
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

        while(!(pivotNode == null || pivotNode.data.compareTo(data)==0)){
            parent = pivotNode;
            pivotNode = pivotNode.data.compareTo(data) > 0 ? pivotNode.left : pivotNode.right;
        }

        if(pivotNode!=null) {
            return null; //Duplicate data rejected.
        }

        pivotNode = new Node<>(data);
        if(parent == null){
            this.root = pivotNode;
        } else if(parent.data.compareTo(data)>0){
            parent.left = pivotNode;
        } else {
            parent.right = pivotNode;
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

        if(node.data.compareTo(data)>0) {
            node.left = insertRec(node.left, data);
        } else if(node.data.compareTo(data)<0){
            node.right = insertRec(node.right, data);
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
        while(!(pivotNode == null || pivotNode.data.compareTo(data)==0)){
            pivotNode = pivotNode.data.compareTo(data) > 0 ? pivotNode.left : pivotNode.right;
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
        if(node==null || node.data.equals(data)){
            return node;
        }
        return searchRec(node.data.compareTo(data)>0 ? node.left : node.right, data);
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
        while(!(pivotNode == null || pivotNode.data.compareTo(data)==0)){
            parent = pivotNode;
            pivotNode = pivotNode.data.compareTo(data) > 0 ? pivotNode.left : pivotNode.right;
        }

        // if pivotNode not found, return
        if(pivotNode == null){
            return null;
        }

        Node<T> successor;

        if(pivotNode.right == null) {
            //If pivot node has no inOrder successor, replace pivot with its left node.
            successor = pivotNode.left;

        } else {
            // Replace pivot with its inorder successor
            Node<T> parentOfSuccessor = pivotNode;
            successor = pivotNode.right;

            if(successor.left==null) {
                parentOfSuccessor.right = successor.right;
            }  else {
                for (; successor.left != null; parentOfSuccessor=successor, successor = successor.left) ;
                parentOfSuccessor.left = successor.right;
                successor.right = pivotNode.right;
            }
            successor.left = pivotNode.left;
        }

        if(parent == null){
            root = successor;
        } else if(parent.left == pivotNode){
            parent.left = successor;
        } else {
            parent.right = successor;
        }

        pivotNode.left = null;
        pivotNode.right = null;
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
        for(; node.left!=null; node=node.left);
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
        for(; node.right!=null; node=node.right);
        return node;
    }

    /**
     *
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(int low, int high) {
        return rangeSumBST((Node<Integer>)root, low, high);
    }

    /**
     * sum of numbers lying in the range low<=x<=max
     *
     * @param node
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(Node<Integer> node, int low, int high) {
        if (node == null) {
            return 0;
        }

        if (node.data.compareTo(low) < 0) {
            return rangeSumBST(node.right, low, high);
        } else if (node.data.compareTo(high) > 0) {
            return rangeSumBST(node.left, low, high);
        } else {
            return node.data + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
        }
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
            T t = data2; data2 = data1; data1 = t;
        }

        Node<T> node = root;
        while(node != null){
            if(node.data.compareTo(data1)<0){
                node = node.right;
            } else if(node.data.compareTo(data2)>0){
                node = node.left;
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

        if(node.data.equals(data)){
            for(Node<T> n = this.root; !n.data.equals(data);){
                System.out.print(n.data + "  ");
                n = n.data.compareTo(node.data) > 0 ? n.left : n.right;
            }
            System.out.print(data + "  ");
            System.out.println();
            return;
        }
        if(node.data.compareTo(data)>0){
            printPath(node.left, data);
        } else {
            printPath(node.right, data);
        }
    }

    /**
     *
     */
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
        if(node.left==null && node.right==null){
            for(Node<T> n = this.root; n!=null;){
                System.out.print(n.data + "  ");
                n = n.data.compareTo(node.data) > 0 ? n.left : n.right;
            }
            System.out.println();
            return;
        }
        printAllPaths(node.left);
        printAllPaths(node.right);
    }

    /**
     * InOrderTraversal gives sorted list.
     * @return
     */
    public List<T> getSortedList(){

        List<T> sortedList = new ArrayList<>();

        Stack<Node<T>> stack = new Stack<>();
        for(Node<T> node = root; node!= null; node=node.left){
            stack.push(node);
        }

        while(!stack.isEmpty()){
            Node<T> node = stack.pop();
            sortedList.add(node.data);

            for(node=node.right; node!= null; node = node.left){
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
            if(node.data.compareTo(x) > 0){
                node = node.left;
            } else {
                if(node.right == null || node.right.data.compareTo(x)>0){
                    return node.data;
                } else {
                    node = node.right;
                }
            }
        }
        return null;
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
            if(node.data.compareTo(x) < 0){
                node = node.right;
            } else {
                if(node.left == null || node.left.data.compareTo(x)<0){
                    return node.data;
                }
                node = node.left;
            }
        }
        return null;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

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
        final BinarySearchTree<Integer> bst1 = new BinarySearchTree<>(preOrder);
        System.out.println("isCopy(bst,bst1)" + BinarySearchTree.isCopy(bst,bst1));


        System.out.println("bst.floor(7) " + bst.floor(7));
        System.out.println("bst.ceil(1) " + bst.ceil(1));

        System.out.println(bst);
        bst.inOrderTraversalRec();
        System.out.println(bst.getSortedList());
        bst.printPath(6);
        bst.printAllPaths();

        System.out.println("bst.height(): " + bst.height());
        System.out.println("bst.search(8): " + bst.search(8));
        System.out.println("bst.containsData(8): " + bst.contains(8));

        System.out.println("bst.searchRec(8): " + bst.searchRec(8));

        System.out.println("bst.isBinarySearchTree(): " + bst.isBST());

        System.out.println("bst.rangeSumBST(4, 7): " + bst.rangeSumBST(4, 7));

        System.out.println("bst.lowestCommonAncestor(4, 8): " + bst.lowestCommonAncestor(4, 8));
        System.out.println("bst.lowestCommonAncestor(4, 11): " + bst.lowestCommonAncestor(4, 11));

        System.out.println("bst.min():" + bst.min());
        System.out.println("bst.max():" + bst.max());

        System.out.println("bst.delete(4): " + bst.delete(4));
        bst.inOrderTraversalRec();
        System.out.println("bst.search(4): " + bst.search(4));
        System.out.println("bst.delete(4): " + bst.delete(4));

        BSTIterator<Integer> bstIterator =  bst.iterator();
        System.out.println("bstIterator.hasNext(): " + bstIterator.hasNext());
        System.out.println("bstIterator.next(): " + bstIterator.next());
        System.out.println("bstIterator.next(): " + bstIterator.next());
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

        Stack<Node<T>> stack = new Stack<>();
        stack.push(rootNode);

        for (int i = 1; i < preOrder.length; i++) {
            Node<T> newNode = new Node<>(preOrder[i]);

            if (preOrder[i].compareTo(stack.peek().data) < 0) {
                stack.peek().left = newNode;
            } else {
                Node<T> node=stack.pop();
                for (;!stack.isEmpty() && preOrder[i].compareTo(stack.peek().data) > 0; node = stack.pop());
                node.right = newNode;
            }

            stack.push(newNode);
        }
        return rootNode;
    }

    /**
     * balanced bst from inorder (sorted list)
     *
     * @param inOrder
     * @return
     */
    public Node<T> bstFromInorder(T[] inOrder, int left, int right){

        if(left>right){
            return null;
        }

        int middle = left + (right-left) /2;
        Node<T> node = new Node<>(inOrder[middle]);

        node.left = bstFromInorder(inOrder, left, middle-1);
        node.right = bstFromInorder(inOrder, middle+1, right);

        return node;
    }

    public static class ListNode<T> {
        T val;
        ListNode<T> next;
        ListNode(T val, ListNode<T> next) { this.val = val; this.next = next; }
    }

    public Node<T> bstFromInorder(ListNode<T> head) {
        return bstFromInorder(head, null);
    }

    private Node<T> bstFromInorder(ListNode<T> left, ListNode<T> right){
        if(left==right) {
            return null;
        }

        ListNode<T> slow,fast;
        for(slow=fast=left ; fast!=right && fast.next!=right; slow=slow.next, fast=fast.next.next);
        Node<T> node = new Node<>(slow.val);
        node.left = bstFromInorder(left, slow);
        node.right= bstFromInorder(slow.next, right);

        return node;
    }

    /**
     * Inorder BST iterator
     */
    public static class BSTIterator<T extends Comparable<T>> {

        Stack<Node<T>> stack = new Stack<>();

        private BSTIterator(Node<T> root) {
            for(Node<T> node = root; node!= null; node=node.left){
                stack.push(node);
            }
        }

        public T next() {
            if(hasNext()){
                Node<T> node1 = stack.pop();

                for(Node<T> node=node1.right; node!= null; node = node.left){
                    stack.push(node);
                }
                return node1.data;
            }
            return null;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    /**
     *
     * @return
     */
    public BSTIterator<T> iterator(){
        return new BSTIterator<>(root);
    }
}
