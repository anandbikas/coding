package com.anand.coding.problems.string.heap;

import java.util.PriorityQueue;

/**
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Example:
 * String "aab"
 * Result: "aba"
 *
 * Solve: Same as LongestDiverseString except no same char can be adjacent.
 *
 */
public class _09_RearrangeString {

    public static String reorganizeString(String s) {

        int []charCount = new int[26];
        char charA='a';
        int size=charCount.length;
        int recentCharConsumption = -1;

        for (char c: s.toCharArray()) {
            charCount[c-charA]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>( (x, y) -> charCount[y] - charCount[x]); //Max heap
        for (int i=0; i<size; i++) {
            queue.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (true){

            int currCharIndex = queue.remove();
            if (recentCharConsumption==currCharIndex) {
                int next = queue.remove();
                queue.add(currCharIndex);
                currCharIndex = next;
            }
            if(charCount[currCharIndex] == 0) {
                break;
            }

            recentCharConsumption=currCharIndex;

            sb.append((char) (charA + currCharIndex));
            charCount[currCharIndex]--;
            queue.add(currCharIndex);
        }
        return sb.length()==s.length() ? sb.toString() : "";
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        System.out.println(reorganizeString("aab"));
    }
}
