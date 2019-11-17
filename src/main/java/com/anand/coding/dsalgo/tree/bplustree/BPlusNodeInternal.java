package com.anand.coding.dsalgo.tree.bplustree;

import java.util.Arrays;

/**
 * BPlusTree Node
 */
public class BPlusNodeInternal<K extends Comparable<K>, V> implements BPlusNode<K,V>{

    private K[] keys;
    private int n; //current size
    private int t; //degree

    private BPlusNode<K,V>[] children;


    /**
     *
     * @param t degree
     */
    public BPlusNodeInternal(int t) {
        this.t = t;
        keys = (K [])new Comparable[2*t-1];
        children = new BPlusNode[2 * t];
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
     */
    public void insertAsSorted(K key, V value) {

        int j;
        for(j=n-1; j >= 0 && keys[j].compareTo(key) > 0; j--) {
            keys[j+1] = keys[j];
        }
        keys[j+1] = key;
        n++;
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
     *
     * @param index
     * @return
     */
    public BPlusNode<K,V> getChildren(int index){
        return children[index];
    }

    /**
     * split child
     *
     * @param childIndex
     * @param childNode
     */
    public void splitChild(int childIndex, BPlusNode<K,V> childNode){
        if(childNode instanceof BPlusNodeInternal){
            splitChild(childIndex, (BPlusNodeInternal<K, V>) childNode);
        } else {
            splitChild(childIndex, (BPlusNodeLeaf<K, V>) childNode);
        }
    }

    /**
     * split internal node
     *
     * @param childIndex
     * @param childNode
     */
    private void splitChild(int childIndex, BPlusNodeInternal<K,V> childNode){

        //Split childNode into two
        BPlusNodeInternal<K,V> rightChild = new BPlusNodeInternal<>(t);
        int middleIndex = childNode.n/2;

        int j=0; int i;
        for(i=middleIndex+1; i<childNode.n; i++, j++){
            rightChild.keys[j] = childNode.keys[i];
            childNode.keys[i]=null;
        }

        j=0;
        for(i=middleIndex+1; i<childNode.n; i++, j++){
            rightChild.children[j] = childNode.children[i];
            childNode.children[i]=null;
        }
        rightChild.children[j] = childNode.children[i];
        childNode.children[i]=null;

        childNode.n = middleIndex;
        rightChild.n = j;

        //Move up the middleKey and update its left and right child.
        children[n+1] = children[n];
        for(i=n; i>childIndex; i--){
            keys[i] = keys[i-1];
            children[i] = children[i-1];
        }

        keys[childIndex] = childNode.keys[middleIndex];
        children[childIndex] = childNode;
        children[childIndex+1] = rightChild;
        this.n++;

        childNode.keys[middleIndex] = null;
    }


    /**
     * Split leaf node
     *
     * @param childIndex
     * @param childNode
     */
    private void splitChild(int childIndex, BPlusNodeLeaf<K,V> childNode){

        //Split childNode into two
        //(Note: Best way to store an internal key at the beginning of its right leaf child)
        //So,copy middle element to the right child along with moving it to parent.
        BPlusNodeLeaf<K,V> rightChild = new BPlusNodeLeaf<>(t);
        int middleIndex = childNode.n/2;

        int j=0; int i;
        for(i=middleIndex; i<childNode.n; i++, j++){
            rightChild.keyValueList[j] = childNode.keyValueList[i];
            childNode.keyValueList[i]=null;
        }

        childNode.n = middleIndex;
        rightChild.n = j;

        //Move up the middleKey and update its left and right child.
        children[n+1] = children[n];
        for(i=n; i>childIndex; i--){
            keys[i] = keys[i-1];
            children[i] = children[i-1];
        }

        keys[childIndex] = rightChild.keyValueList[0].getKey();
        children[childIndex] = childNode;
        children[childIndex+1] = rightChild;
        this.n++;

        // Link leaf nodes.
        childNode.next = rightChild;
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