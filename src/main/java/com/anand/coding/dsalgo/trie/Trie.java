package com.anand.coding.dsalgo.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie
 */
public class Trie {

    private final TrieNode root;
    private final short start, size;

    public Trie(int startIndex, int size) {
        this.start = (short) startIndex;
        this.size = (short) size;
        this.root = new TrieNode(size);
    }

    /**
     *
     * @param key
     * @param val
     */
    public void insert(final String key, final Object val){

        TrieNode trieNode = root;
        for(char c: key.toCharArray()) {
            int childIndex = c-start;
            if(trieNode.children[childIndex]==null) {
                trieNode.children[childIndex] = new TrieNode(size);
            }
            trieNode = trieNode.children[childIndex];
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
            trieNode = trieNode.children[c-start];
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
            for(TrieNode trieNode: rt.children) {
                if(trieNode!=null) {
                    Object res = searchWithDot(trieNode, key, x+1);
                    if(res!=null) {
                        return res;
                    }
                }
            }
            return null;
        }

        TrieNode trieNode = rt.children[c-start];
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
            trieNode = trieNode.children[c-start];
            if(trieNode==null) {
                return;
            }
        }
        trieNode.val = null;

        //Decrease counter and detach the node from where count becomes 0.
        trieNode = root;
        for(char c: key.toCharArray()) {
            if(trieNode.children[c-start].count==1) {
                trieNode.children[c-start]=null;
                return;
            }
            trieNode = trieNode.children[c-start];
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

        for(char i=0; i<size; i++) {
            TrieNode child = trieNode.children[i];
            if(child!=null) {
                sb.append((char)(i+start));
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
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {

        TrieNode trieNode = root;
        for(char c: prefix.toCharArray()) {
            trieNode = trieNode.children[c-start];
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
            trieNode = trieNode.children[c-start];
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

        for(short i=0; i<size; i++) {
            TrieNode child = trieNode.children[i];

            if(child!=null) {
                sb.append((char)(i+start));
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

        final Trie trie = new Trie('a', 26);

        trie.insert("elephant", "hathi");
        trie.insert("book", "kitab");
        trie.insert("boot","juta");
        trie.insert("hippopotamus","dariyayi ghoda");

        trie.display();

        String prefix = "bo";

        System.out.println("All words prefixed with : " + prefix);
        trie.wordsWithPrefix(prefix).forEach(System.out::println);

        trie.delete("boot");
        trie.display();

        System.out.println("trie.search(elephant): " + trie.search("elephant"));
        System.out.println("trie.search(boot): " + trie.search("boot"));

        System.out.println("trie.searchWithDot(elephant): " + trie.searchWithDot("ele.hant"));

        System.out.println("trie.startsWith(ele): " + trie.startsWith("ele"));
    }
}
