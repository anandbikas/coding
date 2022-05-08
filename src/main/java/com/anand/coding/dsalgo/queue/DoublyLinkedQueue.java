package com.anand.coding.dsalgo.queue;

/**
 *
 * @param <T>
 */
public class DoublyLinkedQueue<T> implements Queue<T> {

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
            newNode.prev = rear;
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
            return null;
        }
        Node<T> deletedNode = front;
        front = front.next;
        if(front==null){
            rear = null;
        } else {
            front.prev = null;
        }
        length--;

        return deletedNode.data;
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
            front=node.next;
            front.prev = null;
        } else {
            node.prev.next=node.next;
            node.next.prev = node.prev;
        }

        node.next = null;
        node.prev = rear;
        rear.next = node;
        rear = node;
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

    public static class Node<T> {
        public T data;
        public Node<T> prev, next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}
