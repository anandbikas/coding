package com.anand.coding.dsalgo.list;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Generic Linked List implementation
 *  To reduce start==null/end==null check complexity, use a header node with header.next=start;
 *  and a tailer node with tailer.prev=end;
 * @param <T>
 */
public class DoublyLinkedList<T extends Comparable<T>>{

    private final Node<T> header = new Node<>(null);
    private final Node<T> tailer = new Node<>(null);
    private int size = 0;

    public DoublyLinkedList() {
        header.next=tailer;
        tailer.prev=header;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> addStart(T data){
        final Node<T> newNode = new Node<>(data);
        newNode.next = header.next;
        newNode.prev = header;
        newNode.next.prev = newNode.prev.next = newNode;
        size++;
        return newNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> add(T data){
        final Node<T> newNode = new Node<>(data);
        newNode.next = tailer;
        newNode.prev = tailer.prev;
        newNode.next.prev = newNode.prev.next = newNode;
        size++;
        return newNode;
    }

    /**
     *
     * @param index
     * @param data
     * @return
     */
    public Node<T> add(int index, T data){
        final Node<T> newNode = new Node<>(data);

        Node<T> node=header;
        for(int i=1; node!=tailer && i<index; node=node.next, i++);

        if(node!=tailer){
            newNode.next = node.next;
            newNode.prev = node;
            newNode.next.prev = newNode.prev.next = newNode;
            size++;
            return newNode;
        }

        throw new IndexOutOfBoundsException();
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> addSorted(T data){
        final Node<T> newNode = new Node<>(data);

        Node<T> node;
        for(node=header; node.next!=tailer && node.next.data.compareTo(newNode.data)<0; node=node.next);
        newNode.next = node.next;
        newNode.prev = node;
        newNode.next.prev = newNode.prev.next = newNode;
        size++;
        return newNode;
    }

    /**
     *
     * @return
     */
    public Node<T> removeFirst(){
        if(header.next==tailer){
            return null;
        }

        Node<T> deletedNode = header.next;
        header.next=deletedNode.next;
        header.next.prev=header;
        size--;

        deletedNode.next = deletedNode.prev = null;
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node<T> removeLast(){
        if(header.next==tailer){
            return null;
        }

        Node<T> deletedNode = tailer.prev;
        tailer.prev = deletedNode.prev;
        tailer.prev.next = tailer;
        size--;

        deletedNode.next = deletedNode.prev = null;
        return deletedNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> remove(T data){

        for(Node<T> node=header; node.next!=tailer; node=node.next){
            if(node.next.data.equals(data)){
                Node<T> deletedNode=node.next;
                node.next = deletedNode.next;
                node.next.prev = node;
                deletedNode.next = deletedNode.prev = null;
                size--;

                return deletedNode;
            }
        }

        return null;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> removeIndex(int index){
        if(index<1) {
            return null;
        }

        Node<T> node=header;
        for(int i=1; node.next!=tailer && i<index; node=node.next, i++);

        if(node.next!=tailer){
            Node<T> deletedNode=node.next;
            node.next = deletedNode.next;
            node.next.prev = node;
            deletedNode.next = deletedNode.prev = null;
            size--;

            return deletedNode;
        }

        return null;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> search(T data){

        Node<T> node;
        for(node=header.next; node!=null && !data.equals(node.data); node=node.next);

        return node;
    }

    /**
     *
     * @param data
     * @return
     */
    public int indexOf(T data){

        Node<T> node=header.next;
        int i=1;
        for(; node!=tailer && !node.data.equals(data); node=node.next, i++);

        return (node==tailer) ? -1 : i;
    }

    /**
     *
     * @param data
     * @return
     */
    public int indexOfFromEnd(T data){

        Node<T> node=tailer.prev;
        int i=1;
        for(; node!=null && !node.data.equals(data); node=node.prev, i++);

        return (node==null) ? -1 : i;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> node(int index){
        if(index<1) {
            return null;
        }

        Node<T> node=header.next;
        for(int i=1; node!=tailer && i<index; node=node.next, i++);

        return node==tailer ? null : node;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> nodeFromEnd(int index){

        Node<T> node=tailer.prev;
        for(int i=1; node!=header && i<index; node=node.prev, i++);

        return node==header ? null : node;
    }


    /**
     *
     */
    public void display(){
        System.out.println("Linked list: ");
        for(Node<T> node=header.next; node!=tailer; node=node.next){
            System.out.print(node.data + ", ");
        }
        System.out.println();
    }

    /**
     *
     */
    public void displayReverse(){
        System.out.println("Linked list (reversed): ");
        for(Node<T> node=tailer.prev; node!=header; node=node.prev){
            System.out.print(node.data + ", ");
        }
        System.out.println();
    }

    /**
     *
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Swap k and k+1 th nodes if present
     * @param k
     */
    public void swapAdjacent(final int k){
        Node<T> node=header;
        for(int i=1; node.next!=tailer && i<k; i++, node=node.next);

        if(node.next!=tailer && node.next.next!=tailer){
            Node<T> a = node.next;
            Node<T> b = a.next;
            a.next = b.next;
            b.next = a.next.prev = a;

            b.prev = node;
            a.prev = b.prev.next = b;
        }
    }

    /**
     * Sort in bubble fashion.
     *
     */
    public void bubbleSort() {

        for (int i=0; i < size; i++) {
            Node<T> a = header.next;

            for(int j=0; j < size-1-i; j++){
                Node<T> b = a.next;

                if(a.compareTo(b)>0){
                    Node<T> node = a.prev;
                    a.next = b.next;
                    b.next = a.next.prev = a;

                    b.prev = node;
                    a.prev = b.prev.next = b;
                } else {
                    a=b;
                }
            }
        }
    }

    /**
     *
     */
    public void reverse(){

        Node<T> node = header.next;

        while(node!=tailer){
            Node<T> temp = node.next;
            node.next = node.prev;
            node.prev = temp;
            node = temp;
        }

        Node<T> temp = header.next;
        header.next = tailer.prev;
        tailer.prev = temp;

        header.next.prev = header;
        tailer.prev.next = tailer;
    }

    /**
     *
     */
    public void resetList(){
        header.next=tailer.prev=null;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return(header.next==null);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        IntStream.rangeClosed(1,6).forEach(list::add);
        for(int i=5; i>0; i--){
            list.addStart(i);
        }

        list.add(6, 0);
        list.removeIndex(11);
        System.out.println(list.search(0));
        System.out.println(list.indexOf(0));

        list.display();
        list.bubbleSort();
        list.display();

        list.reverse();
        list.display();

        list.bubbleSort();
        list.display();

        list.swapAdjacent(1);
        list.display();

        list.removeLast();
        list.removeFirst();
        list.remove(5);
        list.display();

        list.displayReverse();
    }

    public static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

        public T data;
        public Node<T> prev, next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            return o!=null
                    && this.getClass() == o.getClass()
                    && data.equals(((Node<?>) o).data);
        }

        @Override public int compareTo(Node<T> that) { return this.data.compareTo(that.data);}
        @Override public String toString() { return data.toString(); }
        @Override public int hashCode() { return Objects.hash(data, next); }
    }
}
