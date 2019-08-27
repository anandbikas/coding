package com.anand.coding.dsalgo.tree.multivalue;

import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi-Value-Node Binary Search Tree
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
            //Duplicate node accepted.
            pivotNode.setData(data);
            return pivotNode;
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

        return pivotNode!=null
                && pivotNode.getDataList().stream().filter(data1 -> data1.equals(data))
                    .findFirst().orElse(null) != null;
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
            node.getDataList().forEach(data -> {
                sortedList.add((T)data);
            });

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
            bst.insert(x);
        }

        System.out.println(bst);

        bst.inOrderTraversalRec();
        System.out.println("bst.search(8): " + bst.search(8));

        bst.insert(8);

        bst.inOrderTraversalRec();
        System.out.println("bst.search(8): " + bst.search(8));

        System.out.println(bst.getSortedList());
        System.out.println(bst.containsData(8));
        System.out.println(bst.containsData(25));

        System.out.println((bst.floor(7)));

        System.out.println((bst.ceil(1)));

    }
}
