package com.anand.coding.dsalgo.tree.multivalue.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Node with  multi-value data along with key and left and right child nodes.
 * @param <K>
 * @param <T>
 */
public class Node<K extends Comparable<K>, T extends Comparable<T>> implements Comparable<Node<K,T>>{

    private K key;
    private List<T> dataList = new ArrayList<>();
    private Node<K,T> left;
    private Node<K,T> right;

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
     * @param key
     * @param data
     */
    public Node(K key, T data){
        this.key = key;
        this.dataList.add(data);
        height=1;
    }

    /**
     *
     * @return
     */
    public K getKey() {
        return key;
    }

    /**
     *
     * @param key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     *
     * @return
     */
    public List<T> getDataList() {
        return dataList;
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
    public Node<K,T> getLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(Node<K,T> left) {
        this.left = left;
    }

    /**
     *
     * @return
     */
    public Node<K,T> getRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(Node<K,T> right) {
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
        return key + " -> " + String.valueOf(dataList);
    }

    /**
     *
     * @param multiValueNode
     * @return
     */
    @Override
    public int compareTo(Node<K,T> multiValueNode) {
        return key.compareTo(multiValueNode.key);
    }
}
