package com.anand.coding.dsalgo.tree.trie;

import com.anand.coding.dsalgo.stack.ArrayStack;

/**
 * TrieDictionary
 */
public class TrieDictionary {

    private TrieNode root = new TrieNode();

    /**
     *
     * @param word
     * @param wordMeaning
     */
    public void insert(final String word, final String wordMeaning){

        final char[] charArray = word.toCharArray();

        TrieNode trieNode = root;
        for(char c: charArray){
            trieNode.setChild(c);
            trieNode = trieNode.getChild(c);
        }
        trieNode.setWordMeaning(wordMeaning);
    }

    /**
     *
     * @param word
     * @return
     */
    public String search(final String word){
        if(root == null){
            return null;
        }

        final char[] charArray = word.toCharArray();

        TrieNode trieNode = root;
        for(char c: charArray){
            trieNode = trieNode.getChild(c);
            if(trieNode==null){
                return null;
            }
        }
        return trieNode.getWordMeaning();
    }

    /**
     *
     * @param word
     * @return
     */
    public void delete(final String word){
        if(root == null){
            return;
        }

        final char[] charArray = word.toCharArray();

        TrieNode trieNode = root;
        for(char c: charArray){
            trieNode = trieNode.getChild(c);
            if(trieNode==null){
                return;
            }
        }
        trieNode.setWordMeaning(null);
    }

    /**
     * Its like
     * printAllWords
     * printAllPaths
     */
    public void display() {
        System.out.println(String.format("%10s%15s", "Words", "Meanings"));
        System.out.println(             "--------------------------------");

        ArrayStack<Character> stack = new ArrayStack<Character>();
        display(root, stack);
        System.out.println();
    }

    /**
     *
     * @param trieNode
     * @param stack
     */
    private void display(TrieNode trieNode, ArrayStack<Character> stack) {
        if(trieNode == null){
            return;
        }

        for(char c='A'; c<'Z'; c++){
            if(trieNode.getChild(c)!=null){
                TrieNode child = trieNode.getChild(c);
                stack.push(c);
                if(child.getWordMeaning()!=null){
                    System.out.println(
                            String.format("%-18s%s", stack.getAsWord(), child.getWordMeaning()));
                }
                display(child, stack);
                stack.pop();
            }
        }
    }


    /**
     *  main function to test TrieDictionary
     * @param args
     */
    public static void main(String [] args){

        final TrieDictionary trieDictionary = new TrieDictionary();

        trieDictionary.insert("elephant", "hathi");
        trieDictionary.insert("book", "kitab");
        trieDictionary.insert("boot","juta");
        trieDictionary.insert("hippopotamus","dariyayi ghoda");


        trieDictionary.display();

        trieDictionary.delete("boot");
        trieDictionary.display();

        System.out.println("trieDictionary.search(elephant): " + trieDictionary.search("elephant"));
        System.out.println("trieDictionary.search(boot): " + trieDictionary.search("boot"));
    }
}
