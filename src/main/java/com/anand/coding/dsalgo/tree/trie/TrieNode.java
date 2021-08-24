package com.anand.coding.dsalgo.tree.trie;

/**
 * TrieNode for dictionary
 */
public class TrieNode<T> {

    public TrieNode<T> [] children;
    T value; // used to store value for key-value pair

    public TrieNode(int size) {
        this.children = new TrieNode[size];;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "value='" + value + '\'' +
                '}';
    }
}
