package com.anand.coding.dsalgo.queue;

/**
 *
 * @param <T>
 */
public class LinkedPriorityQueue<T> implements Queue<T> {

    private PNode<T> front;
    private int length=0;

    /**
     * insert as lowest priority
     *
     * @param data
     */
    public PNode<T> insert(T data){
        return insert(data, Integer.MAX_VALUE);
    }

    /**
     * Insert sorted according to priority.
     *
     * @param data
     * @param priority
     */
    public PNode<T> insert(T data, int priority){

        final PNode<T> newNode = new PNode<>(data, priority);

        if(front==null || front.priority>priority){
            newNode.next = front;
            front=newNode;

        } else {
            PNode<T> node;
            for (node = front; node.next != null && node.next.priority <= priority; node = node.next) ;
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
            return null;
        }
        T data = front.data;
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

        PNode<T> header = new PNode<>(null);
        header.next = front;

        int i=1;
        for(PNode<T> node=header; node.next!=null; node=node.next, i++){
            if(index==i){
                PNode<T> deletedNode=node.next;
                node.next = deletedNode.next;
                front = header.next;
                length--;

                deletedNode.next = null;
                return deletedNode.data;
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
        for(PNode<T> node = front; node!=null; node=node.next){
            System.out.print(node + ", ");
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

        //Update priority
        priorityQueue.updatePriority(3, 0);

        priorityQueue.display();

        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.delete());
        }
    }

}
