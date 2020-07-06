package com.anand.coding.dsalgo.tree.binary;

/**
 * Binary Node with data and left and right child nodes.
 */
public class Node <T extends Comparable<T>> implements Comparable<Node<T>>{

    public T data;
    public Node<T> left;
    public Node<T> right;

    // height is used in AVLTree
    public int height;

    public Node(){
        super();
    }

    public Node(T data){
        this.data = data;
        height=1;
    }

//    @Override
//    public String toString() {
//        return "Node{" +
//                "data=" + data +
//                ", left=" + left +
//                ", right=" + right +
//                '}';
//    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    @Override
    public int compareTo(Node<T> node) {
        return this.data.compareTo(node.data);
    }
}
