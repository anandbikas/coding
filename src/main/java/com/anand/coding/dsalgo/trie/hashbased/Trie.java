package com.anand.coding.dsalgo.trie.hashbased;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie
 */
public class Trie {

    private final TrieNode root = new TrieNode();

    /**
     *
     * @param key
     * @param val
     */
    public void insert(final String key, final Object val){

        TrieNode trieNode = root;
        for(char c: key.toCharArray()) {
            if(!trieNode.charMap.containsKey(c)){
                trieNode.charMap.put(c, new TrieNode());
            }
            trieNode = trieNode.charMap.get(c);
            trieNode.count++;
        }
        trieNode.val = val;
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
            if(trieNode==null) {
                return null;
            }
        }
        return trieNode.val;
    }

    /**
     *
     * @param key
     * @return
     */
    public Object searchWithDot(final String key) {
        return searchWithDot(root, key, 0);
    }

    /**
     *
     * @param rt
     * @param key
     * @param x
     * @return
     */
    private Object searchWithDot(TrieNode rt, final String key, int x) {

        if(x==key.length()){
            return rt.val;
        }

        char c = key.charAt(x);
        if (c =='.') {
            for(TrieNode trieNode: rt.charMap.values()) {
                if(trieNode!=null) {
                    Object res = searchWithDot(trieNode, key, x+1);
                    if(res!=null) {
                        return res;
                    }
                }
            }
            return null;
        }

        TrieNode trieNode = rt.charMap.get(c);
        if (trieNode == null) {
            return null;
        }
        return searchWithDot(trieNode, key, x+1);
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
            if(trieNode==null) {
                return;
            }
        }
        trieNode.val = null;

        //Decrease counter and detach the node from where count becomes 0.
        trieNode = root;
        for(char c: key.toCharArray()) {
            if(trieNode.charMap.get(c).count==1) {
                trieNode.charMap.remove(c);
                return;
            }
            trieNode = trieNode.charMap.get(c);
            trieNode.count--;
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
            if(child!=null) {
                sb.append(c);
                if(child.val!=null) {
                    System.out.printf("%-18s%s%n", sb, child.val);
                }
                display(child, sb);
                sb.setLength(sb.length()-1);
            }
        }
    }

    /**
     *
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
    public boolean startsWith(String prefix) {

        TrieNode trieNode = root;
        for(char c: prefix.toCharArray()) {
            trieNode = trieNode.charMap.get(c);
            if(trieNode==null) {
                return false;
            }
        }
        return true;
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
        for(char c: prefix.toCharArray()) {
            trieNode = trieNode.charMap.get(c);
            if(trieNode==null) {
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
                if(child.val!=null){
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
