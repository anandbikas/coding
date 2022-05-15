package com.anand.coding.dsalgo.queue;


import java.util.Objects;

/**
 * Generic Node Class
 * @param <T>
 */
public class PNode<T> implements Comparable<PNode<T>> {

    public T data;
    public PNode<T> next;
    public int priority;

    public PNode(T data) {
        this.data = data;
        this.priority = Integer.MAX_VALUE;
    }

    public PNode(T data, int priority) {
        this.data = data;
        this.priority = priority;
    }

    @Override
    public int compareTo(PNode<T> that) {
        return Integer.compare(this.priority, that.priority);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", data.toString(), priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, next, priority);
    }
}
