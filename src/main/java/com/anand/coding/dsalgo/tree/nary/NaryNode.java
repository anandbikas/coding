package com.anand.coding.dsalgo.tree.nary;

import java.util.Arrays;
import java.util.Objects;

/**
 * Nary Node with data and all children nodes.
 */
public class NaryNode <T extends Comparable<T>> implements Comparable<NaryNode<T>>{

    public T data;
    public NaryNode<T> [] children;

    // height is used in AVLTree
    public int height;

    public NaryNode(int N){
        super();
        children = new NaryNode[N];

    }

    public NaryNode(T data, int N){
        this.data = data;
        children = new NaryNode[N];
        height=1;
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
        return data.compareTo(node.data);
    }
}
