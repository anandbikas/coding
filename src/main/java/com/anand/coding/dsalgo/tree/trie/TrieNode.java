package com.anand.coding.dsalgo.tree.trie;

/**
 * TrieNode for dictionary
 */
public class TrieNode<T> {

    public final static Alphabet alphabet = new EnglishAlphabet();
    private TrieNode<T> [] children;

    //if value == null, this is not a dictionary word. Else a corresponding value to the key.
    T value;

    /**
     *
     * @param c
     */
    public TrieNode<T> setChild(final char c){
        final int childIndex = alphabet.charToIndex(c);

        if(children==null){
            this.children = new TrieNode[alphabet.getSize()];
        }

        if(children[childIndex] == null){
            children[childIndex] = new TrieNode<>();
        }

        return children[childIndex];
    }

    /**
     *
     * @param c
     * @return
     */
    public TrieNode<T> getChild(final char c){
        return children==null ? null : children[alphabet.charToIndex(c)];
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
