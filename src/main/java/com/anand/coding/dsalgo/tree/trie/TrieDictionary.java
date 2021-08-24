package com.anand.coding.dsalgo.tree.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * TrieDictionary
 */
public class TrieDictionary<T> {

    final private Alphabet alphabet;
    final private TrieNode<T> root;

    public TrieDictionary(Alphabet alphabet) {
        this.alphabet = alphabet;
        this.root = new TrieNode<>(alphabet.size());
    }

    /**
     *
     * @param key
     * @param value
     */
    public void insert(final String key, final T value){

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
            int childIndex = alphabet.charToIndex(c);
            if(trieNode.children[childIndex]==null){
                trieNode.children[childIndex] = new TrieNode<>(alphabet.size());
            }
            trieNode = trieNode.children[childIndex];
        }
        trieNode.value = value;
    }

    /**
     *
     * @param key
     * @return
     */
    public T search(final String key){

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
            trieNode = trieNode.children[alphabet.charToIndex(c)];
            if(trieNode==null){
                return null;
            }
        }
        return trieNode.value;
    }

    /**
     *
     * @param key
     * @return
     */
    public void delete(final String key){

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
            trieNode = trieNode.children[alphabet.charToIndex(c)];
            if(trieNode==null){
                return;
            }
        }
        trieNode.value=null;
    }

    /**
     * Its like
     * printAllWords
     * printAllPaths
     */
    public void display() {
        System.out.println(String.format("%10s%15s", "Key", "Value"));
        System.out.println(             "--------------------------------");

        display(root, new StringBuilder());

        System.out.println();
    }

    /**
     *
     * @param trieNode
     * @param sb
     */
    private void display(TrieNode<T> trieNode, StringBuilder sb) {

        for(char c: alphabet.charSet()){
            TrieNode<T> child = trieNode.children[alphabet.charToIndex(c)];
            if(child!=null){
                sb.append(c);
                if(child.value!=null){
                    System.out.println(
                            String.format("%-18s%s", sb, child.value));
                }
                display(child, sb);
                sb.setLength(sb.length()-1);
            }
        }
    }

    /**
     *
     * @param prefix
     * @return
     */
    public List<String> wordsWithPrefix(String prefix) {

        List<String> list = new ArrayList<>();

        if (prefix == null || prefix.isEmpty()) {
            return list;
        }

        StringBuilder sb = new StringBuilder();

        TrieNode<T> trieNode = root;
        for(char c: prefix.toUpperCase().toCharArray()){
            trieNode = trieNode.children[alphabet.charToIndex(c)];
            if(trieNode==null){
                return list;
            }
            sb.append(c);
        }

        wordsWithPrefix(trieNode, sb, list);

        return list;
    }

    /**
     *
     * @param trieNode
     * @param sb
     * @param list
     */
    private void wordsWithPrefix(TrieNode<T> trieNode, StringBuilder sb, List<String> list){
        if(trieNode == null){
            return;
        }

        for(char c: alphabet.charSet()){
            TrieNode<T> child = trieNode.children[alphabet.charToIndex(c)];

            if(child!=null){
                sb.append(c);
                if(child.value!=null){
                    list.add(sb.toString());
                }
                wordsWithPrefix(child, sb, list);
                sb.setLength(sb.length()-1);
            }
        }
    }


    /**
     *  main function to test TrieDictionary
     * @param args
     */
    public static void main(String [] args){

        final TrieDictionary<String> trieDictionary = new TrieDictionary<>(new EnglishAlphabet());

        trieDictionary.insert("elephant", "hathi");
        trieDictionary.insert("book", "kitab");
        trieDictionary.insert("boot","juta");
        trieDictionary.insert("hippopotamus","dariyayi ghoda");

        trieDictionary.display();

        String prefix = "bo";

        System.out.println("All words prefixed with : " + prefix);
        trieDictionary.wordsWithPrefix(prefix).forEach(System.out::println);

        trieDictionary.delete("boot");
        trieDictionary.display();

        System.out.println("trieDictionary.search(elephant): " + trieDictionary.search("elephant"));
        System.out.println("trieDictionary.search(boot): " + trieDictionary.search("boot"));
    }
}
