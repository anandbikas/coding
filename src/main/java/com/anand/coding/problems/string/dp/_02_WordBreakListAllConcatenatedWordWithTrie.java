package com.anand.coding.problems.string.dp;

import java.util.*;

/**
 * Given a list of unique words. Find all concatenated words.
 *
 * Example:
 * words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 */
public class _02_WordBreakListAllConcatenatedWordWithTrie {

    public static List<String> wordBreakConcatenatedWords(List<String> wordDictList) {

        TrieNode trieRoot = buildTrie(wordDictList);
        List<String> res = new ArrayList<>();
        for(String str: wordDictList) {
            if (isWordBreakPossibleDP(str,trieRoot)) {
                res.add(str);
            }
        }
        return res;
    }

    public static boolean isWordBreakPossibleDP(String s, TrieNode root) {
        if(s==null || s.length()==0){
            return false;
        }
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
                    if(i-sIndex+1<s.length()) //exclude full length word
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

        System.out.println(wordBreakConcatenatedWords(Arrays.asList("")));

        System.out.println(wordBreakConcatenatedWords(Arrays.asList("cat","dog","catdog")));

        System.out.println(wordBreakConcatenatedWords(Arrays.asList("cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat")));
    }
}
