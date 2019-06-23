package com.anand.coding.dsalgo.tree;

import java.util.Arrays;
import java.util.Objects;

/**
 * Nary Node with data and all children nodes.
 */
public class NaryNode <T extends Comparable<T>> implements Comparable<NaryNode<T>>{

    private T data;
    private NaryNode<T> [] children;

    // height is used in AVLTree
    private int height;

    /**
     *
     * @param N
     */
    public NaryNode(int N){
        super();
        children = new NaryNode[N];

    }

    /**
     *
     * @param data
     * @param N
     */
    public NaryNode(T data, int N){
        this.data = data;
        children = new NaryNode[N];
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
     * @param i
     * @return
     */
    public NaryNode<T> getChild(int i) {
        return children[i];
    }

    /**
     * @param i
     * @param child
     */
    public void setChild(int i, NaryNode<T> child) {
        children[i] = child;
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
    public boolean isLeafNode(){
        return Arrays.stream(children)
                .filter(Objects::nonNull).findFirst().orElse(null) == null;

        //Lambda Expression: (x ->  x !=null)
        //Method Reference: (Objects::nonNull)
    }

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
    public int compareTo(NaryNode<T> node) {
        return node.getData().compareTo(node.data);
    }
}
