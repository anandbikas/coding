package com.anand.coding.dsalgo.stack;

public interface Stack<T> {

    public void push(T data);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public boolean isFull();
}
