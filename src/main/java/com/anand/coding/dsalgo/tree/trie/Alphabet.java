package com.anand.coding.dsalgo.tree.trie;

import java.util.Set;

/**
 *
 */
public interface Alphabet {

    public int size();
    public int charToIndex(char c);
    public Set<Character> charSet();
}
