package com.anand.coding.dsalgo.list;


/**
 * Node class
 */
public class Node<T> {

    private Object data;
    private Node next;

    /**
     *
     * @param data
     */
    public Node(Object data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public T getData() {
        return (T)data;
    }

    /**
     *
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data + " -> " + next;
    }
//    @Override
//    public String toString() {
//        return "Node{" +
//                "data=" + data +
//                ", next=" + next +
//                '}';
//    }
}
