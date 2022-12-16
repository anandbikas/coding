package com.anand.coding.dsalgo.trie.hashbased;

import java.util.HashMap;
import java.util.Map;

/**
 * TrieNode for dictionary
 */
public class TrieNode {

    Map<Character, TrieNode> charMap = new HashMap<>();
    Object val; //word meaning
    int count=0; //Optional count to indicate how many words contain this character at a particular level.
}
