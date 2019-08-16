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
public class ArrayPriorityQueue<T> extends BinaryMinHeap<PriorityObject<T>> implements Queue<T> {

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
     * insert as lowest priority
     *
     * @param data
     */
    public void insert(T data){
        insert(data, Integer.MAX_VALUE);
    }

    /**
     *
     * @param data
     * @param priority
     */
    public void insert(T data, int priority){
//        if(isFull()){
//            throw new QueueFullException();
//        }
        super.insert(new PriorityObject<>(data, priority));
    }

    /**
     *
     * @return
     */
    public T delete(){
        if(isEmpty()){
            throw new QueueEmptyException();
        }
        return super.extractMin().getObject();
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
     * @param queueIndex
     * @param priority
     */
    public void updatePriority(int queueIndex, int priority){
        PriorityObject<T> priorityObject = delete(queueIndex);
        priorityObject.setPriority(priority);
        insert(priorityObject);
    }


    /**
     *
     * @param args
     */
    public static void main(String args[]){
       
        ArrayPriorityQueue<String> priorityQueue = new ArrayPriorityQueue<>(10);

        priorityQueue.insert("Obj1", 7);
        priorityQueue.insert("Obj2", 1);
        priorityQueue.insert("Obj3", 2);
        priorityQueue.insert("Obj4", 4);

        priorityQueue.display();

        System.out.println(priorityQueue.delete());
        priorityQueue.display();

        priorityQueue.insert("Obj5", 2);
        priorityQueue.display();

        priorityQueue.insert("Obj6", 6);
        priorityQueue.insert("Obj7");
        priorityQueue.display();

        priorityQueue.updatePriority(3, 0);

        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.delete());
        }

        System.out.println("Capacity: " + priorityQueue.getCapacity());
    }
}
