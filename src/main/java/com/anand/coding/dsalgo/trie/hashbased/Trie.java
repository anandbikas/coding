package com.anand.coding.dsalgo.trie.hashbased;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie using hashMap
 */
public class Trie {

    private final TrieNode root = new TrieNode();

    /**
     *
     * @param key
     * @param value
     */
    public void insert(final String key, final Object value){

        TrieNode trieNode = root;
        for(char c: key.toCharArray()) {
            if(!trieNode.charMap.containsKey(c)){
                trieNode.charMap.put(c, new TrieNode());
            }
            trieNode = trieNode.charMap.get(c);
            trieNode.count++;
        }
        trieNode.value = value;
    }

    /**
     *
     * @param key
     * @return
     */
    public Object search(final String key) {

        TrieNode trieNode = root;
        for(char c: key.toCharArray()) {
            trieNode = trieNode.charMap.get(c);
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
    public void delete(final String key) {

        TrieNode trieNode = root;
        for(char c: key.toCharArray()) {
            trieNode = trieNode.charMap.get(c);
            if(trieNode==null){
                return;
            }
        }
        trieNode.value=null;

        //Decrease counter and detach the node from where count becomes 0.
        TrieNode trieNodeParent = null;
        trieNode = root;

        for(char c: key.toCharArray()) {
            trieNodeParent = trieNode;
            trieNode = trieNode.charMap.get(c);
            trieNode.count--;
            if(trieNode.count==0) {
                trieNodeParent.charMap.remove(c);
                return;
            }
        }
    }

    /**
     * Its like
     * printAllWords
     * printAllPaths
     */
    public void display() {
        System.out.printf("%10s%15s%n", "Key", "Value");
        System.out.println(             "--------------------------------");

        display(root, new StringBuilder());

        System.out.println();
    }

    /**
     *
     * @param trieNode
     * @param sb
     */
    private void display(TrieNode trieNode, StringBuilder sb) {

        for(char c: trieNode.charMap.keySet()) {
            TrieNode child = trieNode.charMap.get(c);
            if(child!=null){
                sb.append(c);
                if(child.value!=null){
                    System.out.printf("%-18s%s%n", sb, child.value);
                }
                display(child, sb);
                sb.setLength(sb.length()-1);
            }
        }
    }

    /**
     * Its like
     * printAllWords
     * printAllPaths
     */
    public void displayAllShortestUniquePrefix() {
        System.out.println("All Unique Prefixes");
        System.out.println(             "--------------------------------");

        displayAllShortestUniquePrefix(root, new StringBuilder());

        System.out.println();
    }

    /**
     *
     * @param trieNode
     * @param sb
     */
    private void displayAllShortestUniquePrefix(TrieNode trieNode, StringBuilder sb) {

        for(char c: trieNode.charMap.keySet()) {
            TrieNode child = trieNode.charMap.get(c);
            if(child!=null) {
                sb.append(c);
                if(child.count==1) {
                    System.out.println(sb);
                } else {
                    displayAllShortestUniquePrefix(child, sb);
                }
                sb.setLength(sb.length() - 1);
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

        TrieNode trieNode = root;
        StringBuilder sb = new StringBuilder();
        for(char c: prefix.toCharArray()){
            trieNode = trieNode.charMap.get(c);
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
    private void wordsWithPrefix(TrieNode trieNode, StringBuilder sb, List<String> list){
        if(trieNode == null){
            return;
        }

        for(char c: trieNode.charMap.keySet()) {
            TrieNode child = trieNode.charMap.get(c);

            if(child!=null) {
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
     *  main function to test Trie
     * @param args
     */
    public static void main(String [] args){

        final Trie trie = new Trie();

        trie.insert("elephant", "hathi");
        trie.insert("book", "kitab");
        trie.insert("boot","juta");
        trie.insert("hippopotamus","dariyayi ghoda");

        trie.display();
        trie.displayAllShortestUniquePrefix();

        String prefix = "bo";

        System.out.println("All words prefixed with : " + prefix);
        trie.wordsWithPrefix(prefix).forEach(System.out::println);

        trie.delete("boot");
        trie.display();

        System.out.println("trie.search(elephant): " + trie.search("elephant"));
        System.out.println("trie.search(boot): " + trie.search("boot"));

        trie.insert(";}#)*!~", "bikas_anand");
        trie.insert("®ÆǛ", "latin");
        trie.display();
    }
}
