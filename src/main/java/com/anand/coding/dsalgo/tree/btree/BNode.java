package com.anand.coding.dsalgo.tree.btree;

import java.util.Arrays;

/**
 * B-Tree Node
 */
public class BNode<K extends Comparable<K>> {

    // This can be extended to store key->value.
    // Pair type can be used to store key value pair.     //public T[] values;
    public K[] keys;

    // Number of keys
    public int n;

    public BNode<K>[] children;
    public int t;


    /**
     *
     * @param t degree
     */
    public BNode(int t) {
        this.t = t;
        keys = (K [])new Comparable[2*t-1];
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
    public void insertAsSorted(K key) {

        int j;
        for(j=n-1; j >= 0 && keys[j].compareTo(key) > 0; j--) {
            keys[j+1] = keys[j];
        }
        keys[j+1] = key;
        n++;
    }

    /**
     *
     * @param childIndex
     * @param childNode
     */
    public void splitChild(int childIndex, BNode<K> childNode){

        //Split childNode into two
        BNode<K> rightChild = new BNode<>(t);
        int middleIndex = childNode.n/2;

        int j=0; int i;
        for(i=middleIndex+1; i<childNode.n; i++, j++){
            rightChild.keys[j] = childNode.keys[i];
            childNode.keys[i]=null;
        }

        if(!childNode.isLeaf()) {
            rightChild.children = new BNode[2 * t];

            j=0;
            for(i=middleIndex+1; i<childNode.n; i++, j++){
                rightChild.children[j] = childNode.children[i];
                childNode.children[i]=null;
            }
            rightChild.children[j] = childNode.children[i];
            childNode.children[i]=null;
        }


        childNode.n = middleIndex;
        rightChild.n = j;

        //Move up the middleKey and update its left and right child.
        if(this.children ==null){
            this.children = new BNode[2 * t];
        }
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
     * @return
     */
    @Override
    public String toString() {
        return "BNode{" +
                "keys=" + Arrays.toString(keys) +
                ", n=" + n +
                ", children=" + Arrays.toString(children) +
                ", t=" + t +
                '}';
    }
}