package com.anand.coding.dsalgo.tree.trie.hashbased;

import com.anand.coding.dsalgo.stack.ArrayStack;

import java.util.ArrayList;
import java.util.List;

/**
 * TrieDictionary using hashMap
 */
public class TrieDictionary<T> {

    private TrieNode<T> root;

    /**
     *
     */
    public TrieDictionary() {
        super();
        root = new TrieNode<>();
    }

    /**
     *
     * @param key
     * @param value
     */
    public void insert(final String key, final T value){

        final char[] charArray = key.toCharArray();

        TrieNode<T> trieNode = root;
        for(char c: charArray){
            trieNode.setChild(c);
            trieNode = trieNode.getChild(c);
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

        final char[] charArray = key.toCharArray();

        TrieNode<T> trieNode = root;
        for(char c: charArray){
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

        final char[] charArray = key.toCharArray();

        TrieNode<T> trieNode = root;
        for(char c: charArray){
            trieNode = trieNode.getChild(c);
            if(trieNode==null){
                return;
            }
        }
        trieNode.setValue(null);
    }

    /**
     * Its like
     * printAllWords
     * printAllPaths
     */
    public void display() {
        System.out.println(String.format("%10s%15s", "Key", "Value"));
        System.out.println(             "--------------------------------");

        ArrayStack<Character> stack = new ArrayStack<>();
        display(root, stack);
        System.out.println();
    }

    /**
     *
     * @param trieNode
     * @param stack
     */
    private void display(TrieNode<T> trieNode, ArrayStack<Character> stack) {
        if(trieNode == null){
            return;
        }

        for(char c: trieNode.getCharSet()){
            if(trieNode.getChild(c)!=null){
                TrieNode<T> child = trieNode.getChild(c);
                stack.push(c);
                if(child.getValue()!=null){
                    System.out.println(
                            String.format("%-18s%s", stack.getAsWord(), child.getValue()));
                }
                display(child, stack);
                stack.pop();
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

        if (prefix == null) {
            return list;
        }

        ArrayStack<Character> stack = new ArrayStack<>();

        final char[] charArray = prefix.toUpperCase().toCharArray();

        //Put all characters in prefix in the stack.
        TrieNode<T> trieNode = root;
        for(char c: charArray){
            trieNode = trieNode.getChild(c);
            if(trieNode==null){
                return list;
            }
            stack.push(c);
        }

        getAllPrefixedWords(trieNode, stack, list);

        return list;
    }

    /**
     *
     * @param trieNode
     * @param stack
     * @param list
     */
    private void getAllPrefixedWords(TrieNode<T> trieNode, ArrayStack<Character> stack, List<String> list){
        if(trieNode == null){
            return;
        }

        for(char c: trieNode.getCharSet()){
            if(trieNode.getChild(c)!=null){
                TrieNode<T> child = trieNode.getChild(c);
                stack.push(c);
                if(child.getValue()!=null){
                    list.add(stack.getAsWord());
                }
                getAllPrefixedWords(child, stack, list);
                stack.pop();
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
