package com.anand.coding.dsalgo.queue;

import com.anand.coding.dsalgo.exception.QueueEmptyException;
import com.anand.coding.dsalgo.list.doubly.Node;

/**
 *
 * @param <T>
 */
public class DoublyLinkedQueue<T extends Comparable<T>> implements Queue<T> {

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
            rear.setNext(newNode);
            newNode.setPrev(rear);
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
        front = front.getNext();
        if(front==null){
            rear = null;
        } else {
            front.setPrev(null);
        }
        length--;

        return deletedNode.getData();
    }

    /**
     * Move to rear
     *
     * @return
     */
    public void moveToRear(Node<T> node){

        if(node == rear){
            return;
        }

        if(node==front){
            front=node.getNext();
            front.setPrev(null);
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

        node.setNext(null);
        node.setPrev(rear);
        rear.setNext(node);
        rear = node;
    }

    /**
     * Move to front
     *
     * @return
     */
    public void moveToFront(Node<T> node){

        if(node == front){
            return;
        }

        if(node==rear){
            rear=node.getPrev();
            rear.setNext(null);
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

        node.setPrev(null);
        node.setNext(front);
        front.setPrev(node);
        front = node;
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
        Queue<Integer> queue = new DoublyLinkedQueue<>();

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
