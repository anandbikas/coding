package com.anand.coding.dsalgo.tree.trie.hashbased;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Hash based TrieNode for dictionary
 */
public class TrieNode<T> {

    Map<Character, TrieNode<T>> charMap = new HashMap<>();
    T value; // used to store value for key-value pair
    int count=0; //Optional count to indicate how many words contain this character at a particular level.

    public Set<Character> charSet(){
        return charMap.keySet();
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "value='" + value + '\'' +
                '}';
    }
}
