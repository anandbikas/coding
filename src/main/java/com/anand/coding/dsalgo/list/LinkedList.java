package com.anand.coding.dsalgo.list;

import java.util.Objects;

/**
 * Linked List implementation
 */
public class LinkedList<T> {

    private Node start;

    /**
     *
     * @param data
     * @return
     */
    public Node insertStart(Object data){

        final Node newNode = new Node(data);
        newNode.setNext(start);
        start = newNode;

        return newNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node insertEnd(Object data){

        final Node newNode = new Node(data);
        if(start==null){
            start=newNode;
            return newNode;
        }

        Node node;
        for(node=start; node.getNext()!=null; node=node.getNext());
        node.setNext(newNode);

        return newNode;
    }

    /**
     *
     * @param index
     * @param data
     * @return
     */
    public Node insertAtIndex(int index, Object data){

        final Node newNode = new Node(data);
        if(index==1){
            newNode.setNext(start);
            start=newNode;
            return newNode;
        }

        int i;
        Node node;
        for(node=start, i=2; node!=null; node=node.getNext(), i++){
            if(index==i){
                newNode.setNext(node.getNext());
                node.setNext(newNode);
                return newNode;
            }
        }

        return null;
    }

    /**
     *
     * @return
     */
    public Node deleteStart(){

        if(start==null){
            return null;
        }

        Node deletedNode = start;
        start=deletedNode.getNext();
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node deleteEnd(){

        if(start==null){
            return null;
        }

        if(start.getNext()==null){
            Node deletedNode = start;
            start=deletedNode.getNext();
            return deletedNode;
        }

        Node node;
        for(node=start; node.getNext().getNext()!=null; node=node.getNext());

        Node deletedNode=node.getNext();
        node.setNext(null);
        return deletedNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node delete(Object data){

        if(start==null){
            return null;
        }

        if(start.getData()==data){
            Node deletedNode = start;
            start=deletedNode.getNext();
            return deletedNode;
        }

        for(Node node=start; node.getNext()!=null; node=node.getNext()){
            if(node.getNext().getData()==data){
                Node deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());
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
    public Node deleteAtIndex(final int index){

        if(start==null){
            return null;
        }

        if(index==1){
            Node deletedNode = start;
            start=deletedNode.getNext();
            return deletedNode;
        }

        int i;
        Node node;
        for(node=start, i=2; node.getNext()!=null; node=node.getNext(), i++){
            if(index==i){
                Node deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());
                return deletedNode;
            }
        }

        return null;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node search(Object data){

        Node node;
        for(node=start; node!=null && node.getData()!=data; node=node.getNext());

        return node;
    }

    /**
     *
     */
    public void display(){
        System.out.println("Linked list: ");
        for(Node node=start; node!=null; node=node.getNext()){
            System.out.print(node.getData() + ", ");
        }
        System.out.println();
    }

    /**
     *
     */
    public void resetList(){
        start=null;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        final LinkedList<Integer> list = new LinkedList();

        for(int i=6; i<=10; i++){
            list.insertEnd(i);
        }
        for(int i=5; i>0; i--){
            list.insertStart(i);
        }
        list.insertAtIndex(6, 0);
        list.deleteAtIndex(11);
        System.out.println(list.search(0));
        list.display();

        list.deleteEnd();
        list.deleteStart();
        list.delete(5);
        list.display();
    }
}
