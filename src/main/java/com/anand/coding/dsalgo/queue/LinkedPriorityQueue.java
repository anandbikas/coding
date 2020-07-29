package com.anand.coding.dsalgo.queue;

import com.anand.coding.dsalgo.exception.QueueEmptyException;
import com.anand.coding.dsalgo.list.Node;

/**
 *
 * @param <T>
 */
public class LinkedPriorityQueue<T> implements Queue<T> {

    private Node<PriorityObject<T>> front;
    private int length=0;

    /**
     * insert as lowest priority
     *
     * @param data
     */
    public Node<PriorityObject<T>> insert(T data){
        return insert(data, Integer.MAX_VALUE);
    }

    /**
     * Insert sorted according to priority.
     *
     * @param data
     * @param priority
     */
    public Node<PriorityObject<T>> insert(T data, int priority){

        final Node<PriorityObject<T>> newNode = new Node<>(new PriorityObject<>(data, priority));

        if(front==null || front.data.getPriority()>priority){
            newNode.next = front;
            front=newNode;

        } else {
            Node<PriorityObject<T>> node;
            for (node = front; node.next != null && node.next.data.getPriority() <= priority;
                        node = node.next) ;
            newNode.next = node.next;
            node.next = newNode;
        }

        length++;
        return newNode;
    }

    /**
     * Delete from start(front)
     *
     * @return
     */
    public T delete(){
        if(front==null){
            throw new QueueEmptyException();
        }
        T data = front.data.getObject();
        front = front.next;
        length--;
        return data;
    }

    /**
     *
     * @return
     */
    public int length(){
        return length;
    }

    /**
     *
     * @param index
     * @return
     */
    private T deleteAtIndex(final int index){

        if(front==null){
            return null;
        }

        if(index==1){
            Node<PriorityObject<T>> deletedNode = front;
            front=deletedNode.next;
            length--;

            deletedNode.next = null;
            return deletedNode.data.getObject();
        }

        int i;
        Node<PriorityObject<T>> node;
        for(node=front, i=2; node.next!=null; node=node.next, i++){
            if(index==i){
                Node<PriorityObject<T>> deletedNode=node.next;
                node.next = deletedNode.next;
                length--;
                deletedNode.next = null;
                return deletedNode.data.getObject();
            }
        }

        return null;
    }

    /**
     *
     * @param queueIndex
     * @param priority
     */
    public void updatePriority(int queueIndex, int priority){
        insert(deleteAtIndex(queueIndex), priority);
    }

    /**
     *
     */
    public void display(){
        for(Node node=front; node!=null; node=node.next){
            System.out.print(node.data + ", ");
        }
        System.out.println();
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return(front==null);
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

        LinkedPriorityQueue<String> priorityQueue = new LinkedPriorityQueue<>();

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
