package com.anand.coding.dsalgo.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @param <T>
 */
public class LinkedPriorityQueue<T> implements Queue<T> {

    private Node<T> front;
    private int size = 0;

    /**
     * insert as lowest priority
     *
     * @param data
     * @return
     */
    public Node<T> add(T data){
        return add(data, Integer.MAX_VALUE);
    }

    /**
     * Insert sorted according to priority.
     *
     * @param data
     * @param priority
     */
    public Node<T> add(T data, int priority){

        final Node<T> newNode = new Node<>(data, priority);

        if(front==null || front.priority>priority){
            newNode.next = front;
            front=newNode;
        } else {
            Node<T> node;
            for (node = front; node.next != null && node.next.priority <= priority; node = node.next);
            newNode.next = node.next;
            node.next = newNode;
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
     * @param index
     * @return
     */
    private Node<T> removeIndex(int index){

        Node<T> header = new Node<>(null);
        header.next = front;

        int i=1;
        for(Node<T> node = header; node.next!=null; node=node.next, i++){
            if(index==i){
                Node<T> deletedNode=node.next;
                node.next = deletedNode.next;
                deletedNode.next = null;
                size--;

                front = header.next;
                return deletedNode;
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
        Node<T> node = removeIndex(queueIndex);
        if(node!=null){
            add(node.data, priority);
        }
    }

    /**
     *
     */
    public void display(){
        for(Node<T> node=front; node!=null; node=node.next){
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
     * @return
     */
    public T peek(){
        return front==null ? null : front.data;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        LinkedPriorityQueue<String> queue = new LinkedPriorityQueue<>();

        queue.add("Obj1", 7);
        queue.add("Obj2", 1);
        queue.add("Obj3", 2);
        queue.add("Obj4", 4);
        queue.display();

        System.out.println(queue.remove());
        System.out.println(queue.peek());
        queue.display();

        queue.add("Obj5", 2);
        queue.display();
        queue.add("Obj6", 6);
        queue.add("Obj7");

        //Update priority
        queue.updatePriority(3, 0);
        queue.display();

        while(!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        queue.display();
    }

    public static class Node<T> implements Comparable<Node<T>> {

        public T data;
        public Node<T> next;
        public int priority;

        public Node(T data) {
            this(data,Integer.MIN_VALUE);
        }

        public Node(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }

        @Override public int compareTo(Node<T> that) {return Integer.compare(this.priority, that.priority);}
        @Override public String toString() {return String.format("(%s,%s)", data.toString(), priority);}
        @Override public int hashCode() {return Objects.hash(data, next, priority);}
    }
}
