package com.anand.coding.dsalgo.tree.map;

/**
 * Binary Node with  key->value and left and right child nodes.
 * @param <K>
 * @param <V>
 */
public class Node<K extends Comparable<K>, V> implements Comparable<Node<K,V>>{

    K key;
    V value;
    Node<K,V> left;
    Node<K,V> right;

    /**
     *
     */
    public Node(){
        super();
    }

    /**
     *
     * @param key
     * @param value
     */
    public Node(K key, V value){
        this.key = key;
        this.value = value;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return key + " -> " + String.valueOf(value);
    }

    /**
     *
     * @param node
     * @return
     */
    @Override
    public int compareTo(Node<K,V> node) {
        return this.key.compareTo(node.key);
    }
}
