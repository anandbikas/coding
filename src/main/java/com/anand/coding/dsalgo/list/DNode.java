package com.anand.coding.dsalgo.list;


/**
 * Generic Node Class
 * @param <T>
 */
public class DNode<T extends Comparable<T>> implements Comparable<DNode<T>>  {

    public T data;
    public DNode<T> prev;
    public DNode<T> next;

    public DNode(T data) {
        this.data = data;
    }

    /**
     *
     * @param that
     * @return
     */
    @Override
    public int compareTo(DNode<T> that) {
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
