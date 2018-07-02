package com.anand.coding.dsalgo.tree;

/**
 * Binary Node with data and left and right child nodes.
 */
public class Node {

    private int data;
    private Node left;
    private Node right;

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
    public Node(int data){
        this.data = data;
        height=1;
    }

    /**
     *
     * @return
     */
    public int getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public Node getLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     *
     * @return
     */
    public Node getRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(Node right) {
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
}
