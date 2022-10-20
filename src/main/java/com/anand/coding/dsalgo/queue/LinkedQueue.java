package com.anand.coding.dsalgo.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @param <T>
 */
public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {

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
        T data = front.data;
        front = front.next;
        if(front==null){
            rear = null;
        }
        size--;
        return data;
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
        Queue<Integer> queue = new LinkedQueue<>();

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
        public Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override public String toString() {return data.toString();}
    }
}
