package com.anand.coding.dsalgo.tree.trie.hashbased;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Hash based TrieNode for dictionary
 */
public class TrieNode<T> {

    private Map<Character, TrieNode<T>> charMap = new HashMap<>();

    //if value == null, this is not a dictionary word. Else a corresponding value to the key.
    private T value;

    /**
     *
     * @return
     */
    public Set<Character> getCharSet(){
        return charMap.keySet();
    }


    /**
     *
     * @param c
     */
    public void setChild(char c){
        c = Character.toUpperCase(c);
        if(!charMap.containsKey(c)){
            charMap.put(c,  new TrieNode<>());
        }
    }

    /**
     *
     * @param c
     * @return
     */
    public TrieNode<T> getChild(final char c){
        return charMap.get(Character.toUpperCase(c));
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
