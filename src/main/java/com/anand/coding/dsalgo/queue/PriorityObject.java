package com.anand.coding.dsalgo.queue;

/**
 * PriorityObject with priority field, implements Comparable
 */
public class PriorityObject<T> implements Comparable<PriorityObject<T>>{

    private T object;
    private Integer priority;

    /**
     *
     */
    public PriorityObject() {
        super();
    }

    /**
     *
     * @param object
     * @param priority
     */
    public PriorityObject(T object, int priority) {
        this.object = object;
        this.priority = priority;
    }

    /**
     *
     * @return
     */
    public T getObject() {
        return object;
    }

    /**
     *
     * @param object
     */
    public void setObject(T object) {
        this.object = object;
    }

    /**
     *
     * @return
     */
    public int getPriority() {
        return priority;
    }

    /**
     *
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     *
     * @param aPriorityObject
     * @return
     */
    @Override
    public int compareTo(PriorityObject aPriorityObject) {
        return this.priority.compareTo(aPriorityObject.priority);
    }

    @Override
    public String toString(){
        return String.format("(%s, %s)", object.toString(), priority);
    }
}
