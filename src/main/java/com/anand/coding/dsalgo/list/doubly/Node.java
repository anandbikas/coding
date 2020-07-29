package com.anand.coding.dsalgo.list.doubly;


/**
 * Generic Node Class
 * @param <T>
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>>  {

    public T data;
    public Node<T> prev;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    /**
     *
     * @param that
     * @return
     */
    @Override
    public int compareTo(Node<T> that) {
        return this.data.compareTo(that.data);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return data.toString();
    }
}
