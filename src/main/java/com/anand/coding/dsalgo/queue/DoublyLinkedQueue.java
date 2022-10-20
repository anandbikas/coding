package com.anand.coding.dsalgo.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @param <T>
 */
public class DoublyLinkedQueue<T> implements Queue<T> {

    private Node<T> front, rear;
    private int size = 0;

    /**
     *
     * @param data
     * @return
     */
    public Node<T> add(T data){
        final Node<T> newNode = new Node<>(data);
        if(rear==null){
            front=rear=newNode;
        } else {
            newNode.prev=rear;
            rear=rear.next=newNode;
        }
        size++;
        return newNode;
    }

    /**
     *
     * @return
     */
    public T remove(){
        if(front==null){
            throw new NoSuchElementException();
        }
        Node<T> deletedNode = front;
        front = deletedNode.next;
        if(front==null){
            rear = null;
        } else {
            front.prev = null;
        }
        size--;
        return deletedNode.data;
    }

    /**
     *
     * @return
     */
    public void moveToRear(Node<T> node){

        if(node==rear){
            return;
        }

        if(node==front){
            front=node.next;
            front.prev = null;
        } else {
            node.prev.next=node.next;
            node.next.prev = node.prev;
        }

        node.next = null;
        node.prev = rear;
        rear = rear.next = node;
    }

    /**
     *
     * @param node
     * @return
     */
    public Node<T> delete(Node<T> node){
        if(front==rear){
            front=rear=null;
        } else if(node == rear){
            rear=node.prev;
            rear.next=null;
        } else if(node==front){
            front=node.next;
            front.prev = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.next = node.prev =null;
        size--;
        return node;
    }

    /**
     *
     * @return
     */
    public int size(){
       return size;
    }


    /**
     *
     */
    public void display(){
        for(Node<T> node=front; node!=null; node=node.next){
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
     * @return
     */
    public T peek(){
        return front==null ? null : front.data;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]){
        Queue<Integer> queue = new DoublyLinkedQueue<>();

        Arrays.stream(new int[]{1,2,3,4}).forEach(queue::add);
        queue.display();

        System.out.println(queue.remove());
        System.out.println(queue.peek());

        queue.add(5);
        queue.remove();
        queue.add(6);
        queue.display();
    }

    public static class Node<T> {
        public T data;
        public Node<T> prev, next;

        public Node(T data) {
            this.data = data;
        }

        @Override public String toString() { return data.toString();}
    }
}
