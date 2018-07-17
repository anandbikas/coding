package com.anand.coding.dsalgo.queue;

import com.anand.coding.dsalgo.exception.QueueEmptyException;
import com.anand.coding.dsalgo.exception.QueueFullException;

/**
 * Total elements inserted = size-1
 * for convenience of curcular queue empty/full condition
 * @param <T>
 */
public class ArrayCircularQueue<T> implements Queue<T> {

    private static final int DEFAULT_SIZE = 100;

    private T [] queueArr;

    private int front=0;
    private int rear=0;

    /**
     *
     */
    public ArrayCircularQueue(){
        this(DEFAULT_SIZE);
    }

    /**
     *
     * @param size
     */
    @SuppressWarnings("unchecked")
    public ArrayCircularQueue(int size){
        queueArr = (T[])new Object[size];
    }

    /**
     *
     * @param data
     */
    public void insert(T data){
        if(isFull()){
            throw new QueueFullException();
        }
        queueArr[front] = data;
        front = (front+1)%queueArr.length;
    }

    /**
     *
     * @return
     */
    public T delete(){
        if(isEmpty()){
            throw new QueueEmptyException();
        }
        T data = queueArr[rear];
        queueArr[rear]=null;
        rear = (rear+1)%queueArr.length;
        return data;
    }

    /**
     *
     * @return
     */
    public int length(){
        if(rear <= front){
            return front-rear;
        } else {
            return queueArr.length-(rear-front);
        }
    }

    /**
     *
     */
    public void display(){
        for(int i=rear; i!=front; i = (i+1)%queueArr.length){
            System.out.print(queueArr[i] + ", ");
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
        return((front+1)%queueArr.length==rear);
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        Queue<Integer> queue = new ArrayCircularQueue<>(5);

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
