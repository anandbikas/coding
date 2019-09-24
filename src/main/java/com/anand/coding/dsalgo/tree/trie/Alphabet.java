package com.anand.coding.dsalgo.tree.trie;

import java.util.Set;

/**
 *
 */
public interface Alphabet {

    /**
     *
     * @return
     */
    public int getSize();

    /**
     *
     * @param c
     * @return
     */
    public int charToIndex(char c);

    /**
     *
     * @return
     */
    public Set<Character> getCharSet();

}
