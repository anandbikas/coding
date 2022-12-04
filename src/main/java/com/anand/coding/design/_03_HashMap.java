package com.anand.coding.design;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Deign a HashMap.
 *
 * leetcode.com/problems/design-hashmap
 */
public class _03_HashMap {

    private int capacity = 10;
    private final double loadFactor = 0.8;

    private Node[] hashMap;
    private int count = 0;

    /**
     *
     */
    public _03_HashMap() {
        hashMap = new Node[capacity];
    }

    /**
     *
     */
    public _03_HashMap(int capacity) {
        this.capacity = capacity;
        hashMap = new Node[capacity];
    }

    /**
     *
     * @param key
     * @return
     */
    public int get(int key) {
        Node prev = find(key);

        return prev.next == null ? -1 : prev.next.value;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (count >= loadFactor * capacity) {
            rehash();
        }

        Node prev = find(key);
        if (prev.next == null) {
            prev.next = new Node(key, value);
            count++;
        } else {
            prev.next.value = value;
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
        if (hashMap[hashCode] == null) {
            return hashMap[hashCode] = new Node(-1,-1);
        }

        Node prev = null;
        for(Node node = hashMap[hashCode]; !(node==null || node.key==key); prev = node, node = node.next);

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
        Node[] oldHashMap = hashMap;
        hashMap = new Node[capacity];

        Arrays.stream(oldHashMap).filter(Objects::nonNull).forEach(bucket -> {

            for(Node node = bucket.next; node != null;) {
                int hashCode = hash(node.key);
                if(hashMap[hashCode]==null) hashMap[hashCode] = new Node(-1,-1);

                Node nextNode=node.next;

                node.next = hashMap[hashCode].next;
                hashMap[hashCode].next = node;

                node = nextNode;
            }
        });
    }

    /**
     *
     */
    public static class Node {
        int key, value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        _03_HashMap hashMap = new _03_HashMap();

        IntStream.range(0,500).forEach(key -> {
            hashMap.put(key, key);
        });

        IntStream.range(0,500).parallel().forEach(key -> {
            System.out.println(hashMap.get(key));
        });

        System.out.println(hashMap.get(500));
    }
}