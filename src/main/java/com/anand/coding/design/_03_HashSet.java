package com.anand.coding.design;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class _03_HashSet {

    private int capacity = 10;
    private final double loadFactor = 0.8;

    private Node[] hashSet;
    private int count = 0;

    /**
     *
     */
    public _03_HashSet() {
        hashSet = new Node[capacity];
    }

    /**
     *
     */
    public _03_HashSet(int capacity) {
        this.capacity = capacity;
        hashSet = new Node[capacity];
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean contains(int key) {
        Node prev = find(key);
        return prev.next != null;
    }

    /**
     *
     * @param key
     */
    public void add(int key) {
        if (count >= loadFactor * capacity) {
            rehash();
        }

        Node prev = find(key);
        if (prev.next == null) {
            prev.next = new Node(key);
            count++;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public void remove(int key) {
        Node prev = find(key);
        if (prev.next != null) {
            prev.next = prev.next.next;
            count--;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    private Node find(int key) {

        int hashCode = hash(key);
        if (hashSet[hashCode] == null) {
            return hashSet[hashCode] = new Node(-1);
        }

        Node prev = null;
        for(Node node = hashSet[hashCode]; !(node==null || node.key==key); prev = node, node = node.next);

        return prev;
    }

    /**
     *
     * @param key
     * @return
     */
    private int hash(int key){
        return key % capacity;
    }

    /**
     *
     */
    private synchronized void rehash() {
        if (count < loadFactor * capacity) {
            return;
        }

        capacity *= 2;
        Node[] oldHashMap = hashSet;
        hashSet = new Node[capacity];

        Arrays.stream(oldHashMap).filter(Objects::nonNull).forEach(bucket -> {

            for(Node node = bucket.next; node != null;) {
                int hashCode = hash(node.key);
                if(hashSet[hashCode]==null) hashSet[hashCode] = new Node(-1);

                Node nextNode=node.next;

                node.next = hashSet[hashCode].next;
                hashSet[hashCode].next = node;

                node = nextNode;
            }
        });
    }

    /**
     *
     */
    public static class Node {
        int key;
        Node next;

        public Node(int key) {
            this.key = key;
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        _03_HashSet hashSet = new _03_HashSet();

        IntStream.range(0,500).forEach(hashSet::add);

        IntStream.range(0,500).parallel().forEach(key -> {
            System.out.println(hashSet.contains(key));
        });

        System.out.println(hashSet.contains(500));
    }
}