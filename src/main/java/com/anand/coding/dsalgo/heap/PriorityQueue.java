package com.anand.coding.dsalgo.heap;

import com.anand.coding.dsalgo.queue.Queue;

/**
 * PriorityQueue implementation using BinaryMinHeap
 *
 * front is always at index 0;
 *
 * @param <T>
 */
public class PriorityQueue<T> extends BinaryMinHeap<PObject<T>> implements Queue<T> {

    public PriorityQueue(){ super(); }
    public PriorityQueue(int size){ super(size); }

    public T insert(T data){ return super.insert(new PObject<>(data)).object; }
    public T insert(T data, int priority){ return super.insert(new PObject<>(data, priority)).object; }
    public T delete(){ return super.extract().object; }
    public int length(){ return getSize(); }

    /**
     *
     * @param queueIndex
     * @param priority
     */
    public void updatePriority(int queueIndex, int priority){

        PObject<T> object = view(queueIndex);
        object.priority = priority;
        replace(queueIndex, object);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
       
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(10);

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
        priorityQueue.insert("Obj7", Integer.MAX_VALUE);
        priorityQueue.display();

        //Update priority
        priorityQueue.updatePriority(3, 0);

        priorityQueue.display();

        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.delete());
        }

        System.out.println("Capacity: " + priorityQueue.getCapacity());
    }
}
