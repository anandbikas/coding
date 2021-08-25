package com.anand.coding.problems.string.dp;

import java.util.Arrays;
import java.util.List;

/**
 * Given a set of words and a string, Check if the string can be broken into words contained in the set.
 *
 * Example:
 * Set: [a,cat,bat,tree,on]
 * String: catontree
 *
 * Result: [cat, on, tree]
 *
 */
public class _01_WordBreakCheckWithTrie {

    public static boolean isWordBreakPossibleDP(String s, List<String> wordDictList) {
        if(s==null || s.length()==0){
            return false;
        }
        TrieNode root = buildTrie(wordDictList);
        boolean[] DP = new boolean[s.length()+1];

        DP[0] = true;
        for(int sIndex=0; sIndex<s.length();sIndex++){
            if(!DP[sIndex]){
                continue;
            }
            TrieNode trieNode = root;
            for(int i=sIndex; i<s.length() && trieNode.children[s.charAt(i)-'a']!=null; i++){
                trieNode = trieNode.children[s.charAt(i)-'a'];
                if(trieNode.value){
                    DP[i+1]=true;
                }
            }
        }
        return DP[s.length()];
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

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(isWordBreakPossibleDP("catontree", Arrays.asList("a", "cat", "bat", "tree", "on")));

        System.out.println(isWordBreakPossibleDP("catsanddog",Arrays.asList("cat","cats","and","sand","dog")));

        System.out.println(isWordBreakPossibleDP("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
    }
}
