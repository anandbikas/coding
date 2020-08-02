package com.anand.coding.dsalgo.tree.bplustree;

import java.util.Arrays;

/**
 * BPlusTree Leaf Node
 */
public class BPlusNodeLeaf<K extends Comparable<K>, V> implements BPlusNode<K,V> {

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
    public BPlusNodeLeaf<K,V>[] children;

    public BPlusNodeLeaf<K,V> next;


    /**
     *
     * @param t degree
     */
    public BPlusNodeLeaf(int t) {
        this.t = t;
        entries = (Entry<K,V> []) new Entry[2*t-1 + 1];  // Extra +1 to store its left parent.
    }

    /**
     *
     * @return
     */
    public int size(){
        return n;
    }

    /**
     *
     * @return
     */
    public boolean isFull(){
        return n==2*t-1 + 1;
    }

    /**
     * Inserts a key in a sorted array in sorted fashion.
     *
     * @param key
     */
    public int insertAsSorted(K key, V value) {

        int j;
        for(j=n-1; j >= 0 && entries[j].key.compareTo(key) > 0; j--) {
            entries[j+1] = entries[j];
        }
        entries[j+1] = new Entry<>(key,value);
        n++;

        return j+1;
    }

    /**
     *
     * @param index
     * @return
     */
    public K getKey(int index){
        return entries[index].key;
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
    public int split(BPlusNodeInternal<K,V> parent){

        if(parent==null){
            throw new IllegalArgumentException("Parent can not be null");
        }

        BPlusNodeLeaf<K,V> rightChild = next = new BPlusNodeLeaf<>(t);
        int middleIndex = n/2;

        //Note: Best way to store an internal key at the beginning of its right leaf child
        //      So,copy middle element to the right child along with moving it to parent.
        rightChild.entries[0] = entries[middleIndex];

        int i,j;
        for(i=middleIndex+1, j=1; i<n; i++, j++){
            rightChild.entries[j] = entries[i];
            entries[i]=null;
        }

        n = middleIndex;
        rightChild.n = j;

        K keyToShiftUp = rightChild.entries[0].key;
        entries[middleIndex] = null;

        //Insert the newly born child into parent.
        return parent.insertAsSortedIntoInternalNode(keyToShiftUp, this, rightChild);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "BPlusNodeLeaf{" +
                "entries=" + Arrays.toString(entries) +
                ", n=" + n +
                ", t=" + t +
                '}';
    }
}