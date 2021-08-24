package com.anand.coding.dsalgo.tree.trie.hashbased;

import java.util.ArrayList;
import java.util.List;

/**
 * TrieDictionary using hashMap
 */
public class TrieDictionary<T> {

    private TrieNode<T> root = new TrieNode<>();

    /**
     *
     * @param key
     * @param value
     */
    public void insert(final String key, final T value){

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
            if(!trieNode.charMap.containsKey(c)){
                trieNode.charMap.put(c, new TrieNode<>());
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
    public T search(final String key){

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
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
    public void delete(final String key){

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
            trieNode = trieNode.charMap.get(c);
            if(trieNode==null){
                return;
            }
        }
        trieNode.value=null;

        //Decrease counter and detach the node from where count becomes 0.
        TrieNode<T> trieNodeParent = null;
        trieNode = root;

        for(char c: key.toCharArray()){
            trieNodeParent = trieNode;
            trieNode = trieNode.charMap.get(c);
            trieNode.count--;
            if(trieNode.count==0){
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

        for(char c: trieNode.charSet()){
            TrieNode<T> child = trieNode.charMap.get(c);
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
    private void displayAllShortestUniquePrefix(TrieNode<T> trieNode, StringBuilder sb) {

        for(char c: trieNode.charSet()){
            TrieNode<T> child = trieNode.charMap.get(c);
            if(child!=null){
                sb.append(c);
                if(child.count==1){
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

        StringBuilder sb = new StringBuilder();

        TrieNode<T> trieNode = root;
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
    private void wordsWithPrefix(TrieNode<T> trieNode, StringBuilder sb, List<String> list){
        if(trieNode == null){
            return;
        }

        for(char c: trieNode.charSet()){
            TrieNode<T> child = trieNode.charMap.get(c);

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

        final TrieDictionary<String> trieDictionary = new TrieDictionary<>();

        trieDictionary.insert("elephant", "hathi");
        trieDictionary.insert("book", "kitab");
        trieDictionary.insert("boot","juta");
        trieDictionary.insert("hippopotamus","dariyayi ghoda");

        trieDictionary.display();
        trieDictionary.displayAllShortestUniquePrefix();

        String prefix = "bo";

        System.out.println("All words prefixed with : " + prefix);
        trieDictionary.wordsWithPrefix(prefix).forEach(System.out::println);

        trieDictionary.delete("boot");
        trieDictionary.display();

        System.out.println("trieDictionary.search(elephant): " + trieDictionary.search("elephant"));
        System.out.println("trieDictionary.search(boot): " + trieDictionary.search("boot"));

        trieDictionary.insert(";}#)*!~", "bikas_anand");
        trieDictionary.insert("®ÆǛ", "latin");
        trieDictionary.display();
    }
}
