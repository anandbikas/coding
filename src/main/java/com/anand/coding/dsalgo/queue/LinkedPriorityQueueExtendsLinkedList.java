package com.anand.coding.dsalgo.queue;

import com.anand.coding.dsalgo.list.LinkedList;

/**
 *
 * @param <T>
 */
public class LinkedPriorityQueueExtendsLinkedList<T> extends LinkedList<PriorityObject<T>> {

    /**
     * insert as lowest priority
     *
     * @param data
     */
    public void insert(T data){
        insert(data, Integer.MAX_VALUE);
    }

    /**
     * Insert sorted according to priority.
     *
     * @param data
     * @param priority
     */
    public void insert(T data, int priority){
        insertSorted(new PriorityObject<>(data, priority));
    }

    /**
     * Delete from start(front)
     *
     * @return
     */
    public T delete(){
        return deleteStart().getData().getObject();
    }

    /**
     *
     * @param queueIndex
     * @param priority
     */
    public void updatePriority(int queueIndex, int priority){
        insert(deleteAtIndex(queueIndex).getData().getObject(), priority);
    }

    /**
     *
     * @return
     */
    public boolean isFull(){
        return false;
    }


    /**
     *
     * @param args
     */
    public static void main(String args[]){

        LinkedPriorityQueueExtendsLinkedList<String> priorityQueue = new LinkedPriorityQueueExtendsLinkedList<>();

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
    }

}
