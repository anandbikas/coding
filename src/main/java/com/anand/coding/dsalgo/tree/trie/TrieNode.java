package com.anand.coding.dsalgo.tree.trie;

import java.util.Arrays;

/**
 * TrieNode for dictionary
 */
public class TrieNode {
    private final static int ALPHABET_SIZE = 26;
    private final static int A = 'A';

    private TrieNode [] children = new TrieNode[ALPHABET_SIZE];

    //if wordMeaning == null, this is not a dictionary word.
    private String wordMeaning;

    /**
     *
     */
    public TrieNode(){
        super();
    }

    /**
     *
     * @param c
     */
    public void setChild(final char c){
        final int childIndex = Character.toUpperCase(c)-A;
        if(children[childIndex] == null){
            children[childIndex] = new TrieNode();
        }
    }

    /**
     *
     * @param c
     * @return
     */
    public TrieNode getChild(final char c){
        return children[Character.toUpperCase(c)-A];
    }

    /**
     *
     * @return
     */
    public String getWordMeaning() {
        return wordMeaning;
    }

    /**
     *
     * @param meaning
     */
    public void setWordMeaning(String meaning) {
        this.wordMeaning = meaning;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "TrieNode{" +
                "wordMeaning='" + wordMeaning + '\'' +
                '}';
    }
}
