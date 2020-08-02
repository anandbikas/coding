package com.anand.coding.dsalgo.tree.bplustree;

/**
 * BPlusTree Node
 */
public interface BPlusNode<K extends Comparable<K>, V> {

    public int size();
    public boolean isFull();
    public K getKey(int index);
    public int getKeyIndex(K key);
    public int split(BPlusNodeInternal<K,V> parent);
}