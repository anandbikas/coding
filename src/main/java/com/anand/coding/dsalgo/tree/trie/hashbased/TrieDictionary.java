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
            trieNode = trieNode.setChild(c);
            trieNode.incrementCounter();
        }
        trieNode.setValue(value);
    }

    /**
     *
     * @param key
     * @return
     */
    public T search(final String key){
        if(root == null){
            return null;
        }

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
            trieNode = trieNode.getChild(c);
            if(trieNode==null){
                return null;
            }
        }
        return trieNode.getValue();
    }

    /**
     *
     * @param key
     * @return
     */
    public void delete(final String key){
        if(root == null){
            return;
        }

        TrieNode<T> trieNode = root;
        for(char c: key.toCharArray()){
            trieNode = trieNode.getChild(c);
            if(trieNode==null){
                return;
            }
        }
        trieNode.setValue(null);

        //Decrease counter and detach the node from where count becomes 0.
        TrieNode<T> trieNodeParent = null;
        trieNode = root;

        for(char c: key.toCharArray()){
            trieNodeParent = trieNode;
            trieNode = trieNode.getChild(c);
            trieNode.decrementCounter();
            if(trieNode.getCount()==0){
                trieNodeParent.deleteChild(c);
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

        if(root!=null) {
            display(root, new StringBuilder());
        }
        System.out.println();
    }

    /**
     *
     * @param trieNode
     * @param sb
     */
    private void display(TrieNode<T> trieNode, StringBuilder sb) {

        for(char c: trieNode.getCharSet()){
            if(trieNode.getChild(c)!=null){
                TrieNode<T> child = trieNode.getChild(c);
                sb.append(c);
                if(child.getValue()!=null){
                    System.out.println(
                            String.format("%-18s%s", sb.toString(), child.getValue()));
                }
                display(child, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    /**
     * Its like
     * printAllWords
     * printAllPaths
     */
    public void displayAllUniquePrefix() {
        System.out.println("All Unique Prefixes");
        System.out.println(             "--------------------------------");

        if(root!=null) {
            displayAllUniquePrefix(root, new StringBuilder());
        }
        System.out.println();
    }

    /**
     *
     * @param trieNode
     * @param sb
     */
    private void displayAllUniquePrefix(TrieNode<T> trieNode, StringBuilder sb) {

        for(char c: trieNode.getCharSet()){
            if(trieNode.getChild(c)!=null){
                TrieNode<T> child = trieNode.getChild(c);
                sb.append(c);
                if(child.getCount()==1){
                    System.out.println(String.format("%s", sb.toString()));
                } else {
                    displayAllUniquePrefix(child, sb);
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
    public List<String> getAllPrefixedWords(String prefix) {

        List<String> list = new ArrayList<>();

        if (root == null || prefix == null) {
            return list;
        }

        StringBuilder sb = new StringBuilder();

        //Put all characters in prefix in the StringBuilder.
        TrieNode<T> trieNode = root;
        for(char c: prefix.toUpperCase().toCharArray()){
            trieNode = trieNode.getChild(c);
            if(trieNode==null){
                return list;
            }
            sb.append(c);
        }

        getAllPrefixedWords(trieNode, sb, list);

        return list;
    }

    /**
     *
     * @param trieNode
     * @param sb
     * @param list
     */
    private void getAllPrefixedWords(TrieNode<T> trieNode, StringBuilder sb, List<String> list){
        if(trieNode == null){
            return;
        }

        for(char c: trieNode.getCharSet()){
            if(trieNode.getChild(c)!=null){
                TrieNode<T> child = trieNode.getChild(c);
                sb.append(c);
                if(child.getValue()!=null){
                    list.add(sb.toString());
                }
                getAllPrefixedWords(child, sb, list);
                sb.setLength(sb.length() - 1);
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
        trieDictionary.displayAllUniquePrefix();

        String prefix = "bo";

        System.out.println("All words prefixed with : " + prefix);
        trieDictionary.getAllPrefixedWords(prefix).forEach(System.out::println);


        trieDictionary.delete("boot");
        trieDictionary.display();

        System.out.println("trieDictionary.search(elephant): " + trieDictionary.search("elephant"));
        System.out.println("trieDictionary.search(boot): " + trieDictionary.search("boot"));

        trieDictionary.insert(";}#)*!~", "bikas_anand");
        trieDictionary.insert("®ÆǛ", "latin");
        trieDictionary.display();
    }
}
