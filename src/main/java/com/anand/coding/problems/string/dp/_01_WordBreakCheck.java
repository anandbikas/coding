package com.anand.coding.problems.string.dp;
import java.util.*;

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
public class _01_WordBreakCheck {

    public static boolean isWordBreakPossibleDP(String s, List<String> wordDictList) {
        if(s==null || s.length()==0){
            return false;
        }

        boolean[] DP = new boolean[s.length()+1];
        Set<String> wordDict = new HashSet<>(wordDictList);
        int maxWordLength = wordDict.stream().mapToInt(String::length).max().orElse(Integer.MAX_VALUE);

        DP[0] = true;
        for(int sIndex=0; sIndex<s.length();sIndex++){
            if(!DP[sIndex]){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=sIndex; i<s.length() && i-sIndex<maxWordLength;i++){
                sb.append(s.charAt(i));
                if(wordDict.contains(sb.toString())){
                    DP[i+1]=true;
                }
            }
        }
        return DP[s.length()];
    }

    public static void main(String [] args) {

        System.out.println(isWordBreakPossibleDP("catontree", Arrays.asList("a", "cat", "bat", "tree", "on")));

        System.out.println(isWordBreakPossibleDP("catsanddog",Arrays.asList("cat","cats","and","sand","dog")));

        System.out.println(isWordBreakPossibleDP("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
    }
}
