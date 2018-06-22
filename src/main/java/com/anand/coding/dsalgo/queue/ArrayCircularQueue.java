package com.anand.coding.dsalgo.queue;

import java.util.EmptyStackException;

/**
 * Total elements inserted = size-1
 * for convenience of curcular queue empty/full condition
 * @param <T>
 */
public class ArrayCircularQueue<T> implements Queue {
    private Object [] queueArr;
    private int size=100;

    private int front = 0;
    private int rear = 0;

    /**
     *
     */
    public ArrayCircularQueue(){
        queueArr = new Object[size];
    }

    /**
     *
     * @param size
     */
    public ArrayCircularQueue(int size){
        this.size = size;
        queueArr = new Object[size];
    }

    /**
     *
     * @param data
     */
    public void insert(Object data){
        if(isFull()){
            throw new StackOverflowError();
        }
        queueArr[front] = data;
        front = (front+1)%size;
    }

    /**
     *
     * @return
     */
    public T delete(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T data = elementData(rear);
        rear = (rear+1)%size;
        return data;
    }

    /**
     *
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) queueArr[index];
    }

    /**
     *
     * @return
     */
    public int length(){
        if(rear <= front){
            return front-rear;
        } else {
            return size-(rear-front);
        }
    }

    /**
     *
     */
    public void display(){
        for(int i=rear; i!=front; i = (i+1)%size){
            System.out.print(queueArr[i]);
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
        return((front+1)%size==rear);
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        Queue queue = new ArrayCircularQueue(5);

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);

        System.out.println(queue.delete());

        queue.insert(5);
        queue.delete();
        queue.insert(6);
        queue.display();
    }

}
