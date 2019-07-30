package com.anand.coding.dsalgo.tree.multivalue.map;

import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi-Value-Node BSTMap
 */
public class BSTMap<K extends Comparable<K>, T extends Comparable<T>> {

    protected Node<K,T> root;

    public BSTMap(){
        super();
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
        root.getDataList().forEach(data ->{
            System.out.print(data + "  ");
        });
        inOrderTraversalRec(root.getRight());
    }

    /**
     *
     * @param data
     * @return
     */
    public Node insert(K key, T data){

        Node<K,T> parent = null;
        Node<K,T> pivotNode = root;

        while(!(pivotNode == null || pivotNode.getKey().compareTo(key)==0)){
            parent = pivotNode;
            pivotNode = pivotNode.getKey().compareTo(key) > 0 ? pivotNode.getLeft() : pivotNode.getRight();
        }

        if(pivotNode!=null) {
            //Duplicate node accepted.
            pivotNode.setData(data);
            return pivotNode;
        }

        pivotNode = new Node<>(key, data);
        if(parent == null){
            this.root = pivotNode;
        } else if(parent.getKey().compareTo(key)>0){
            parent.setLeft(pivotNode);
        } else {
            parent.setRight(pivotNode);
        }
        return pivotNode;
    }

    /**
     *
     * @param key
     * @return
     */
    public Node<K,T> search(K key){

        Node<K,T> pivotNode=root;
        while(!(pivotNode == null || pivotNode.getKey().compareTo(key)==0)){
            pivotNode = pivotNode.getKey().compareTo(key) > 0 ? pivotNode.getLeft() : pivotNode.getRight();
        }
        return pivotNode;
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
     *
     * @return
     */
    @Override
    public String toString() {
        return "BSTMap{" +
                "root=" + root +
                '}';
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
        final BSTMap<String, Integer> bst = new BSTMap<>();

        for(int x : new int[]{ 5,7,3,6,4,8,2}) {
            bst.insert("key-" + x, x);
        }

        System.out.println(bst);

        bst.inOrderTraversalRec();
        System.out.println("bst.search(key-6): " + bst.search("key-6"));

        bst.insert("key-8", 16);
        bst.insert("key-8", 24);


        bst.inOrderTraversalRec();
        System.out.println("bst.search(key-8): " + bst.search("key-8"));

        System.out.println(bst.getSortedList());
    }
}
