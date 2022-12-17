package com.anand.coding.dsalgo.tree.binary;

/**
 * Binary Tree Node
 */
public class Node <T extends Comparable<T>>{

    public T data;
    public Node<T> left;
    public Node<T> right;
    public int height; // height is used in AVLTree

    public Node(){
        super();
    }

    public Node(T data){
        this.data = data;
        height=1;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
