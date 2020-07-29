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

    //Optional count to indicate how many words contain this character at a particular level.
    private int count=0;

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
    public TrieNode<T> setChild(char c){
        c = Character.toUpperCase(c);
        if(!charMap.containsKey(c)){
            charMap.put(c,  new TrieNode<>());
        }

        return charMap.get(c);
    }

    /**
     *
     * @param c
     */
    public void deleteChild(char c){
        c = Character.toUpperCase(c);
        if(!charMap.containsKey(c)){
            charMap.remove(c);
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
    public int getCount() {
        return count;
    }

    /**
     *
     */
    public void incrementCounter(){
        count++;
    }

    /**
     *
     */
    public void decrementCounter(){
        count--;
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
