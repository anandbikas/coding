package com.anand.coding.problems.string.backtracking;

import java.util.*;

/**
 * Given a set of words and a string, Break the string into words contained in the set if possible.
 *
 * Example:
 * Set: [a,cat,bat,tree,on]
 * String: catontree
 *
 * Result: [cat, on, tree]
 *
 */
public class _01_WordBreakListSingleWithTrie {

    public static List<String> wordBreak(String s, List<String> wordDictList) {
        return wordBreak(s, 0, buildTrie(wordDictList), new HashMap<>());
    }

    public static List<String> wordBreak(String s, int sIndex,
                                         TrieNode trieRoot,
                                         Map<Integer, List<String>> memoizationMap) {
        if(sIndex>=s.length()){
            return new ArrayList<>();
        }

        if (memoizationMap.containsKey(sIndex)){
            return memoizationMap.get(sIndex);
        }

        TrieNode trieNode = trieRoot;
        for(int i=sIndex; i<s.length() && trieNode.children[s.charAt(i)-'a']!=null; i++){
            trieNode = trieNode.children[s.charAt(i)-'a'];
            if(trieNode.value){
                List<String> wordList = wordBreak(s, i+1, trieRoot, memoizationMap);
                if(wordList!=null){
                    wordList.add(0, s.substring(sIndex,i+1));
                    memoizationMap.put(sIndex, wordList);
                    return wordList;
                }
            }
        }
        memoizationMap.put(sIndex, null);
        return null;
    }

    public static class TrieNode {
        TrieNode[] children=new TrieNode['z'-'a'+1];
        boolean value;
    }

    public static TrieNode buildTrie(List<String> wordDictList){
        TrieNode root = new TrieNode();

        for(String word: wordDictList){
            TrieNode trieNode = root;
            for(char c: word.toCharArray()){
                int childIndex = c-'a';
                if(trieNode.children[childIndex]==null){
                    trieNode.children[childIndex] = new TrieNode();
                }
                trieNode = trieNode.children[childIndex];
            }
            trieNode.value = true;
        }
        return root;
    }

    public static void main(String [] args) {

        System.out.println(wordBreak("catontree", Arrays.asList("a", "cat", "bat", "tree", "on")));

        System.out.println(wordBreak("catsanddog",Arrays.asList("cat","cats","and","sand","dog")));

        System.out.println(wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
    }
}
