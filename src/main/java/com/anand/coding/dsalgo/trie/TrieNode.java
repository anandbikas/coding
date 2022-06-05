package com.anand.coding.dsalgo.trie;

/**
 * TrieNode for dictionary
 */
public class TrieNode {

    TrieNode [] children;
    Object value; //value for key-value pair

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
