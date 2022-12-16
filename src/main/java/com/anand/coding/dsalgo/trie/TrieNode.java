package com.anand.coding.dsalgo.trie;

/**
 * TrieNode for dictionary
 */
public class TrieNode {

    TrieNode [] children;
    Object val; //word meaning
    int count=0; //Optional count to indicate how many words contain this character at a particular level.

    public TrieNode(int size) {
        this.children = new TrieNode[size];
    }
}
