package com.anand.coding.dsalgo.tree.btree;

import com.anand.coding.dsalgo.graph.adjacencylist.Pair;

import java.util.Arrays;

/**
 * B-Tree Node
 */
public class BNode<K extends Comparable<K>, V> {

    // public K[] keyValueList;
    public Pair<K,V>[] keyValueList;

    // Number of keyValue's
    public int n;

    // Degree
    public int t;

    public BNode<K,V>[] children;


    /**
     *
     * @param t degree
     */
    public BNode(int t) {
        this.t = t;
        keyValueList = new Pair[2*t-1];
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
    public void insertAsSorted(K key, V value) {

        int j;
        for(j=n-1; j >= 0 && keyValueList[j].getKey().compareTo(key) > 0; j--) {
            keyValueList[j+1] = keyValueList[j];
        }
        keyValueList[j+1] = new Pair<>(key,value);
        n++;
    }

    /**
     * Get index of the largest key <= given key, -1 if not found.
     */
    public int getKeyIndex(K key){
        //Binary Search
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (key.compareTo(keyValueList[mid].getKey()) == 0) {
                return mid;
            }

            if (key.compareTo(keyValueList[mid].getKey()) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    /**
     *
     * @param childIndex
     * @param childNode
     */
    public void splitChild(int childIndex, BNode<K,V> childNode){

        //Split childNode into two
        BNode<K,V> rightChild = new BNode<>(t);
        int middleIndex = childNode.n/2;

        int j=0; int i;
        for(i=middleIndex+1; i<childNode.n; i++, j++){
            rightChild.keyValueList[j] = childNode.keyValueList[i];
            childNode.keyValueList[i]=null;
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
            keyValueList[i] = keyValueList[i-1];
            children[i] = children[i-1];
        }

        keyValueList[childIndex] = childNode.keyValueList[middleIndex];
        children[childIndex] = childNode;
        children[childIndex+1] = rightChild;
        this.n++;

        childNode.keyValueList[middleIndex] = null;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "BNode{" +
                "keyValueList=" + Arrays.toString(keyValueList) +
                ", n=" + n +
                ", children=" + Arrays.toString(children) +
                ", t=" + t +
                '}';
    }
}