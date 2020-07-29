package com.anand.coding.dsalgo.queue;

import com.anand.coding.dsalgo.exception.QueueEmptyException;
import com.anand.coding.dsalgo.list.Node;

/**
 *
 * @param <T>
 */
public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {

    private Node<T> front;
    private Node<T> rear;

    private int length=0;

    /**
     * Insert at end (rear).
     *
     * @param data
     */
    public Node<T> insert(T data){

        final Node<T> newNode = new Node<>(data);
        if(rear==null){
            front=rear=newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
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
        Node<T> deletedNode = front;
        front = front.next;
        if(front==null){
            rear = null;
        }
        length--;

        return deletedNode.data;
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
        Queue<Integer> queue = new LinkedQueue<>();

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
