package com.anand.coding.dsalgo.queue;

/**
 * Queue Interface
 *
 * @param <T>
 */
public interface Queue<T> {

    public Object add(T data);
    public T remove();
    public boolean isEmpty();
    public boolean isFull();
    public void display();
    public int size();
    public T peek();
}
