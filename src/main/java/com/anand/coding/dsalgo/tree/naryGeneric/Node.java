package com.anand.coding.dsalgo.tree.naryGeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * Nary Tree Node
 */
public class Node<T extends Comparable<T>>{

    public T data;
    public List<Node<T>> child;

    public Node(T data){
        this.data = data;
        child = new ArrayList<>();
    }

    public boolean isLeafNode(){
        return !child.isEmpty();
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
