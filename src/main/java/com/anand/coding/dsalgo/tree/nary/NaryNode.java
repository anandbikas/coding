package com.anand.coding.dsalgo.tree.nary;

import java.util.Arrays;
import java.util.Objects;

/**
 * Nary Node with data and all children nodes.
 */
public class NaryNode <T extends Comparable<T>> implements Comparable<NaryNode<T>>{

    public T data;
    public NaryNode<T> [] child;

    public NaryNode(T data, int N){
        this.data = data;
        child = new NaryNode[N];
    }

    public boolean isLeafNode(){
        return Arrays.stream(child).filter(Objects::nonNull).findFirst().orElse(null) == null;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    @Override
    public int compareTo(NaryNode<T> node) {
        return data.compareTo(node.data);
    }
}
