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

    /**
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean isWordBreakPossibleDP(String s, List<String> wordDict) {
        boolean[] DP = new boolean[s.length()+1];
        Set<String> set = new HashSet<>(wordDict);
        int maxWordLength = wordDict.stream().mapToInt(String::length).max().orElse(Integer.MAX_VALUE);

        DP[0] = true;
        for (int i=1; i<=s.length(); i++){
            for (int j=i-1; j>=0 && i-j<=maxWordLength && !DP[i]; j--){
                DP[i] = DP[j] && set.contains(s.substring(j, i));
            }
        }
        System.out.println(Arrays.toString(DP));
        return DP[s.length()];
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
