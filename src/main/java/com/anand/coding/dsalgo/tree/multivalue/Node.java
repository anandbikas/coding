package com.anand.coding.dsalgo.tree.multivalue;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Node with multi-value data and left and right child nodes.
 * @param <T>
 */
public class Node<T extends Comparable> implements Comparable<Node<T>>{


    private List<T> dataList = new ArrayList<>();
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
        this.dataList.add(data);
        height=1;
    }

    /**
     *
     * @return
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * getData returns first element used for comparison purpose
     *
     *  -> Comparing with any element is sufficient as the key will be same for all.
     *
     * @return
     */
    public T getData() {
        return dataList.get(0);
    }

    /**
     *
     * @param data
     */
    public void setData(T data) {
        this.dataList.add(data);
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
    @Override
    public String toString() {
        return String.valueOf(dataList);
    }

    /**
     *
     * @param multiValueNode
     * @return
     */
    @Override
    public int compareTo(Node<T> multiValueNode) {
        if(this.dataList.size()==0 && multiValueNode.dataList.size()==0){
            return 0;
        }
        return getData().compareTo(multiValueNode.getData());
    }
}
