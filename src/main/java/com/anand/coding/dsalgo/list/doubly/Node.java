package com.anand.coding.dsalgo.list.doubly;


/**
 * Generic Node Class
 * @param <T>
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>>  {

    private T data;
    private Node<T> prev;
    private Node<T> next;

    /**
     *
     * @param data
     */
    public Node(T data) {
        this.data = data;
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
     * @return
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     *
     * @return
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     *
     * @param prev
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
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
