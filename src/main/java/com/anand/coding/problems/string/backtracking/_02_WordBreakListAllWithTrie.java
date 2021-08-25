package com.anand.coding.problems.string.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a set of words and a string, Break the string into words contained in the set if possible.
 * Return all occurrences as space separate strings.
 *
 * Example:
 * Set: ["cat","cats","and","sand","dog"]
 * String: catsanddog
 *
 * Result: [cats and dog, cat sand dog]
 *
 */
public class _02_WordBreakListAllWithTrie {

    public static List<String> wordBreak(String s, List<String> wordDictList) {
        List<String> stringList =  wordBreak(s, 0, buildTrie(wordDictList), new HashMap<>());
        return stringList==null? new ArrayList<>() : stringList.stream().map(String::trim).collect(Collectors.toList());
    }

    public static List<String> wordBreak(String s, int sIndex,
                                         TrieNode trieRoot,
                                         Map<Integer, List<String>> memoizationMap) {
        if(sIndex>=s.length()){
            return new ArrayList<String>(){{add("");}};
        }

        if (memoizationMap.containsKey(sIndex)){
            return memoizationMap.get(sIndex);
        }

        TrieNode trieNode = trieRoot;
        for(int i=sIndex; i<s.length() && trieNode.children[s.charAt(i)-'a']!=null; i++){
            trieNode = trieNode.children[s.charAt(i)-'a'];
            if(trieNode.value) {
                List<String> stringList = wordBreak(s, i + 1, trieRoot, memoizationMap);
                if (stringList != null) {
                    if(!memoizationMap.containsKey(sIndex)){
                        memoizationMap.put(sIndex, new ArrayList<>());
                    }
                    String word = s.substring(sIndex, i + 1);
                    for (String str : stringList) {
                        memoizationMap.get(sIndex).add(word + " " + str);
                    }
                }
            }
        }
        if(!memoizationMap.containsKey(sIndex)){
            memoizationMap.put(sIndex, null);
        }
        return memoizationMap.get(sIndex);
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
