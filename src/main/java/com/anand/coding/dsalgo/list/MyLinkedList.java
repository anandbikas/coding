package com.anand.coding.dsalgo.list;

/**
 * MyLinkedList
 * To reduce start==null check complexity, use a header node with header.next=start;
 *
 * leetcode.com/problems/design-linked-list
 */
public class MyLinkedList {

    private final Node header = new Node(0);

    public void addAtHead(int data){
        final Node newNode = new Node(data);
        newNode.next = header.next;
        header.next = newNode;
    }

    public void addAtTail(int data){
        final Node newNode = new Node(data);

        Node node;
        for(node=header; node.next!=null; node=node.next);
        node.next = newNode;
    }

    public void addAtIndex(int index, int data){
        final Node newNode = new Node(data);

        Node node=header;
        for(int i=0; node!=null && i<index; node=node.next, i++);

        if(node!=null){
            newNode.next = node.next;
            node.next = newNode;
        }
    }

    public void deleteAtIndex(int index){
        if(index<0) {
            return;
        }

        Node node=header;
        for(int i=0; node.next!=null && i<index; node=node.next, i++);

        if(node.next!=null){
            Node deletedNode=node.next;
            node.next = deletedNode.next;
            deletedNode.next = null;

        }
    }

    public int get(int index){
        if(index<0) {
            return -1;
        }

        Node node=header.next;
        for(int i=0; node!=null && i<index; node=node.next, i++);

        return node==null? -1 : node.data;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1,2);
        System.out.println(list.get(1));
        list.deleteAtIndex(1);
        System.out.println(list.get(1));

    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
