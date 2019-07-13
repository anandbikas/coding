package com.anand.coding.dsalgo.tree;

/**
 * Binary Node with data and left and right child nodes.
 */
public class Node <T extends Comparable<T>> implements Comparable<Node<T>>{

    private T data;
    private Node<T> left;
    private Node<T> right;

    // height is used in AVLTree
    private int height;

    /**
     *
     */
    public Node(){
        super();
    }

    /**
     *
     * @param data
     */
    public Node(T data){
        this.data = data;
        height=1;
    }

    /**
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     *
     * @return
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
//    @Override
//    public String toString() {
//        return "Node{" +
//                "data=" + data +
//                ", left=" + left +
//                ", right=" + right +
//                '}';
//    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(data);
    }

    /**
     *
     * @param node
     * @return
     */
    @Override
    public int compareTo(Node<T> node) {
        return this.data.compareTo(node.data);
    }
}
