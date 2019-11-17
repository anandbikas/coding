package com.anand.coding.dsalgo.tree.bplustree;

import com.anand.coding.dsalgo.graph.adjacencylist.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

/**
 * BPlusTree Node
 */
public class BPlusNodeLeaf<K extends Comparable<K>, V> implements BPlusNode<K,V> {

    // public K[] keyValueList;
    Pair<K,V>[] keyValueList;

    // Number of keyValue's
    int n;
    int t;

    BPlusNodeLeaf<K,V> next;



    /**
     *
     * @param t degree
     */
    public BPlusNodeLeaf(int t) {
        this.t = t;
        keyValueList = (Pair<K,V> []) new Pair[2*t-1 + 1];  // Extra +1 to store its left parent.
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
    public void insertAsSorted(K key, V value) {

        int j;
        for(j=n-1; j >= 0 && keyValueList[j].getKey().compareTo(key) > 0; j--) {
            keyValueList[j+1] = keyValueList[j];
        }
        keyValueList[j+1] = new Pair<>(key,value);
        n++;
    }

    /**
     *
     * @param index
     * @return
     */
    public K getKey(int index){
        return keyValueList[index].getKey();
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
     * @return
     */
    @Override
    public String toString() {
        return "BPlusNodeLeaf{" +
                "keyValueList=" + Arrays.toString(keyValueList) +
                ", n=" + n +
                ", t=" + t +
                '}';
    }
}