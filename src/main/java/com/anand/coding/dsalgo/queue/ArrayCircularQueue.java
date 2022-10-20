package com.anand.coding.dsalgo.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * for convenience of circular queue empty/full condition, one element is wasted.
 * So declare one extra element than the size.
 * @param <T>
 */
public class ArrayCircularQueue<T> implements Queue<T> {

    private T [] A;
    private int front=0, rear=0;
    private int capacity = 20;

    public ArrayCircularQueue(){
        A = (T[])new Object[capacity+1];
    }

    public ArrayCircularQueue(int capacity){
        this.capacity = capacity;
        A = (T[])new Object[capacity+1];
    }

    /**
     *
     * @param data
     * @return state
     */
    public T add(T data){
        if(isFull()){
            throw new IllegalStateException();
        }
        A[rear] = data;
        rear = (rear+1)%A.length;

        return data;
    }

    /**
     *
     * @return data
     */
    public T remove(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T data = A[front];
        A[front]=null;
        front = (front+1)%A.length;
        return data;
    }

    /**
     *
     * @return size
     */
    public int size(){
        if(front <= rear){
            return rear-front;
        } else {
            return A.length-(front-rear);
        }
    }

    /**
     *
     */
    public void display(){
        for(int i=front; i!=rear; i = (i+1)%A.length){
            System.out.print(A[i] + ", ");
        }
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return(rear==front);
    }

    /**
     *
     * @return
     */
    public boolean isFull(){
        return((rear+1)%A.length==front);
    }

    /**
     *
     * @return
     */
    public T peek(){
        return A[front];
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        Queue<Integer> queue = new ArrayCircularQueue<>(5);

        Arrays.stream(new int[]{1,2,3,4}).forEach(queue::add);
        queue.display();

        System.out.println(queue.remove());
        System.out.println(queue.peek());

        queue.add(5);
        queue.remove();
        queue.add(6);
        queue.display();
    }

}
