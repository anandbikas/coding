package com.anand.coding.dsalgo.tree.btree;

import java.util.Arrays;

/**
 * B-Tree Node
 */
public class BNode<K extends Comparable<K>, V> {

    public static class Entry<K extends Comparable<K>,V> implements Comparable<Entry<K,V>> {
        public K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K,V> o) {
            return this.key.compareTo(o.key);
        }

        @Override
        public String toString() {
            return key + (value==null ? "" : "=" + value);
        }
    }

    public int t; // Degree
    public int n; // Number of entries

    public Entry<K,V>[] entries;
    public BNode<K,V>[] children;


    /**
     *
     * @param t degree
     */
    public BNode(int t) {
        this.t = t;
        entries = (Entry<K,V> []) new Entry[2*t-1];
    }

    /**
     *
     * @return
     */
    public boolean isFull(){
        return n==2*t-1;
    }

    /**
     *
     * @return
     */
    public boolean isLeaf(){
        return children==null;
    }

    /**
     * Inserts a key in a sorted array in sorted fashion.
     *
     * @param key
     */
    public int insertAsSorted(K key, V value) {

        if(!isLeaf()){
            throw new RuntimeException("Not a leaf node");
        }

        int j;
        for(j=n-1; j >= 0 && entries[j].key.compareTo(key) > 0; j--) {
            entries[j+1] = entries[j];
        }
        entries[j+1] = new Entry<>(key,value);
        n++;

        return j+1;
    }

    /**
     * Inserts a key in a sorted array in sorted fashion.
     *
     * @param entry
     * @param leftChild
     * @param rightChild
     */
    public int insertAsSortedIntoInternalNode(Entry<K,V> entry, BNode<K,V> leftChild, BNode<K,V> rightChild){

        if(children == null){
            children = new BNode[2 * t];
        }

        //Shift entries and children to make room for the new comer.
        int j;
        children[n+1] = children[n];
        for(j=n-1; j >= 0 && entries[j].key.compareTo(entry.key) > 0; j--){
            entries[j+1] = entries[j];
            children[j+1] = children[j];
        }

        entries[j+1] = entry;
        children[j+1] = leftChild;
        children[j+1+1] = rightChild;
        n++;

        return j+1;
    }

    /**
     * Get index of the largest key <= given key, -1 if not found.
     */
    public int getKeyIndex(K key){
        //Binary Search
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (key.compareTo(entries[mid].key) == 0) {
                return mid;
            }

            if (key.compareTo(entries[mid].key) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    /**
     * Split the node into two adjust the middle entry into parent
     * And return the index of the shifted entry in parent node.
     *
     * @param parent
     * @return
     */
    public int split(BNode<K,V> parent){

        if(parent==null){
            throw new IllegalArgumentException("Parent can not be null");
        }

        BNode<K,V> rightChild = new BNode<>(t);
        int middleIndex = n/2;

        int i,j;
        for(i=middleIndex+1, j=0; i<n; i++, j++){
            rightChild.entries[j] = entries[i];
            entries[i]=null;
        }

        if(!isLeaf()) {
            rightChild.children = new BNode[2 * t];

            for(i=middleIndex+1, j=0; i<n; i++, j++){
                rightChild.children[j] = children[i];
                children[i]=null;
            }
            rightChild.children[j] = children[i];
            children[i]=null;
        }

        n = middleIndex;
        rightChild.n = j;

        Entry<K,V> entryToShiftUp = entries[middleIndex];
        entries[middleIndex] = null;

        //Insert the newly born child into parent.
        return parent.insertAsSortedIntoInternalNode(entryToShiftUp, this, rightChild);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "BNode{" +
                "entries=" + Arrays.toString(entries) +
                ", n=" + n +
                ", children=" + Arrays.toString(children) +
                ", t=" + t +
                '}';
    }
}