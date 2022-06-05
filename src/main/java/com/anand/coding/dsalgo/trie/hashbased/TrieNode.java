package com.anand.coding.dsalgo.trie.hashbased;

import java.util.HashMap;
import java.util.Map;

/**
 * Hash based TrieNode for dictionary
 */
public class TrieNode {

    Map<Character, TrieNode> charMap = new HashMap<>();
    Object value; //value for key-value pair
    int count=0; //Optional count to indicate how many words contain this character at a particular level.

    @Override
    public String toString() {
        return "TrieNode{" +
                "value='" + value + '\'' +
                '}';
    }
}
