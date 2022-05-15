package com.anand.coding.dsalgo.heap;

import java.util.Objects;

/**
 * PriorityObject with priority field, implements Comparable
 */
public class PObject<T> implements Comparable<PObject<T>> {

    public T object;
    public Integer priority;

    public PObject(T object) {
        this.object = object;
        this.priority = Integer.MAX_VALUE;
    }

    public PObject(T object, Integer priority) {
        this.object = object;
        this.priority = priority;
    }

    @Override
    public int compareTo(PObject that) {
        return this.priority.compareTo(that.priority);
    }

    @Override
    public String toString(){
        return String.format("(%s, %s)", object.toString(), priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, priority);
    }
}
