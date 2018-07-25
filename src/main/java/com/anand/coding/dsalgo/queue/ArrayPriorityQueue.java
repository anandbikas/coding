package com.anand.coding.dsalgo.queue;

import com.anand.coding.dsalgo.exception.QueueEmptyException;
import com.anand.coding.dsalgo.exception.QueueFullException;
import com.anand.coding.dsalgo.tree.arraybased.BinaryMinHeap;

/**
 * PriorityQueue implementation using BinaryMinHeap
 *
 * front is always at index 0;
 *
 * @param <T>
 */
public class ArrayPriorityQueue<T extends Comparable<T>> extends BinaryMinHeap<T> implements Queue<T> {

    /**
     *
     */
    public ArrayPriorityQueue(){
        super();
    }

    /**
     *
     * @param size
     */
    public ArrayPriorityQueue(int size){
        super(size);
    }

    /**
     *
     * @param data
     */
    public void insert(T data){
        if(isFull()){
            throw new QueueFullException();
        }
        super.insert(data);
    }

    /**
     *
     * @return
     */
    public T delete(){
        if(isEmpty()){
            throw new QueueEmptyException();
        }
        return super.extractMin();
    }

    /**
     *
     * @return
     */
    public int length(){
        return getSize();
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
       
        ArrayPriorityQueue<PriorityObject> priorityQueue = new ArrayPriorityQueue<>(10);

        priorityQueue.insert(new PriorityObject("Obj1", 7));
        priorityQueue.insert(new PriorityObject("Obj2", 1));
        priorityQueue.insert(new PriorityObject("Obj3", 2));
        priorityQueue.insert(new PriorityObject("Obj4", 4));

        priorityQueue.display();

        System.out.println(priorityQueue.delete());
        priorityQueue.display();

        priorityQueue.insert(new PriorityObject("Obj5", 2));
        priorityQueue.display();

        priorityQueue.insert(new PriorityObject("Obj6", 6));
        priorityQueue.display();

        PriorityObject priorityObject = priorityQueue.delete(3);
        priorityObject.setPriority(0);
        priorityQueue.insert(priorityObject);

        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.delete());
        }
    }
}
