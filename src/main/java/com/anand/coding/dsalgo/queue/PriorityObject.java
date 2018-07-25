package com.anand.coding.dsalgo.queue;

/**
 * PriorityObject with priority field, implements Comparable
 */
public class PriorityObject implements Comparable<PriorityObject>{

    String name;
    Integer priority;

    /**
     *
     */
    public PriorityObject() {
        super();
    }

    /**
     *
     * @param name
     * @param priority
     */
    public PriorityObject(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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

//    @Override
//    public String toString() {
//        return "PriorityObject{" +
//                "name='" + name + '\'' +
//                ", priority=" + priority +
//                '}';
//    }

    @Override
    public String toString(){
        return String.format("(%s, %s)", name, priority);
    }
}
