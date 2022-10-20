package com.anand.coding.dsalgo.heap;

import com.anand.coding.dsalgo.queue.Queue;
import java.util.Objects;

/**
 * PriorityQueue implementation using BinaryMinHeap
 *
 * front is always at index 0;
 *
 * @param <T>
 */
public class PriorityQueue<T> extends BinaryMinHeap<PriorityQueue.Node<T>> implements Queue<T> {

    public PriorityQueue(){ super(); }
    public PriorityQueue(int size){ super(size); }

    public T add(T data){ return super.insert(new Node<>(data)).data; }
    public T add(T data, int priority){ return super.insert(new Node<>(data, priority)).data; }
    public T remove(){ return super.extract().data; }
    public int size(){ return getSize(); }
    public T peek(){ throw new UnsupportedOperationException();}

    /**
     *
     * @param queueIndex
     * @param priority
     */
    public void updatePriority(int queueIndex, int priority){

        Node<T> object = view(queueIndex);
        object.priority = priority;
        replace(queueIndex, object);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        PriorityQueue<String> queue = new PriorityQueue<>(10);

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

        System.out.println("Capacity: " + queue.getCapacity());
    }

    public static class Node<T> implements Comparable<Node<T>> {

        public T data;
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
        @Override public int hashCode() {return Objects.hash(data, priority);}
    }
}
