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
public class _01_WordBreakListSingle {

    /**
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak(String s, List<String> wordDict) {

        int maxWordLength = wordDict.stream().mapToInt(String::length).max().orElse(Integer.MAX_VALUE);
        return wordBreak(new HashSet<>(wordDict), s, 0, new HashMap<>(), maxWordLength);
    }

    /**
     *
     * @param wordDict
     * @param s
     * @param sIndex
     * @param memoizationMap
     * @param maxWordLength
     * @return
     */
    public static List<String> wordBreak(Set<String> wordDict,
                                         String s,
                                         int sIndex,
                                         Map<Integer, List<String>> memoizationMap,
                                         int maxWordLength) {

        if(sIndex>=s.length()){
            return new ArrayList<>();
        }

        if (memoizationMap.containsKey(sIndex)){
            return memoizationMap.get(sIndex);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=sIndex; i<s.length() && sb.length()<maxWordLength; i++){
            sb.append(s.charAt(i));
            if(wordDict.contains(sb.toString())){
                List<String> wordList = wordBreak(wordDict, s, i+1, memoizationMap, maxWordLength);
                if(wordList!=null){
                    wordList.add(0, sb.toString());
                    memoizationMap.put(sIndex, wordList);
                    return wordList;
                }
            }
        }
        memoizationMap.put(sIndex, null);
        return null;
    }

    public static void main(String [] args) {

        System.out.println(wordBreak("catontree", Arrays.asList("a", "cat", "bat", "tree", "on")));

        System.out.println(wordBreak("catsanddog",Arrays.asList("cat","cats","and","sand","dog")));

        System.out.println(wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
    }
}
