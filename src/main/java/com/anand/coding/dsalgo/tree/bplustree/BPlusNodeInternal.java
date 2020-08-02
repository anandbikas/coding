package com.anand.coding.dsalgo.tree.bplustree;

import java.util.Arrays;

/**
 * BPlusTree Node
 */
public class BPlusNodeInternal<K extends Comparable<K>, V> implements BPlusNode<K,V> {

    public int t; // Degree
    public int n; // Number of entries

    public K[] keys;
    public BPlusNode<K,V>[] children;


    /**
     *
     * @param t degree
     */
    public BPlusNodeInternal(int t) {
        this.t = t;
        keys = (K []) new Comparable[2*t-1];
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
        return n==2*t-1;
    }

    /**
     * Inserts a key in a sorted array in sorted fashion.
     *
     * @param key
     * @param leftChild
     * @param rightChild
     */
    public int insertAsSortedIntoInternalNode(K key, BPlusNode<K,V> leftChild, BPlusNode<K,V> rightChild){

        if(children == null){
            children = new BPlusNode[2 * t];
        }

        //Shift entries and children to make room for the new comer.
        int j;
        children[n+1] = children[n];
        for(j=n-1; j >= 0 && keys[j].compareTo(key) > 0; j--){
            keys[j+1] = keys[j];
            children[j+1] = children[j];
        }

        keys[j+1] = key;
        children[j+1] = leftChild;
        children[j+1+1] = rightChild;
        n++;

        return j+1;
    }

    /**
     *
     * @param index
     * @return
     */
    public K getKey(int index){
        return keys[index];
    }

    /**
     * Get index of the largest key <= given key, -1 if not found.
     */
    public int getKeyIndex(K key){
        //Binary Search
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (key.compareTo(keys[mid]) == 0) {
                return mid;
            }

            if (key.compareTo(keys[mid]) < 0) {
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

        BPlusNodeInternal<K,V> rightChild = new BPlusNodeInternal<>(t);
        int middleIndex = n/2;

        int i,j;
        for(i=middleIndex+1, j=0; i<n; i++, j++){
            rightChild.keys[j] = keys[i];
            keys[i]=null;
        }

        rightChild.children = new BPlusNode[2 * t];

        for(i=middleIndex+1, j=0; i<n; i++, j++){
            rightChild.children[j] = children[i];
            children[i]=null;
        }
        rightChild.children[j] = children[i];
        children[i]=null;

        n = middleIndex;
        rightChild.n = j;

        K keyToShiftUp = keys[middleIndex];
        keys[middleIndex] = null;

        //Insert the newly born child into parent.
        return parent.insertAsSortedIntoInternalNode(keyToShiftUp, this, rightChild);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "BPlusNodeInternal{" +
                "keys=" + Arrays.toString(keys) +
                ", n=" + n +
                ", children=" + Arrays.toString(children) +
                ", t=" + t +
                '}';
    }
}