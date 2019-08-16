package com.anand.coding.dsalgo.tree.trie;

import com.anand.coding.dsalgo.tree.trie.exception.TrieCharacterNotSupportedException;

/**
 * TrieNode for dictionary
 */
public class TrieNode<T> {
    private final static int ALPHABET_SIZE = 26;
    private final static int A = 'A';

    private TrieNode [] children = new TrieNode[ALPHABET_SIZE];

    //if value == null, this is not a dictionary word. Else a corresponding value to the key.
    private T value;

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
    public T getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "TrieNode{" +
                "value='" + value + '\'' +
                '}';
    }
}
