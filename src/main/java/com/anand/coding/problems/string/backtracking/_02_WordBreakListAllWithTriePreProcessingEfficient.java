package com.anand.coding.problems.string.backtracking;
import java.util.*;

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
public class _02_WordBreakListAllWithTriePreProcessingEfficient {

    public static List<String> wordBreak(String s, List<String> wordDictList) {

        Map<Integer, List<String>> mem = new HashMap<>(); //mem.get(i) contains all the words ending with index i.
        mem.put(-1, null);
        TrieNode trieRoot = buildTrie(wordDictList);

        for(int i=0; i<s.length(); i++){
            if(!mem.containsKey(i-1)){
                continue;
            }
            TrieNode trieNode = trieRoot;
            for(int j=i; j<s.length() && trieNode.children[s.charAt(j)-'a']!=null; j++){
                trieNode = trieNode.children[s.charAt(j)-'a'];
                if(trieNode.value){
                    if(!mem.containsKey(j)) {
                        mem.put(j, new ArrayList<>());
                    }
                    mem.get(j).add(s.substring(i,j+1));
                }
            }
        }

        List<String> res = new ArrayList<>();
        if(mem.containsKey(s.length()-1)) {
            dfsString(s.length() - 1, mem, res, new ArrayList<>());
        }
        return res;
    }

    public static void dfsString(int index,
                                 Map<Integer, List<String>> mem,
                                 List<String> res,
                                 List<String> wordList){
        if(index<0){
            StringBuilder sb=new StringBuilder();
            for(int i=wordList.size()-1; i>-1; i--){
                sb.append(wordList.get(i)).append(" ");
            }
            res.add(sb.toString().trim());
            return;
        }
        for (String str : mem.get(index)) {
            wordList.add(str);
            dfsString(index-str.length(), mem, res, wordList);
            wordList.remove(wordList.size()-1);
        }
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
