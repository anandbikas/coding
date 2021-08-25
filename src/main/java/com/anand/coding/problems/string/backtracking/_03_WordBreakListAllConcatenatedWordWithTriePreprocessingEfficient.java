package com.anand.coding.problems.string.backtracking;
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
public class _03_WordBreakListAllConcatenatedWordWithTriePreprocessingEfficient {

    public static List<String> wordBreak(List<String> wordDictList) {

        TrieNode trieRoot = buildTrie(wordDictList);

        List<String> res = new ArrayList<>();
        for(String s: wordDictList) {
            Map<Integer, List<String>> mem = new HashMap<>(); //mem.get(i) contains all the words ending with index i.
            mem.put(-1, new ArrayList<>());

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
            if(mem.containsKey(s.length()-1) && mem.get(s.length()-1).size()>1) {
                res.add(s);
            }
        }

        return res;
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

        System.out.println(wordBreak(Arrays.asList("")));

        System.out.println(wordBreak(Arrays.asList("cat","dog","catdog")));

        System.out.println(wordBreak(Arrays.asList("cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat")));
    }
}
