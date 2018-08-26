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

    int length=0;

    /**
     * Insert at end (rear).
     *
     * @param data
     */
    public void insert(T data){

        final Node<T> newNode = new Node<>(data);
        if(front==null){
            front=rear=newNode;
        }
        rear.setNext(newNode);
        rear = newNode;
        length++;
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
        T data = front.getData();
        front = front.getNext();
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
     */
    public void display(){
        for(Node node=front; node!=null; node=node.getNext()){
            System.out.print(node.getData() + ", ");
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
