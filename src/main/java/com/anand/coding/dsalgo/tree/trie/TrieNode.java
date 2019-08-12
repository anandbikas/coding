package com.anand.coding.dsalgo.tree.trie;

import com.anand.coding.dsalgo.tree.trie.exception.TrieCharacterNotSupportedException;

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
     * @return
     * @throws TrieCharacterNotSupportedException
     */
    private int charToIndex(char c){
        c = Character.toUpperCase(c);
        if(c>='A' && c<='Z'){
            return Character.toUpperCase(c)-A;
        } else {
            throw new TrieCharacterNotSupportedException(String.format("Character %s not supported", c));
        }
    }

    /**
     *
     * @param c
     */
    public void setChild(final char c){
        final int childIndex = charToIndex(c);
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
        return children[charToIndex(c)];
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
