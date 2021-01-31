package com.anand.coding.dsalgo.list;


import java.util.Objects;

/**
 * Generic Node Class
 * @param <T>
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        return o!=null
                && this.getClass() == o.getClass()
                && data.equals(((Node<?>) o).data);
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

    @Override
    public int hashCode() {
        return Objects.hash(data, next);
    }
}
