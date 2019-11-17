package com.anand.coding.dsalgo.tree.bplustree;

/**
 * BPlusTree Node
 */
public interface BPlusNode<K extends Comparable<K>, V> {

    public int size();
    public boolean isFull();
    public void insertAsSorted(K key, V value);
    public K getKey(int index);
    public int getKeyIndex(K key);
}