package com.anand.coding.problems.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * WordFilter
 */
public class WordFilter {

    private final TrieNode pRoot;
    private final TrieNode sRoot;

    private final short start='a';
    private final List<Integer> EMPTY_LIST = new ArrayList<>();

    public WordFilter(String[] words) {
        this.pRoot = new TrieNode();
        this.sRoot = new TrieNode();

        for(int i=0; i<words.length; i++){
            insert(words[i], i, pRoot);
            insertRev(words[i], i, sRoot);
        }
    }

    private void insert(final String key, int keyIdx, TrieNode trieNode) {

        for(int i=0; i<key.length(); i++) {
            char c = key.charAt(i);
            int childIndex = c-start;
            if(trieNode.children[childIndex]==null){
                trieNode.children[childIndex] = new TrieNode();
            }
            trieNode = trieNode.children[childIndex];
            trieNode.idxList.add(keyIdx);
        }
    }

    private void insertRev(final String key, int keyIdx, TrieNode trieNode) {

        for(int i=key.length()-1; i>=0; i--) {
            char c = key.charAt(i);
            int childIndex = c-start;
            if(trieNode.children[childIndex]==null){
                trieNode.children[childIndex] = new TrieNode();
            }
            trieNode = trieNode.children[childIndex];
            trieNode.idxList.add(keyIdx);
        }
    }

    private List<Integer> startsWith(String pref) {

        TrieNode trieNode = pRoot;
        for(int i=0; i<pref.length() && trieNode!=null; i++) {
            char c = pref.charAt(i);
            trieNode = trieNode.children[c-start];
        }
        return trieNode==null ? EMPTY_LIST: trieNode.idxList;
    }

    private List<Integer> endsWith(String suff) {

        TrieNode trieNode = sRoot;
        for(int i=suff.length()-1; i>=0 && trieNode!=null; i--) {
            char c = suff.charAt(i);
            trieNode = trieNode.children[c-start];
        }
        return trieNode==null ? EMPTY_LIST: trieNode.idxList;
    }

    public int f(String pref, String suff){
        List<Integer> L = startsWith(pref);
        List<Integer> R = endsWith(suff);

        for(int p=L.size()-1,s=R.size()-1; p>=0 && s>=0;){
            if (L.get(p).equals(R.get(s))) {
                return L.get(p);
            }
            if (L.get(p) < R.get(s)) s--; else p--;
        }
        return -1;
    }

    static class TrieNode {
        TrieNode [] children = new TrieNode[26] ;
        List<Integer> idxList = new ArrayList<>();
    }

    /**
     *  main function to test WordFilter
     * @param args
     */
    public static void main(String [] args){

        final WordFilter wordFilter = new WordFilter(new String[]{"apple"});

        System.out.println(wordFilter.f("a", "e"));
        System.out.println(wordFilter.f("a", "ple"));
        System.out.println(wordFilter.f("a", "pp"));
    }
}
