package com.anand.coding.dsalgo.list;


import java.util.Objects;

/**
 * Generic Node Class
 * @param <T>
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private T data;
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

//    @Override
//    public String toString() {
//        return data + " -> " + next;
//    }
//    @Override
//    public String toString() {
//        return "Node{" +
//                "data=" + data +
//                ", next=" + next +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Node<?> node = (Node<?>) o;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, next);
    }
}
